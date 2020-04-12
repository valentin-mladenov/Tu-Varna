package com.vale.warehouses.auth.security;

import com.vale.warehouses.auth.service.UserDetailsServiceImpl;
import com.vale.warehouses.auth.service.interfaces.AuthServiceInterface;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

public class AuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    @Autowired
    AuthServiceInterface authService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    public AuthenticationFilter(final RequestMatcher requiresAuth) {
        super(requiresAuth);
    }

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse
    ) throws AuthenticationException, IOException, ServletException {
        String token = StringUtils.isNotEmpty(httpServletRequest.getHeader(AUTHORIZATION))
                ? httpServletRequest.getHeader(AUTHORIZATION) : "";

        token = StringUtils.removeStart(token, "Bearer").trim();

        UUID tokenUUID = UUID.fromString(token);

        if (!authService.isTokenValid(tokenUUID)) {
            throw new BadCredentialsException("Token expired");
        }

        String userName = authService.findUserByToken(tokenUUID).getUsername();

        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);

        Authentication requestAuthentication =
                new UsernamePasswordAuthenticationToken(userName, token, userDetails.getAuthorities());

        return getAuthenticationManager().authenticate(requestAuthentication);
    }

    @Override
    protected void successfulAuthentication(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final FilterChain chain,
            final Authentication authResult
    ) throws IOException, ServletException {

        SecurityContextHolder.getContext().setAuthentication(authResult);

        chain.doFilter(request, response);
    }
}
