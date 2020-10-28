package com.springexercise.mybudgetapp.security;

//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
//import org.springframework.security.web.util.matcher.RequestMatcher;

//Spring Security exercise for creating a Custom Authentication Filter

//@Slf4j
public abstract class AbstractRestAuthFilter { //extends AbstractAuthenticationProcessingFilter {

    //public AbstractRestAuthFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
    //    super(requiresAuthenticationRequestMatcher);
    //}

//    @Override
//    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
//            throws IOException, ServletException {
//
//        HttpServletRequest request = (HttpServletRequest) req;
//        HttpServletResponse response = (HttpServletResponse) res;
//
//
//        if (logger.isDebugEnabled()) {
//            logger.debug("Request is to process authentication");
//        }
//
//        try {
//            Authentication authResult = attemptAuthentication(request, response);
//
//
//            if (authResult != null) {
//                successfulAuthentication(request, response, chain, authResult);
//            } else {
//                chain.doFilter(request, response);
//            }
//        } catch (AuthenticationException e) {
//            unsuccessfulAuthentication(request, response, e);
//        }
//    }
//
//    @Override
//    protected void unsuccessfulAuthentication(HttpServletRequest request,
//                                              HttpServletResponse response, AuthenticationException failed)
//            throws IOException, ServletException {
//        SecurityContextHolder.clearContext();
//
//        if (log.isDebugEnabled()) {
//            log.debug("Authentication request failed: " + failed.toString(), failed);
//            log.debug("Updated SecurityContextHolder to contain null Authentication");
//        }
//
//        response.sendError(HttpStatus.UNAUTHORIZED.value(),
//                HttpStatus.UNAUTHORIZED.getReasonPhrase());
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
//        String userName = getUsername(httpServletRequest);
//        String password = getPassword(httpServletRequest);
//
//        if (userName == null) {
//            userName = "";
//        }
//        if (password == null) {
//            password = "";
//        }
//
//        log.debug("Authentication User: " + userName);
//
//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userName, password);
//
//        if (!StringUtils.isEmpty(userName)) {
//            return this.getAuthenticationManager().authenticate(token);
//        } else {
//            return null;
//        }
//    }
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request,
//                                            HttpServletResponse response, FilterChain chain, Authentication authResult)
//            throws IOException, ServletException {
//
//        if (logger.isDebugEnabled()) {
//            logger.debug("Authentication success. Updating SecurityContextHolder to contain: "
//                    + authResult);
//        }
//
//        SecurityContextHolder.getContext().setAuthentication(authResult);
//    }
//
//    protected abstract String getPassword(HttpServletRequest httpServletRequest);
//
//    protected abstract String getUsername(HttpServletRequest httpServletRequest);
}
