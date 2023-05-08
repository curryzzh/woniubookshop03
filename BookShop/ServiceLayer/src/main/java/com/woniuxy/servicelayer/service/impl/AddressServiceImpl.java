package com.woniuxy.servicelayer.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.woniuxy.dal.entity.Address;
import com.woniuxy.dal.mapper.AddressMapper;
import com.woniuxy.servicelayer.service.AddressService;
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
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {

    @Autowired
    AddressMapper addressMapper;

    @Override
    public void addAddress(Long currentUserId, Address address) {
        address.setUserId(currentUserId);

        if (address.getIsDefault().equals("1")){
            Address updateAddress = new Address();
            updateAddress.setIsDefault("0");

            UpdateWrapper<Address> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("userId",currentUserId);

            addressMapper.update(updateAddress,updateWrapper);
        }

        addressMapper.insert(address);

    }
}
