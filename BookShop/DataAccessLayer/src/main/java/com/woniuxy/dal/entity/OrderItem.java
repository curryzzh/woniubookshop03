package com.woniuxy.dal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

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
@TableName("t_order_item")
public class OrderItem {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("bookId")
    private Long bookId;

    @TableField("bookName")
    private String bookName;

    @TableField("price")
    private BigDecimal price;

    @TableField("bcount")
    private Integer bcount;

    @TableField("sumprice")
    private BigDecimal sumprice;

    @TableField("orderId")
    private Long orderId;

    @TableField("createtime")
    private Date createtime;


}
