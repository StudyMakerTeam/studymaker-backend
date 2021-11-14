package com.anytime.studymaker.config.annotation;


import com.anytime.studymaker.config.annotation.QueryStringToJson;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Component
public class QueryStringArgsResolver implements HandlerMethodArgumentResolver {
    private final ObjectMapper mapper;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(QueryStringToJson.class) != null;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        final HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        final String json = queryStringToJson(request.getQueryString());
        final Object result = mapper.readValue(json, parameter.getParameterType());
        return result;
    }

    private String queryStringToJson(String queryString) {
        String result = "{\"";

        for (int i = 0; i < queryString.length(); i++) {
            if (queryString.charAt(i) == '=') {
                result += "\"" + ":" + "\"";
            } else if (queryString.charAt(i) == '&') {
                result += "\"" + "," + "\"";
            } else {
                result += queryString.charAt(i);
            }
        }
        result += "\"" + "}";
        return result;
    }
}
