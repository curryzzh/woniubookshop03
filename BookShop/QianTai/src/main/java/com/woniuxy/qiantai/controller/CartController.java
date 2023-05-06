package com.woniuxy.qiantai.controller;


import com.woniuxy.dal.entity.User;
import com.woniuxy.qiantai.service.CartService;
import com.woniuxy.qiantai.utils.CookieUtils;
import com.woniuxy.servicelayer.service.UserService;
import com.woniuxy.servicelayer.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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

        String userTokenFromCookie = CookieUtils.getUserTokenFromCookie(request);
        String account = JwtUtils.getAccount(userTokenFromCookie);
        User currentUser = userService.getUserByName(account);

        cartService.addBook(bookId,currentUser.getId());

        return "加购成功";
    }



}

