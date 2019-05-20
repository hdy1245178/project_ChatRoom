package com.hdy.base;

import com.sun.org.apache.xml.internal.security.algorithms.SignatureAlgorithm;

import java.util.Date;

public class Test {

    public static void creatJWT(){
        //链式编程->函数式编程
        Date date = new Date();
        long since = date.getTime();
        since = since + 20*1000 ;

        Date expireDate = new Date(since);

        JwtBuilder builder = Jwts.builder().setId("111").setSubject("my subject").setIssuedAt(date)
                //a.算法   b.自定义：盐
                .signWith(SignatureAlgorithm.HS256,"yq".getBytes()).setExpiration(     expireDate  )
                .claim("roles","admin") ;//自定义属性
        System.out.println(builder.compact() );
    }

    public static void parseJwt(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMTEiLCJzdWIiOiJteSBzdWJqZWN0IiwiaWF0IjoxNTU1OTE1NjAzLCJleHAiOjE1NTU5MTU2MjMsInJvbGVzIjoiYWRtaW4ifQ.x9dqXh1XfduuV3maeE1Am70TMcwX9712_3kJEY_RXKU" ;
        try {
            Claims claims = Jwts.parser().setSigningKey("yq".getBytes()).parseClaimsJws(token).getBody();
            System.out.println(claims.getId());
            System.out.println(claims.getSubject());
            System.out.println(claims.getIssuedAt());
            System.out.println(claims.get("roles"));
        }catch(ExpiredJwtException e){
            System.out.println("超时...");
        }catch(Exception e){
            e.printStackTrace();
        }

    }


    //    "2019-02-13 24:24:21.123"
    public static void main(String[] args) {
//        creatJWT() ;
        parseJwt();
    }
}
