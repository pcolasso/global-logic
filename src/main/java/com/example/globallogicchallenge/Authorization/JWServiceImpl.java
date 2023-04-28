package com.example.globallogicchallenge.Authorization;

import com.example.globallogicchallenge.entities.User;
import com.example.globallogicchallenge.exeptions.BadRequestException;
import com.example.globallogicchallenge.exeptions.UnauthorizedException;
import com.example.globallogicchallenge.repositories.UserRepository;
import com.example.globallogicchallenge.statics.Static;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Service
public class JWServiceImpl implements JWTService {

    @Autowired
    UserRepository userRepository;


    public String createJWT (UUID usr, String password) {

        long timeStamp = System.currentTimeMillis();
        String jwtToken = Jwts.builder()
                .claim("user", usr)
                .claim("password", password)
                .setSubject(usr.toString())
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(new Date(timeStamp))
                .setExpiration(new Date(timeStamp + 500000L))
                .signWith(SignatureAlgorithm.HS256, Static.SECRET)
                .compact();
        return jwtToken;
    }

    @Override
    public User validateJwt(String authHeader) throws RuntimeException {
        if (!Strings.isBlank(authHeader)) {
            String[] authHeaderArr = authHeader.split("Bearer ");

            if (authHeaderArr.length > 1 && authHeaderArr[1] != null) {
                String token = authHeaderArr[1];

                try {

                    Claims claims = Jwts.parser().setSigningKey(Static.SECRET).parseClaimsJws(token).getBody();
                    String usrFromToken = claims.getSubject();
                    User user = userRepository.findById(UUID.fromString(usrFromToken));
                    if (Objects.isNull(user)) {
                        throw new UnauthorizedException("Invalid token, user not found");
                    }
                    if (!user.getTokenVersion().equals(claims.getId())) {
                        throw new UnauthorizedException("Invalid authorization token");
                    }
                    if (claims.getExpiration().getTime() >= System.currentTimeMillis()) {
                        return user;
                    }
                } catch (SignatureException se) {
                    throw new BadRequestException("Jwt was corrupted and cannot be validated");
                }
            } else {
                throw new BadRequestException("Invalid Authorization Header. Desired format is 'Bearer token'");
            }
        } else {
            throw new BadRequestException("Authorization token header is missing");
        }
        return null;
    }

}
