package com.yuegou.utils;

import com.yuegou.config.TokenConfig;
import com.yuegou.controller.pretreatment.Code;
import com.yuegou.entity.User;
import io.jsonwebtoken.*;
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
        Claims body = null;
        try {
            body = Jwts.parser()
                    .setSigningKey(tokenConfig.getTokenKey())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new com.yuegou.controller.pretreatment.exceptionhandle.ExpiredJwtException(Code.TOKEN_EXPIRE_ERR,"登录过期，请重新登录");
        }
        return body;
    }

}
