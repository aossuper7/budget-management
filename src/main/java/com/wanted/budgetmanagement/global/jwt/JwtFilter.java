package com.wanted.budgetmanagement.global.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wanted.budgetmanagement.global.exception.ErrorCode;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends GenericFilterBean {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    private final JwtTokenProvider jwtTokenProvider;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String jwt = getBearerToken(httpServletRequest.getHeader(AUTHORIZATION_HEADER));

        if (jwt != null) {
            try {
                jwtTokenProvider.validateToken(jwt);
                Authentication authentication = jwtTokenProvider.getAuthentication(jwt);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                chain.doFilter(request, response);
            } catch (ExpiredJwtException e) {
                sendResponse(httpServletResponse, ErrorCode.ACCESS_TOKEN_EXPIRED);
            } catch (JwtException | IllegalArgumentException e) {
                sendResponse(httpServletResponse, ErrorCode.INVALID_JWT);
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    private void sendResponse(HttpServletResponse response, ErrorCode status) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(convertObjectToJson(status));
    }

    private String convertObjectToJson(Object code) throws IOException {
        return objectMapper.writeValueAsString(code);
    }

    private String getBearerToken(String token) {
        if (StringUtils.hasText(token) && token.startsWith("Bearer "))
            return token.substring(7);
        return null;
    }
}