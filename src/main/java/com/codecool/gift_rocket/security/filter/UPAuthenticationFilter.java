package com.codecool.gift_rocket.security.filter;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.codecool.gift_rocket.model.AuthenticationDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

public class UPAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private Logger logger = LoggerFactory.getLogger(UPAuthenticationFilter.class);

    private static final AntPathRequestMatcher ANT_PATH_REQUEST_MATCHER = new AntPathRequestMatcher("/user/login",
            "POST");

    @Autowired
    public UPAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
        setRequiresAuthenticationRequestMatcher(ANT_PATH_REQUEST_MATCHER);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        AuthenticationDTO authenticationDTO;
        try {
            authenticationDTO = new ObjectMapper().readValue(request.getInputStream(), AuthenticationDTO.class);
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    authenticationDTO.getUsername(),
                    authenticationDTO.getPassword());
            return getAuthenticationManager().authenticate(authToken);
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        var message = new StringBuilder()
                .append("Inside UPAuthenticationFilter: ")
                .append("\n")
                .append(String.format("Request: %s\n", request.toString()))
                .append(String.format("Response: %s\n", response.toString()))
                .toString();
        super.doFilter(request, response, chain);
    }

    @Override
    public void successfulAuthentication(HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain,
            Authentication authenticationResult) throws IOException, ServletException {
        User user = (User) authenticationResult.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        String accessToken = JWT
                .create()
                .withSubject(user.getUsername())
                .withIssuer(request.getRequestURL().toString())
                .withClaim("role",
                        user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
        Cookie cookie = new Cookie("token", accessToken);
        cookie.setMaxAge(60 * 10 * 10);
        cookie.setSecure(true);
        response.addCookie(cookie);
        response.setStatus(HttpStatus.OK.value());
    }
}
