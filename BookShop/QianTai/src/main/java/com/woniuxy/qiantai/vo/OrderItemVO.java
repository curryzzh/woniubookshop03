package com.woniuxy.qiantai.vo;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class OrderItemVO {
    private String imgsrc;
    private String bookName;
    private Integer bcount;
    private BigDecimal sumprice;
}