package com.yuegou.utils;

import com.yuegou.config.TokenConfig;
import com.yuegou.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Component
public class JwtUtil {

    @Autowired
    private TokenConfig tokenConfig;

    public String createToken(User user){
        System.out.println(tokenConfig.getTokenKey());
        System.out.println(tokenConfig.getTokenExpirationTime());
        String userName = user.getUserName();
        Integer userPower = user.getUserPower();
        Map<String,Object> claim = new HashMap<>();
        claim.put("userName", userName);
        claim.put("userPower", userPower);
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,tokenConfig.getTokenKey())
                .setClaims(claim)
                .setExpiration(new Date(System.currentTimeMillis() + tokenConfig.getTokenExpirationTime()))
                .compact();
    }

    public Claims parseToken(String token){
        return Jwts.parser()
                .setSigningKey(tokenConfig.getTokenKey())
                .parseClaimsJws(token)
                .getBody();
    }

}
