package com.example.hellospring.jwt;

import com.example.hellospring.beans.User;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class JwtTokenUtil {
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(JwtTokenUtil.class);
    private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000; //24 hours

    @Value("${app.jwt.secret}")
    private String secretKey;

    public String generateAccessToken(User user){
        return Jwts.builder()
                .setSubject(user.getId()+","+ user.getEmail())
                .setIssuer("codeJava")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public boolean validateAccessToken(String token){
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        }catch (ExpiredJwtException ex){
            LOGGER.error("JWT Expired", ex.getMessage());
        }catch (IllegalArgumentException e){
            LOGGER.error("Token is null, empty or has only whitespace", e.getMessage());
        }catch (MalformedJwtException m){
            LOGGER.error("JWT is invalid ", m.getMessage());
        }catch (UnsupportedJwtException j){
            LOGGER.error("JWT is Not Supported",j);
        }catch (SignatureException s){
            LOGGER.error("Signature validation failed", s);
        }
        return false;
    }

    public String getSubject(String token){
        return parseClaims(token).getSubject();
    }
    private Claims parseClaims(String token){
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();

    }
}
