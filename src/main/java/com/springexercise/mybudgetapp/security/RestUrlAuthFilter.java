package com.springexercise.mybudgetapp.security;

import javax.servlet.http.HttpServletRequest;

//import org.springframework.security.web.util.matcher.RequestMatcher;

//Spring Security exercise for creating a Custom Rest URL Authentication Filter

//@Slf4j
public class RestUrlAuthFilter extends AbstractRestAuthFilter {


    //public RestUrlAuthFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
    //    super(requiresAuthenticationRequestMatcher);
    //}

    protected String getPassword(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getParameter("apiSecret");
    }

    protected String getUsername(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getParameter("apiKey");
    }
}
