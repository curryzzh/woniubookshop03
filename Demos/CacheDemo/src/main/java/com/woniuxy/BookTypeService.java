package com.woniuxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookTypeService {

    @Autowired
    BooktypeMapper booktypeMapper;

    public List<Booktype> getAll(){
        List<Booktype> booktypes = booktypeMapper.selectList(null);
        return booktypes;
    }

    public Booktype getById(Integer typeId){
        Booktype booktype = booktypeMapper.selectById(typeId);
        return booktype;
    }

    public void add(Booktype booktype){
        int insert = booktypeMapper.insert(booktype);
    }

    public void deleteById(Integer typeId){
        int i = booktypeMapper.deleteById(typeId);
    }

    public void updateById(Booktype booktype){
        int i = booktypeMapper.updateById(booktype);
    }


}
