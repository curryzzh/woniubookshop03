package com.woniuxy.dal.mapper;

import com.woniuxy.dal.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author woniumrwang
 * @since 2023-04-26 11:40:03
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
