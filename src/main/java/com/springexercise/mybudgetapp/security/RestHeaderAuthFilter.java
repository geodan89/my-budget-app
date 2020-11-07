package com.springexercise.mybudgetapp.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;

//Spring Security exercise for creating a Custom Rest Header Authentication Filter

@Slf4j
public class RestHeaderAuthFilter extends AbstractRestAuthFilter {


    public RestHeaderAuthFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
        super(requiresAuthenticationRequestMatcher);
    }

    protected String getPassword(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getHeader("Api-Secret");
    }

    protected String getUsername(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getHeader("Api-Key");
    }
}
