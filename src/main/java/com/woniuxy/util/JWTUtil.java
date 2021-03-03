package com.woniuxy.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Date;
import java.util.HashMap;

public class JWTUtil {
    public static final Long EXPIRE_TIME=6*60*60*1000L;
    public static final String SIGV="shdahu9dfn5svsd";

    public static String getToken(HashMap<String,String> map) {
        JWTCreator.Builder builder = JWT.create();

        map.forEach((k,v)->{
            builder.withClaim(k,v);
            });

        builder.withExpiresAt(new Date(System.currentTimeMillis()+EXPIRE_TIME));
        String token = builder.sign(Algorithm.HMAC256(SIGV));

        return token;
    }
    public static DecodedJWT decodedJWT(String token) {
        return JWT.require(Algorithm.HMAC256(SIGV)).build().verify(token);

    }

}
