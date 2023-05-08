package com.woniuxy.servicelayer.service.impl;

import com.woniuxy.dal.entity.Book;
import com.woniuxy.dal.entity.Order;
import com.woniuxy.dal.entity.OrderItem;
import com.woniuxy.dal.mapper.BookMapper;
import com.woniuxy.dal.mapper.OrderItemMapper;
import com.woniuxy.dal.mapper.OrderMapper;
import com.woniuxy.servicelayer.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniuxy.servicelayer.vo.CartItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
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
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    OrderItemMapper orderItemMapper;

    @Autowired
    BookMapper bookMapper;

    @Override
    @Transactional
    public String createOrder(Long currentUserId, Long addressId, List<CartItemVO> cartItemOrderList, BigDecimal totalPrice) {

        //创建主订单
        Order order = new Order();
        order.setOrderNum( "WoniuShop03"+System.currentTimeMillis() );
        order.setTotalprice(totalPrice);
        order.setUserId(currentUserId);
        order.setAddressId(addressId);
        order.setCreatetime(new Date());
        //订单状态 1.未支付  2 . 已支付  3.退款中  4. 已退款  5.已取消
        order.setState(1);

        orderMapper.insert(order);

        //写入所有订单项信息
        for (CartItemVO cartItemVO : cartItemOrderList) {

            //维护库存量信息
            Book book = bookMapper.selectById(cartItemVO.getBookId());
            if (book.getStorecount() < cartItemVO.getBuyCount()){
                throw new RuntimeException(book.getName()+" 库存不足!");
            }

            Book bookForUpdate = new Book();
            bookForUpdate.setId(book.getId());
            bookForUpdate.setStorecount( book.getStorecount()-cartItemVO.getBuyCount() );
            bookForUpdate.setBuycount( book.getBuycount()+cartItemVO.getBuyCount() );

            bookMapper.updateById(bookForUpdate);


            //写入订单项信息
            OrderItem orderItem = new OrderItem();
            orderItem.setBookId(cartItemVO.getBookId());
            orderItem.setBookName(cartItemVO.getBookName());
            orderItem.setPrice(cartItemVO.getBookPrice());
            orderItem.setBcount(cartItemVO.getBuyCount());
            orderItem.setSumprice(cartItemVO.getSumPrice());
            orderItem.setOrderId( order.getId() );
            orderItem.setCreatetime(new Date());

            orderItemMapper.insert(orderItem);
        }

        return order.getOrderNum();

    }



}
