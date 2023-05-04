package com.woniuxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookTypeService {

    @Autowired
    BooktypeMapper booktypeMapper;

//    private List<Booktype> booktypes;

    @Autowired
    RedisTemplate<String,Object> stringObjectRedisTemplate;


    public List<Booktype> getAll(){

        ValueOperations<String, Object> opsForValue = stringObjectRedisTemplate.opsForValue();
        Object booktypesDemo = opsForValue.get("booktypesDemo");
        List<Booktype> booktypesList = null;

        if (booktypesDemo==null) {
            System.out.println("从数据库查询");
            booktypesList = booktypeMapper.selectList(null);
            opsForValue.set("booktypesDemo",booktypesList);
        }else {
            System.out.println("直接返回结果");
            booktypesList = (List<Booktype>)booktypesDemo;
        }

        return booktypesList;
    }

    public Booktype getById(Integer typeId){
        Booktype booktype = booktypeMapper.selectById(typeId);
        return booktype;
    }

    public void add(Booktype booktype){
        //booktypes = null;  //解决缓存一致性问题
        stringObjectRedisTemplate.delete("booktypesDemo");
        int insert = booktypeMapper.insert(booktype);
    }

    public void deleteById(Integer typeId){
//        booktypes = null;  //解决缓存一致性问题
        stringObjectRedisTemplate.delete("booktypesDemo");
        int i = booktypeMapper.deleteById(typeId);
    }

    public void updateById(Booktype booktype){
//        booktypes = null;  //解决缓存一致性问题
        stringObjectRedisTemplate.delete("booktypesDemo");
        int i = booktypeMapper.updateById(booktype);
    }


}
