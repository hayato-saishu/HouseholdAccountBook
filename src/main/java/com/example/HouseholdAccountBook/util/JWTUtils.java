package com.example.HouseholdAccountBook.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;

@Service
public class JWTUtils extends UsernamePasswordAuthenticationFilter {

    // Secret Keyと有効期限を定数化
    private static final String SECRET_KEY = generateKey();
    private static final long EXPIRATION_TIME = 1000 * 60 * 24 * 7; // 7日間

    // JWTトークンを生成
    public String generateToken(Authentication authentication) {
        String email = authentication.getName();  // ユーザーのemailを取得

        // トークンの発行日と有効期限を設定
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + EXPIRATION_TIME);

        // JWTを生成して返す
        return JWT.create()
                .withIssuer("https://HouseholdAccountBook.com")
                .withClaim("email", email)  // emailをクレームに設定
                .withExpiresAt(expireDate)
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    // ランダムな秘密鍵を生成
    private static String generateKey() {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[32];
        random.nextBytes(key);
        return Base64.getEncoder().encodeToString(key); // Base64でエンコード
    }

    // トークンからemailを取得
    public String getEmail(String token) {
        return getClaimFromToken(token);
    }

    // JWTのクレームを取得
    private String getClaimFromToken(String token) {
        token = token.substring(7);  // "Bearer " プレフィックスを削除

        // アルゴリズムを使用してトークンを検証
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);

        return jwt.getClaim("email").asString();  // 指定されたクレームを取得
    }

    // トークンの検証（有効期限も確認）
    public boolean validateToken(String token) {
        try {
            // トークンを検証
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);  // 検証

            return true;  // 検証に成功したら有効
        } catch (JWTVerificationException e) {
            // 検証エラーやデコードエラーが発生した場合
            return false;
        }
    }
}
