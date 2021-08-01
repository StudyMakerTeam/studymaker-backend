package com.anytime.studymaker.auth.jwt;

import com.anytime.studymaker.domain.user.User;
import com.anytime.studymaker.domain.user.cache.Token;
import com.anytime.studymaker.domain.user.cache.TokenRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;


@Slf4j
@RequiredArgsConstructor
@Component
public class JWTProvider implements TokenProvider {
    private final TokenRepository tokenRepository;

    private static final String AUTHORITY_KEY = "authority";
    private static final String BEARER_TYPE = "Bearer ";

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.tokenValidityTimeLimit}")
    private long tokenValidityTimeLimit;

    private Key key;

    @Override
    public void afterPropertiesSet() throws Exception {
        byte[] keyBytes = Base64.getEncoder().encode(secret.getBytes());
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public Token publishToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        String access = createAccessToken(user);
        String refresh = createRefreshToken(user);

        return tokenRepository.save(Token.builder()
                        .accessToken(access).refreshToken(refresh)
                        .userId(user.getUserId()).build());
    }

    @Override
    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody();
        String[] authorities = claims.get(AUTHORITY_KEY, String.class).split(", ");
        Set<GrantedAuthority> authoritySet = Arrays.stream(authorities).map(SimpleGrantedAuthority::new).collect(Collectors.toSet());

        User user = User.builder().email(claims.getSubject()).userId(claims.get("userId", Long.class)).build();
        return new UsernamePasswordAuthenticationToken(user, null, authoritySet);
    }


    @Override
    public String createAccessToken(User user) {
        long now = System.currentTimeMillis();
        Date validity = new Date(now + this.tokenValidityTimeLimit);
        String authoritySet = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(", "));

        return BEARER_TYPE + Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuer(issuer)
                .setExpiration(validity)
                .claim(AUTHORITY_KEY, authoritySet)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    @Override
    public String createRefreshToken(User user) {
        long now = System.currentTimeMillis();
        Date validity = new Date(now + this.tokenValidityTimeLimit);
        return BEARER_TYPE + Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuer(issuer)
                .setExpiration(validity)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
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
