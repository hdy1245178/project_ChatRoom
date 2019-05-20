package com.hdy.util;

import io.jsonwebtoken.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@ConfigurationProperties("jwt.config")
public class JwtUtil {
    private String key ;//盐 yq
    private long expire ;//过期时间
    public JwtUtil() {
    }
    public JwtUtil(String key, long expire) {
        this.key = key;
        this.expire = expire;
    }
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getExpire() {
        return expire;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }

    public  String creatJWT(String id, String subject,String roles){
        //链式编程->函数式编程
        JwtBuilder builder = null  ;

            Date date = new Date();
            builder = Jwts.builder().setId(id).setSubject(subject).setIssuedAt(date)
                    //a.算法   b.自定义：盐
                    .signWith(SignatureAlgorithm.HS256, key.getBytes()).setExpiration(new Date(expire + date.getTime()))
                    .claim("roles", roles);//自定义属性

        return builder.compact() ; //xxxxx
    }

    public Claims parseJwt(String token){
        Claims claims = null  ;
        try {
             claims = Jwts.parser().setSigningKey(key.getBytes()).parseClaimsJws(token).getBody();
        }catch(ExpiredJwtException e){
            System.out.println("超时...");
        }catch(Exception e){
            e.printStackTrace();
        }
        return claims ;
    }
}
