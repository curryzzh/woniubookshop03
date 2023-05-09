package com.woniuxy.servicelayer.service;

import com.woniuxy.dal.entity.Address;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author woniumrwang
 * @since 2023-04-26 11:40:03
 */
public interface AddressService extends IService<Address> {

    void addAddress(Long currentUserId, Address address);

}
