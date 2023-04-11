package com.iot.api.seguridad;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@Component
public class JWTUtil {
    private Dotenv dotenv;

    public String validateTokenAndRetrieveSubject(String token) throws JwtException {
        String secret_key=System.getenv("SECRET_KEY");

        Algorithm algorithm= Algorithm.HMAC256(secret_key.getBytes());
        JWTVerifier verifier= JWT.require(algorithm).build();
        DecodedJWT jwt= verifier.verify(token);



        return jwt.getSubject();

    }
}
