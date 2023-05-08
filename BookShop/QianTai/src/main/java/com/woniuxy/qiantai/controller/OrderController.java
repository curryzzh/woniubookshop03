package com.woniuxy.qiantai.controller;


import com.woniuxy.dal.entity.User;
import com.woniuxy.qiantai.service.CartService;
import com.woniuxy.qiantai.utils.CookieUtils;
import com.woniuxy.servicelayer.service.OrderService;
import com.woniuxy.servicelayer.service.UserService;
import com.woniuxy.servicelayer.util.JwtUtils;
import com.woniuxy.servicelayer.vo.CartItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
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
    OrderService orderService;

    @Autowired
    UserService userService;

    @Autowired
    CartService cartService;

    @PostMapping("create")
    public String create(Long addressId, @RequestParam("bookIds[]") Long[] bookIds, HttpServletRequest request){
        User currentUser = getCurrentUser(request);

        //要结算的购物项
        List<CartItemVO> cartItemVOList = cartService.cartItemList(currentUser.getId());
        List<CartItemVO> cartItemOrderList = cartItemVOList.stream().filter(cartItemVO -> {
            for (int i = 0; i < bookIds.length; i++) {
                if (cartItemVO.getBookId().equals(bookIds[i])) {
                    return true;
                }
            }
            return false;
        }).collect(Collectors.toList());

        //计算总价
        BigDecimal totalPrice = cartService.calculateTotalPrice(currentUser.getId(), bookIds);

        //创建订单
        try {
            orderService.createOrder(currentUser.getId(), addressId, cartItemOrderList, totalPrice);
        }catch (RuntimeException e){
            e.printStackTrace();
            return e.getMessage();
        }

        //从购物车中删除已经结算的项目
        cartItemOrderList.forEach(cartItemVO -> {
            cartService.freshBuycount(currentUser.getId(),cartItemVO.getBookId(),0);
        });


        return "ok";
    }



    private User getCurrentUser(HttpServletRequest request){

        String userTokenFromCookie = CookieUtils.getUserTokenFromCookie(request);
        String account = JwtUtils.getAccount(userTokenFromCookie);
        User currentUser = userService.getUserByName(account);

        return currentUser;
    }



}

