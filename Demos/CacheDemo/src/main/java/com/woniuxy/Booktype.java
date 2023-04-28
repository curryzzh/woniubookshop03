package com.woniuxy;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author woniumrwang
 * @since 2022-12-27 05:21:30
 */
@Getter
@Setter
@TableName("t_booktype")
public class Booktype implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("name")
    private String name;

    @TableField("createtime")
    private Date createtime;

    @TableField("updatetime")
    private Date updatetime;

}
