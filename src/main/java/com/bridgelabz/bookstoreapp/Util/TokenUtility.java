package com.bridgelabz.bookstoreapp.Util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import org.springframework.stereotype.Component;


@Component
public class TokenUtility {
    private static final String TOKEN_SECRET="Akshay";

    public String createToken(Integer id) {

        Algorithm algo = Algorithm.HMAC256(TOKEN_SECRET);
        String token = JWT.create().withClaim("id_key", id).sign(algo);

        return token;
    }
    public int decodeToken(String token) throws SignatureVerificationException {

        Verification verification = JWT.require(Algorithm.HMAC256(TOKEN_SECRET));
        JWTVerifier jwtVerifier = verification.build();

        DecodedJWT decodedJWT = jwtVerifier.verify(token);

        Claim idClaim = decodedJWT.getClaim("id_key");
        int id = Math.toIntExact(idClaim.asLong());

        return id;
    }
}
