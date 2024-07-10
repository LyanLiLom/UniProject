package com.cybersoft.uniclub.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class JWTUtils {
    @Value("${key.token.jwt}")
    private String strkey;

    public String createToken(String data){

        //Tạo token từ key đã sinh ra và lữu trữ trước đó
        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(strkey));//(Đưa lại về Secret để sử dụng)
        return Jwts.builder().subject(data).signWith(secretKey).compact();
    }

    public String decryptToken(String token){
        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(strkey));

        String data = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getSubject();

        return data;
    }
}
