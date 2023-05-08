package com.woniuxy.qiantai.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniuxy.dal.entity.Address;
import com.woniuxy.dal.entity.China;
import com.woniuxy.dal.entity.User;
import com.woniuxy.qiantai.utils.CookieUtils;
import com.woniuxy.servicelayer.service.AddressService;
import com.woniuxy.servicelayer.service.ChinaService;
import com.woniuxy.servicelayer.service.UserService;
import com.woniuxy.servicelayer.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping("/address")
public class AddressController {

    @Autowired
    ChinaService chinaService;

    @Autowired
    AddressService addressService;

    @Autowired
    UserService userService;

    @PostMapping("queryByPid")
    public List<China> queryByPid(Integer Pid){

        QueryWrapper<China> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("Pid",Pid);

        return chinaService.list(queryWrapper);
    }

    @PostMapping("add")
    public String add(@RequestBody Address address, HttpServletRequest request){

        User currentUser = getCurrentUser(request);

        addressService.addAddress(currentUser.getId(),address);

        return "ok";
    }

    @RequestMapping("list")
    public List<Address> list(HttpServletRequest request){
        User currentUser = getCurrentUser(request);

        QueryWrapper<Address> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userId",currentUser.getId());

        return addressService.list(queryWrapper);
    }


    private User getCurrentUser(HttpServletRequest request){

        String userTokenFromCookie = CookieUtils.getUserTokenFromCookie(request);
        String account = JwtUtils.getAccount(userTokenFromCookie);
        User currentUser = userService.getUserByName(account);

        return currentUser;
    }




}

