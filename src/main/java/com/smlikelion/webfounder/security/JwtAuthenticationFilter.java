package com.smlikelion.webfounder.security;

import com.smlikelion.webfounder.security.exception.EmptyTokenException;
import com.smlikelion.webfounder.security.exception.InvalidTokenException;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("JwtAuthenticationFilter is invoked!");
        try{
            String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
            if (bearerToken == null || !bearerToken.startsWith("Bearer ")) {
                System.out.println("Authorization 헤더에 토큰이 없습니다.");
                throw new EmptyTokenException("Authorization 헤더에 토큰이 없습니다.");
            }

            String jwtToken = jwtTokenProvider.resolveToken(bearerToken); // Bearer 제거
            if (jwtToken != null && jwtTokenProvider.validateToken(jwtToken)) {
                // 토큰이 유효한 경우에만 Authentication 객체를 가져옴
                Authentication authentication = jwtTokenProvider.getAuthentication(jwtToken);
                if (authentication != null) {
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    System.out.println("토큰이 유효합니다.");
                } else {
                    throw new InvalidTokenException("토큰 유효성을 검증한 후 Authentication 객체를 가져오지 못했습니다.");
                }
            } else {
                throw new InvalidTokenException("토큰이 유효하지 않습니다.");
            }
            filterChain.doFilter(request, response);
        }
        catch(ExpiredJwtException e) {
            throw new InvalidTokenException("토큰이 만료되었습니다.");
        }
        catch(NullPointerException e) {
            throw new EmptyTokenException("Authorization 헤더에 토큰이 없습니다.");
        }
    }
}