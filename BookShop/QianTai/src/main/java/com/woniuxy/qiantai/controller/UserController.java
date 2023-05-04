package com.woniuxy.qiantai.controller;


import com.google.code.kaptcha.Producer;
import com.woniuxy.dal.entity.User;
import com.woniuxy.servicelayer.service.UserService;
import com.woniuxy.servicelayer.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author woniumrwang
 * @since 2023-04-26 11:40:03
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    UserService userService;

    @Autowired
    Producer producer;


    @RequestMapping("/requestEmailCode")
    public String requestEmailCode(String email){
        //校验邮箱地址是否合法
        String str="^" +
                "([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@" +
                "([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+" +
                "[\\.][A-Za-z]{2,3}" +
                "([\\.][A-Za-z]{2})?" +
                "$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        if(!m.matches()){
            return "邮箱地址非法,请正确填写";
        }

        //发送向邮箱发送验证码
        String Code = "0000";

        //在Redis中存储数据设置ttl,时效内有效期控制
        stringRedisTemplate.opsForValue().set(email,Code,5L, TimeUnit.MINUTES);

        sendEmail(email,"欢迎注册蜗牛书店","您的验证码是 "+Code+" ,有效期5分钟,请尽快使用");

        return "ok";
    }

    private void sendEmail(String email, String subject, String content){

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("woniumrwang@qq.com");
        message.setTo(email);
        message.setSubject(subject);
        message.setText(content);

        javaMailSender.send(message);
    }


    @RequestMapping("register")
    public String register(String username, String password, String repass, String email, String emailCode){

        if (StringUtils.isEmpty(email)){
            return "邮箱地址不能为空";
        }

        String redisEmailCode = stringRedisTemplate.opsForValue().get(email);
        if (StringUtils.isEmpty(redisEmailCode) || StringUtils.isEmpty(emailCode) || !redisEmailCode.equals(emailCode)){
            return "邮箱验证码非法";
        }

        if (StringUtils.isEmpty(password) || StringUtils.isEmpty(repass) || !password.equals(repass)){
            return "密码未填写或两次输入不一致";
        }

        User userByName = userService.getUserByName(username);
        if (userByName!=null){
            return "用户名已经被占用";
        }

        //通过校验,写入新用户
        User user = new User();
        user.setAccount(username);
//        user.setPassword(password);
        user.setPassword(Md5Util.encode(password));
        user.setEmail(email);
        user.setCreatetime(new Date());
        user.setUpdatetime(new Date());

        userService.save(user);

        return "ok";
    }


    @RequestMapping("getKaptchaCode")
    public void getKaptchaCode(HttpServletResponse response) throws IOException {

        //生成验证码
        String code = producer.createText();
        //验证码图片
        BufferedImage bufferedImage = producer.createImage(code);

        response.setContentType("image/jpeg");
        ImageIO.write(bufferedImage,"jpg",response.getOutputStream());

    }


}

