package com.woniuxy.qiantai.config;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class KaptchaConfig {

    @Bean
    public Producer kaptchaProducer() {

        Properties properties = new Properties();
        //字体颜色
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_COLOR, "black");
        //验证码宽 110px
        properties.setProperty(Constants.KAPTCHA_IMAGE_WIDTH, "110");
        //验证码高 40px
        properties.setProperty(Constants.KAPTCHA_IMAGE_HEIGHT, "40");
        //字体大小
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_SIZE, "30");
        //生成验证字符的长度
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, "4");
        //字体
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_NAMES, "宋体,楷体,微软雅黑");
        //验证码干扰项
        properties.setProperty(Constants.KAPTCHA_NOISE_IMPL,"com.google.code.kaptcha.impl.NoNoise");
        //验证字符选项 0-9及A-Z的组合 【可根据具体的情况，进行设置，汉字也同样可以作为验证符选项】
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_STRING,"abcdefghjk23456789ABCDEFGHGKLMNPQRSTUVWSYZ");

        Config config = new Config(properties);

        DefaultKaptcha captchaProducer = new DefaultKaptcha();
        captchaProducer.setConfig(config);

        return captchaProducer;
    }
}

