package com.woniuxy.servicelayer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniuxy.dal.entity.User;
import com.woniuxy.dal.mapper.UserMapper;
import com.woniuxy.servicelayer.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author woniumrwang
 * @since 2023-04-26 11:40:03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User getUserByName(String username) {

        QueryWrapper<User>  queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account",username);

        User user = userMapper.selectOne(queryWrapper);
        return user;
    }

}
