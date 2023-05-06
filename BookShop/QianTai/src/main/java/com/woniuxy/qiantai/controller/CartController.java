package com.woniuxy.qiantai.controller;


import com.woniuxy.dal.entity.User;
import com.woniuxy.qiantai.service.CartService;
import com.woniuxy.qiantai.utils.CookieUtils;
import com.woniuxy.servicelayer.service.UserService;
import com.woniuxy.servicelayer.util.JwtUtils;
import com.woniuxy.servicelayer.vo.CartItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @Autowired
    UserService userService;


    @RequestMapping("addBook")
    public String addBook(Long bookId, HttpServletRequest request){

        cartService.addBook(bookId,getCurrentUser(request).getId());

        return "加购成功";
    }

    @RequestMapping("cartItemList")
    public List<CartItemVO> cartItemList(HttpServletRequest request){
        return cartService.cartItemList(getCurrentUser(request).getId());
    }


    @RequestMapping("freshBuycount")
    public CartItemVO freshBuycount(Long bookId, Integer buyCount, HttpServletRequest request){
        User currentUser = getCurrentUser(request);

        return cartService.freshBuycount(currentUser.getId(),bookId,buyCount);
    }



    private User getCurrentUser(HttpServletRequest request){

        String userTokenFromCookie = CookieUtils.getUserTokenFromCookie(request);
        String account = JwtUtils.getAccount(userTokenFromCookie);
        User currentUser = userService.getUserByName(account);

        return currentUser;
    }


}

