package com.woniuxy.servicelayer.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniuxy.dal.entity.Book;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author woniumrwang
 * @since 2023-04-26 11:40:03
 */
public interface BookService extends IService<Book> {

    List<Book> topN(Integer n);

    Page<Book> pageOfType(Long current, Long size, Long typeId);
}
