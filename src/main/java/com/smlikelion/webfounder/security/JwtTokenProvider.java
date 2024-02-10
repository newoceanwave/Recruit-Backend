package com.smlikelion.webfounder.security;

import com.smlikelion.webfounder.admin.entity.Role;
import com.smlikelion.webfounder.security.exception.InvalidTokenException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    private final long ACCESS_TOKEN_VALID_TIME = 1000L * 60 * 60 * 24 * 7; // 7일
    private final long REFRESH_TOKEN_VALID_TIME = 1000L * 60 * 60 * 24 * 30; // 30일
    private final String BEARER_TYPE = "Bearer ";

    @Value("${secret.jwt}")
    private String baseSecretKey;

    //jwt 토큰 생성
    public TokenInfo createAccessToken(String accountId, Role role) {
        System.out.println("JwtTokenProvider.createAccessToken");
        return createToken(accountId, role, ACCESS_TOKEN_VALID_TIME);
    }

    public TokenInfo createRefreshToken(String accountId, Role role) {
        return createToken(accountId, role, REFRESH_TOKEN_VALID_TIME);
    }

    //토큰 유효성 검사
    public Boolean validateToken(String token) {
        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(baseSecretKey));
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey).build()
                    .parseClaimsJws(token);
            return true;
        } catch (SecurityException e) {
            throw new InvalidTokenException("Jwt Security error");
        } catch (MalformedJwtException e) {
            throw new InvalidTokenException("MalformedJwtException - 잘못된 Jwt Token");
        } catch (ExpiredJwtException e) {
            throw new InvalidTokenException("ExpiredJwtException - 만료된 Jwt Token");
        } catch (UnsupportedJwtException e) {
            throw new InvalidTokenException("UnsupportedJwtException - 지원하지 않는 Jwt Token");
        } catch (IllegalArgumentException e) {
            throw new InvalidTokenException("IllegalArgumentException - 잘못된 헤더");
        } catch (io.jsonwebtoken.security.SignatureException e) {
            throw new InvalidTokenException("SignatureException - 잘못된 Token 형식");
        }
    }

    //jwt 토큰 생성
    private TokenInfo createToken(String accountId, Role role, long validTime) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + validTime);
        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(baseSecretKey));

        String token = Jwts.builder()  //jwt payload에 저장되는 정보 명시
                .claim("accountId", accountId)
                .claim("ROLE_", role)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();

        token = BEARER_TYPE + token;
        return new TokenInfo(token, expiration.getTime()-now.getTime());
    }

    public Authentication getAuthentication(String token) throws AuthenticationException {
        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(baseSecretKey));

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();

        String accountId = claims.get("accountId", String.class);
        Role role = claims.get("role", Role.class);

        List<GrantedAuthority> authorities = extractAuthoritiesFromRole(role);

        return new UsernamePasswordAuthenticationToken(accountId, null, authorities);
    }

    // ROLE에 따라 특정 역할과 기본 역할 설정
    private List<GrantedAuthority> extractAuthoritiesFromRole(Role role) {
        List<String> roles = Arrays.asList("ROLE_USER"); // 기본 역할은 USER로 설정

        if (role == Role.SUPERUSER) {
            roles = Arrays.asList("ROLE_SUPERUSER", "ROLE_USER");
        } else if (role == Role.MANAGER) {
            roles = Arrays.asList("ROLE_MANAGER", "ROLE_USER");
        }

        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    public String resolveToken(String token) {
        if(token.startsWith("Bearer ")) {
            return token.replace("Bearer ", "");
        }
        return null;
    }

    public String getAccountId(String token) {
        Claims claims = parseClaims(token);
        return claims.get("accountId").toString();
    }

    public String getRole(String token) {
        Claims claims = parseClaims(token);
        return claims.get("role").toString();
    }

    private Claims parseClaims(String token) {
        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(baseSecretKey));
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
    }


}
