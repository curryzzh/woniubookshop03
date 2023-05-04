package com.woniuxy;

//import com.woniuxy.qiantai.controller.BooktypeController;
import com.woniuxy.servicelayer.service.BooktypeService;
import com.woniuxy.servicelayer.util.JwtUtils;
import io.jsonwebtoken.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Date;

@SpringBootTest
public class QianTaiMainTest {

    @Autowired
    BooktypeService booktypeService;
//
//    @Autowired
//    BooktypeController booktypeController;

    @Test
    void testGeneratorCode(){
        System.out.println( booktypeService.list() );
    }


    @Autowired
    JavaMailSender  javaMailSender;

    @Test
    void testEmail(){

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("woniumrwang@qq.com");
        message.setTo("wangnimvp@163.com");
        message.setSubject("主题就是我");
        message.setText("我是邮件的具体内容"+new Date());

        javaMailSender.send(message);
    }

    private String secret = "kasdjfklsadlf!@#$@$%#@%$@$#97193284";

    @Test
    void testJwtEncode(){

        //获取建造器
        JwtBuilder jwtBuilder = Jwts.builder();

        //头部Header
        jwtBuilder.setHeaderParam("alg","HS256");
        jwtBuilder.setHeaderParam("typ","JWT");


        //载荷Payload
        jwtBuilder.setIssuer("蜗牛书店");
        jwtBuilder.setSubject("令牌");
        jwtBuilder.claim("name","woniu");
        jwtBuilder.claim("age",18);
          //设置过期时间
        long time = new Date().getTime() + 250 * 1000;
        jwtBuilder.setExpiration(new Date(time));


        //签名Signature
        jwtBuilder.signWith(SignatureAlgorithm.HS256,secret);


        System.out.println( jwtBuilder.compact() );

    }


    @Test
    void testParse(){
        String token ="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiLonJfniZvkuablupciLCJzdWIiOiLku6TniYwiLCJuYW1lIjoid29uaXUiLCJhZ2UiOjE4LCJleHAiOjE2ODMxOTMxNzR9.FYhc8LdvWAxrsKVd0SMrHFeRvPceb8A7KwPpM-593Lo";

        JwtParser jwtParser = Jwts.parser();

        Jws<Claims> claimsJws = jwtParser.setSigningKey(secret).parseClaimsJws(token);

        System.out.println(claimsJws.getHeader());
        System.out.println(claimsJws.getBody());
        System.out.println(claimsJws.getSignature());

        Claims body = claimsJws.getBody();

        System.out.println(body.getIssuer());
        System.out.println(body.getSubject());
        System.out.println(body.get("name"));
        System.out.println(body.get("age"));


    }

    @Test
    void testJwtUtils(){
        String token = JwtUtils.createToken("woniu03", 2);
        System.out.println(token);

        String account = JwtUtils.getAccount(token);
        System.out.println(account);
    }


}
