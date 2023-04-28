package com.woniuxy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;
import java.util.Date;

@SpringBootTest
public class CacheDemoMainTest {

    //@Resource
    @Autowired
    RedisTemplate<Object,Object> objectObjectRedisTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Test
    void  testRedisTemplate(){

        //先获取到ops 操作对象
        ValueOperations<Object, Object> opsForValue = objectObjectRedisTemplate.opsForValue();

        //使用ops 的api实现数据的操作
        opsForValue.set("testKey","testValue");

        System.out.println( opsForValue.get("testKey") );

        opsForValue.set("testDate",new Date());
        System.out.println( opsForValue.get("testDate") );

        HashOperations<Object, Object, Object> opsForHash = objectObjectRedisTemplate.opsForHash();
        opsForHash.put("panshi","woniu","三期");
        System.out.println( opsForHash.get("panshi","woniu") );


        objectObjectRedisTemplate.delete("testKey");

    }


    @Test
    void testStringRedisTemplate(){

        //先获取到ops 操作对象
        ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();

        //使用ops 的api实现数据的操作
        opsForValue.set("testKey","testValue2");

        System.out.println( opsForValue.get("testKey") );

        opsForValue.set("testDate",new Date().toString());
        System.out.println( opsForValue.get("testDate") );


        HashOperations<String, Object, Object> opsForHash = stringRedisTemplate.opsForHash();
        opsForHash.put("panshi","woniu","三期");
        System.out.println( opsForHash.get("panshi","woniu") );

        //删除需要使用  Template 对象
        stringRedisTemplate.delete("testKey");

    }

    @Test
    void testObject(){

        //先获取到ops 操作对象
        ValueOperations<Object, Object> opsForValue = objectObjectRedisTemplate.opsForValue();

        Booktype booktype = new Booktype();
        booktype.setId(5);
        booktype.setName("历史");

        opsForValue.set("booktype",booktype);


        System.out.println( opsForValue.get("booktype") );

    }


    @Autowired
    RedisTemplate<String,Object> stringObjectRedisTemplate;

    @Test
    void testObjectJSon(){

        //先获取到ops 操作对象
        ValueOperations<String, Object> opsForValue = stringObjectRedisTemplate.opsForValue();

        Booktype booktype = new Booktype();
        booktype.setId(5);
        booktype.setName("历史");

        opsForValue.set("booktype",booktype);


        System.out.println( opsForValue.get("booktype") );

    }





}
