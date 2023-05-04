package com.woniuxy.qiantai.controller;


import com.woniuxy.dal.entity.Booktype;
import com.woniuxy.servicelayer.service.BooktypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author woniumrwang
 * @since 2023-04-26 11:40:03
 */
@RestController
@RequestMapping("/booktype")
public class BooktypeController {

    @Autowired
    BooktypeService booktypeService;

    @Autowired
    RedisTemplate<String,Object> stringObjectRedisTemplate;


    /**
     * 查询所有图书类型
     *
     * @return
     */
    @RequestMapping("all")
    public List<Booktype> all(){

        ValueOperations<String, Object> opsForValue = stringObjectRedisTemplate.opsForValue();
        Object redisValue = opsForValue.get("shopBookTypeList");
        List<Booktype> booktypeList = null;

        //根据缓存中是否有数据做不同操作
        if (redisValue == null){ //缓存中还没有数据
            booktypeList = booktypeService.list();
            opsForValue.set("shopBookTypeList",booktypeList);
        }else {
            booktypeList = (List<Booktype>)redisValue;
        }

        return booktypeList;
    }



}

