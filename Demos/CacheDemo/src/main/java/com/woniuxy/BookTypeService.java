package com.woniuxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookTypeService {

    @Autowired
    BooktypeMapper booktypeMapper;

    private List<Booktype> booktypes;

    public List<Booktype> getAll(){
        if (booktypes==null) {
            System.out.println("从数据库查询");
            booktypes = booktypeMapper.selectList(null);
        }else {
            System.out.println("直接返回结果");
        }
        return booktypes;
    }

    public Booktype getById(Integer typeId){
        Booktype booktype = booktypeMapper.selectById(typeId);
        return booktype;
    }

    public void add(Booktype booktype){
        booktypes = null;  //解决缓存一致性问题
        int insert = booktypeMapper.insert(booktype);
    }

    public void deleteById(Integer typeId){
        booktypes = null;  //解决缓存一致性问题
        int i = booktypeMapper.deleteById(typeId);
    }

    public void updateById(Booktype booktype){
        booktypes = null;  //解决缓存一致性问题
        int i = booktypeMapper.updateById(booktype);
    }


}
