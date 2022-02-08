package com.midas.userservice.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtUtil {

    private final Environment env;

    public String createToken(String email, List<String> roles) {
        Algorithm algorithm = Algorithm.HMAC256(env.getProperty("jwt.secret"));
        return JWT.create()
                .withSubject(email)
                .withExpiresAt(new Date(System.currentTimeMillis() + Long.valueOf(env.getProperty("jwt.expires_sec"))))
                .withClaim("roles", roles)
                .sign(algorithm);
    }

    public String createRefreshToken(String email) {
        Algorithm algorithm = Algorithm.HMAC256(env.getProperty("jwt.refresh_secret"));
        return JWT.create()
                .withSubject(email)
                .withExpiresAt(new Date(System.currentTimeMillis() + Long.valueOf(env.getProperty("jwt.expires_sec"))))
                .sign(algorithm);
    }

    public boolean isValidateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(env.getProperty("jwt.secret"));
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            log.info("Token Validation Success");
            log.info("Token Email: {}", jwt.getSubject());
            log.info("Token ExpiresAt: {}", jwt.getExpiresAt());
        } catch (JWTVerificationException jex) {
            log.error("Token Validation Error: " + jex.getMessage());
            return false;
        } catch (Exception ex) {
            log.error("Token Validation Error: " + ex.getMessage());
            return false;
        }
        return true;
    }

    public boolean isValidateRefreshToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(env.getProperty("jwt.refresh_secret"));
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            log.info("Refresh Token Validation Success");
            log.info("Refresh Token Email: {}", jwt.getSubject());
            log.info("Refresh Token ExpiresAt: {}", jwt.getExpiresAt());
        } catch (JWTVerificationException jex) {
            log.error("Refresh Token Validation Error: " + jex.getMessage());
            return false;
        } catch (Exception ex) {
            log.error("Refresh Token Validation Error: " + ex.getMessage());
            return false;
        }
        return true;
    }

    public DecodedJWT decodedJWT(String token) {
        Algorithm algorithm = Algorithm.HMAC256(env.getProperty("jwt.secret"));
        JWTVerifier verifier = JWT.require(algorithm)
                .build();
        return verifier.verify(token);
    }
}
