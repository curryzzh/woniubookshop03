package com.woniuxy.servicelayer.service;

import com.woniuxy.dal.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author woniumrwang
 * @since 2023-04-26 11:40:03
 */
public interface UserService extends IService<User> {

    User getUserByName(String username);
}
