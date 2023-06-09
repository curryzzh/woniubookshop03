package com.woniuxy.dal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author woniumrwang
 * @since 2023-04-26 11:40:03
 */
@Getter
@Setter
@TableName("t_manager_role")
public class ManagerRole {

    /**
     * 自增主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 员工id
     */
    @TableField("manager_id")
    private Long managerId;

    /**
     * 角色id
     */
    @TableField("role_id")
    private Long roleId;

    /**
     * 创建时间
     */
    @TableField("insert_time")
    private Date insertTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;


}
