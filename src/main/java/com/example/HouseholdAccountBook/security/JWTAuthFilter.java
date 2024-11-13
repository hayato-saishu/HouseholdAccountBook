package com.example.HouseholdAccountBook.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.HouseholdAccountBook.dto.LoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;
import java.util.ArrayList;

public class JWTAuthFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JWTAuthFilter(AuthenticationManager authenticationManager) {

        // AuthenticationManagerの設定
        this.authenticationManager = authenticationManager;

        // ログインPATHの設定
        setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/api/auth/login", "POST"));
        // ログイン用パラメータの設定
        setUsernameParameter("email");
        setPasswordParameter("password");

        // ログイン成功時はtokenを発行してレスポンスにセットする
        this.setAuthenticationSuccessHandler((request, response, ex) -> {
            // トークンの作成
            String token = JWT.create().withIssuer("https:HouseholdAccountBook.com").withClaim("email", ex.getName()).sign(Algorithm.HMAC256("secret"));

            response.setHeader("X-AUTH-TOKEN", token);
            response.setStatus(200);
        });

        // ログイン失敗時
        this.setAuthenticationFailureHandler((request, response, ex) -> {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        });

    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {
            ServletInputStream stream = request.getInputStream();
            // リクエストのJSONの値をログインリクエストにマッピング
            LoginRequest loginRequest = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);
            return this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword(), new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
