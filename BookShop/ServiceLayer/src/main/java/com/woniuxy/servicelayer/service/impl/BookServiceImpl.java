package com.woniuxy.servicelayer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniuxy.dal.entity.Book;
import com.woniuxy.dal.mapper.BookMapper;
import com.woniuxy.servicelayer.service.BookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author woniumrwang
 * @since 2023-04-26 11:40:03
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {

    @Autowired
    BookMapper bookMapper;

    @Override
    public List<Book> topN(Integer n) {

        //分页条件
        Page<Book> page = new Page<>(1,n);

        //查询条件
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("buycount");

        Page<Book> pageResult = bookMapper.selectPage(page, queryWrapper);
        return pageResult.getRecords();
    }

    @Override
    public Page<Book> pageOfType(Long current, Long size, Long typeId) {

        //分页条件
        Page<Book> page = new Page<>(current,size);

        //查询条件
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("typeId",typeId);

        Page<Book> pageResult = bookMapper.selectPage(page, queryWrapper);
        return pageResult;
    }


}
