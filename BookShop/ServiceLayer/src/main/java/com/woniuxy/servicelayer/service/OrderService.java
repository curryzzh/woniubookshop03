package com.woniuxy.servicelayer.service;

import com.woniuxy.dal.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.woniuxy.servicelayer.vo.CartItemVO;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author woniumrwang
 * @since 2023-04-26 11:40:03
 */
public interface OrderService extends IService<Order> {

    String createOrder(Long currentUserId, Long addressId, List<CartItemVO> cartItemOrderList, BigDecimal totalPrice);
}
