package com.woniuxy.servicelayer.util;

import io.jsonwebtoken.*;

import java.util.Date;

public class JwtUtils {

    private static String secret = "woniuQAZwsasfHOHOOHUsaf*&^%$#@xERTYGV&*^%IJNLkhjg";

    public static String createToken(String account,int minutes){
        JwtBuilder jwtBuilder = Jwts.builder();

        //设置 头部
        jwtBuilder.setHeaderParam("alg","HS256");
        jwtBuilder.setHeaderParam("typ","JWT");

        //设置载荷
        jwtBuilder.setIssuer("woniuhz");
        jwtBuilder.setSubject("蜗牛书店");
        long expire = minutes*60*1000;
        jwtBuilder.setExpiration(new Date(new Date().getTime()+expire));
        //自定义信息
        jwtBuilder.claim("account",account);

        //设置 签名
        jwtBuilder.signWith(SignatureAlgorithm.HS256,secret);

        //生成token
        String token = jwtBuilder.compact();

        return token ;
    }

    public static Claims parseClaims(String token){
        JwtParser parser = Jwts.parser();
        Jws<Claims> claimsJws = parser.setSigningKey(secret).parseClaimsJws(token);
        Claims body = claimsJws.getBody();
        return body ;
    }

    /**
     * 返回用户名
     * @param token
     * @return
     */
    public static String getAccount(String token){
        Claims body = parseClaims(token);
        return  (String) body.get("account");
    }

    /**
     * 返回用户名,只关心能不能正常解析
     * @param token
     * @return
     */
    public static String getAccountWithoutException(String token){
        String account="";

        try {
            account=getAccount(token);
        }catch (Exception e){
            //e.printStackTrace();
            //log......
        }
        return account;
    }

}

