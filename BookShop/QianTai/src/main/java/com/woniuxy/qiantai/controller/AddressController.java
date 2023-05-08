package com.woniuxy.qiantai.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniuxy.dal.entity.China;
import com.woniuxy.servicelayer.service.ChinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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


    @PostMapping("queryByPid")
    public List<China> queryByPid(Integer Pid){

        QueryWrapper<China> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("Pid",Pid);

        return chinaService.list(queryWrapper);
    }



}

