package com.anytime.studymaker.config.jwt;

import com.anytime.studymaker.domain.user.Roles;
import com.anytime.studymaker.domain.user.User;
import com.anytime.studymaker.domain.user.component.Authority;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;


@Slf4j
@Component
public class JWTProvider implements TokenProvider {
    private static final String AUTHORITY_KEY = "authority";

    private Key key;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.tokenValidityTimeLimit}")
    private long tokenValidityTimeLimit;

    @Override
    public void afterPropertiesSet() throws Exception {
        byte[] keyBytes = Base64.getEncoder().encode(secret.getBytes());
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public String createToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        String authoritySet = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(", "));

        long now = new Date().getTime();
        Date validity = new Date(now + this.tokenValidityTimeLimit);
        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuer(issuer)
                .setExpiration(validity)
                .claim("userId", user.getUserId())
                .claim("username", user.getName())
                .claim("nickname", user.getNickname())
                .claim(AUTHORITY_KEY, authoritySet)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    @Override
    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody();
        String[] authorities = claims.get(AUTHORITY_KEY, String.class).split(", ");
        Collection<GrantedAuthority> authoritySet = new HashSet<>();
        for (String authority : authorities) {
            Roles roles = new Roles();
            if (authority.equals(Authority.ROLE_USER.getRole())) {
                roles.setAuthority(Authority.ROLE_USER);
            } else {
                roles.setAuthority(Authority.ROLE_ADMIN);
            }
            authoritySet.add(roles);
        }
        User user = User.builder().email(claims.getSubject()).userId(claims.get("userId", Long.class)).build();
        return new UsernamePasswordAuthenticationToken(user, null, authoritySet);
    }

    @Override
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }
}
