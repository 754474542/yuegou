package com.yuegou.utils;

import com.yuegou.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

    private static final String TOKEN_KEY = "YueGou_JWT_Key";

    public static String createToken(User user){
        String userName = user.getUserName();
        Integer userPower = user.getUserPower();
        Map<String,Object> claim = new HashMap<>();
        claim.put("userName", userName);
        claim.put("userPower", userPower);
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,TOKEN_KEY)
                .setClaims(claim)
                .setExpiration(new Date(System.currentTimeMillis() + 43200 * 1000))
                .compact();
    }

    public static Claims parseToken(String token){
        return Jwts.parser()
                .setSigningKey(TOKEN_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

}
