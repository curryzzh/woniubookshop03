package com.woniuxy.qiantai.interceptors;

import com.woniuxy.qiantai.utils.CookieUtils;
import com.woniuxy.servicelayer.util.JwtUtils;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    StringRedisTemplate stringRedisTemplate;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        return HandlerInterceptor.super.preHandle(request, response, handler);

        //先从request获取到userToken
        String userTokenFromCookie = CookieUtils.getUserTokenFromCookie(request);

        //解析userToken;
        try {
            JwtUtils.getAccount(userTokenFromCookie);
        }catch (ExpiredJwtException expiredJwtException){
            //如果遇到过期异常则判断是否需要续期;  其它异常忽略
            ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();

            String accountFromRedis = opsForValue.get(userTokenFromCookie);
            if (!StringUtils.isEmpty(accountFromRedis)){   //判断关联数据是否过期, 只有在关联数据未过期时更新token和关联数据
                //做续期操作
                String freshToken = JwtUtils.createToken(accountFromRedis, 30);
                CookieUtils.setUserToken2Cookie(response,freshToken);
                //同时保存一份关联数据
                opsForValue.set(freshToken,accountFromRedis,60L, TimeUnit.MINUTES);
                //删除原有的关联数据
                stringRedisTemplate.delete(userTokenFromCookie);

                //同步更新request中的token值
                CookieUtils.refreshRequestUserToken(request,freshToken);
            }

        }catch (Exception e){
            //......
        }


        return true;
    }
}
