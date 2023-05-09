package com.woniuxy.qiantai.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniuxy.dal.entity.OrderItem;
import com.woniuxy.qiantai.vo.OrderItemVO;
import com.woniuxy.servicelayer.service.BookService;
import com.woniuxy.servicelayer.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
@RequestMapping("/orderItem")
public class OrderItemController {
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    BookService bookService;

    @RequestMapping("/queryByOid")
    public List<OrderItemVO> queryByOid(Long oid) {
        List<OrderItem> orderItems = orderItemService.list(new QueryWrapper<OrderItem>().eq("orderId", oid));
        List<OrderItemVO> orderItemVOList = new ArrayList<>();
        for (int i = 0; i < orderItems.size(); i++) {
            OrderItemVO orderItemVO = new OrderItemVO();
            orderItemVO.setBookName(orderItems.get(i).getBookName());
            orderItemVO.setImgsrc(bookService.getById(orderItems.get(i).getBookId()).getImgsrc());
            orderItemVO.setBcount(orderItems.get(i).getBcount());
            orderItemVO.setSumprice(orderItems.get(i).getSumprice());
            orderItemVOList.add(orderItemVO);
        }
        return orderItemVOList;
    }

}

