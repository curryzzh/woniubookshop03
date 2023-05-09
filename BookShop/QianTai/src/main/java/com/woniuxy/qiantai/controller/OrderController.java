package com.woniuxy.qiantai.controller;


import com.woniuxy.dal.entity.Book;
import com.woniuxy.dal.entity.Order;
import com.woniuxy.dal.entity.OrderItem;
import com.woniuxy.dal.entity.User;
import com.woniuxy.qiantai.service.CartService;
import com.woniuxy.qiantai.utils.CookieUtils;
import com.woniuxy.servicelayer.service.BookService;
import com.woniuxy.servicelayer.service.OrderItemService;
import com.woniuxy.servicelayer.service.OrderService;
import com.woniuxy.servicelayer.service.UserService;
import com.woniuxy.servicelayer.util.JwtUtils;
import com.woniuxy.servicelayer.vo.CartItemVO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author woniumrwang
 * @since 2023-04-26 11:40:03
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    RabbitTemplate  rabbitTemplate;
    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;

    @Autowired
    CartService cartService;

    @Autowired
    BookService bookService;
    @Autowired
    OrderItemService orderItemService;

    @RequestMapping("create")
    public String create(Long addressId, @RequestParam("bookIds[]") Long[] bookIds, HttpServletRequest request) {
        User currentUser = getCurrentUser(request);
        List<CartItemVO> cartItemVOList = cartService.cartItemList(currentUser.getId());
        List<CartItemVO> cartItemOrderList = cartItemVOList.stream().filter(cartItemVO -> {
            for (Long bookId : bookIds) {
                if (cartItemVO.getBookId().equals(bookId)) {
                    return true;
                }
            }
            return false;
        }).collect(Collectors.toList());
        BigDecimal totalPrice = cartService.calculateTotalPrice(currentUser.getId(), bookIds);
        Order order;
        try {
            order = new Order();
            order.setOrderNum("WoniuBookShop_" + System.currentTimeMillis());
            order.setTotalprice(totalPrice);
            order.setUserId(currentUser.getId());
            order.setAddressId(addressId);
            order.setCreatetime(new Date());
            //订单状态 1.未支付  2.已支付  3.退款中  4.已退款  5.已取消
            order.setState(1);
            orderService.save(order);

            for (CartItemVO cartItemVO : cartItemOrderList) {
                Book book = bookService.getById(cartItemVO.getBookId());
                if (book.getStorecount() < cartItemVO.getBuyCount()) {
                    throw new RuntimeException(book.getName() + " 库存不足!");
                }
                Book bookForUpdate = new Book();
                bookForUpdate.setId(book.getId());
                bookForUpdate.setStorecount(book.getStorecount() - cartItemVO.getBuyCount());
                bookForUpdate.setBuycount(book.getBuycount() + cartItemVO.getBuyCount());
                bookService.updateById(bookForUpdate);

                OrderItem orderItem = new OrderItem();
                orderItem.setBookId(cartItemVO.getBookId());
                orderItem.setBookName(cartItemVO.getBookName());
                orderItem.setPrice(cartItemVO.getBookPrice());
                orderItem.setBcount(cartItemVO.getBuyCount());
                orderItem.setSumprice(cartItemVO.getSumPrice());
                orderItem.setOrderId(order.getId());
                orderItem.setCreatetime(new Date());
                orderItemService.save(orderItem);
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            return e.getMessage();
        }
        cartItemOrderList.forEach(cartItemVO -> {
            cartService.freshBuycount(currentUser.getId(), cartItemVO.getBookId(), 0);
        });
        rabbitTemplate.convertAndSend("order_dl_exchange", "order_dl_queue", order.getId(), message -> {
            message.getMessageProperties().setHeader("x-delay",1000*60*15);// 设置消息的过期时间为15分钟（900000毫秒）
            return message;
        });
        return "ok-" + order.getId().toString();
    }



    private User getCurrentUser(HttpServletRequest request){

        String userTokenFromCookie = CookieUtils.getUserTokenFromCookie(request);
        String account = JwtUtils.getAccount(userTokenFromCookie);
        User currentUser = userService.getUserByName(account);

        return currentUser;
    }
    @RequestMapping("queryByOid")
    public Order queryByOid(Long oid) {
        return orderService.getById(oid);
    }


}

