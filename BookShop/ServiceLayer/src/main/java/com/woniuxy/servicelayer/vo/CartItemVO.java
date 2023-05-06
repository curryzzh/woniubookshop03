package com.woniuxy.servicelayer.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemVO {

    private Long bookId;
    private String bookName;
    private String bookImgSrc;
    private BigDecimal bookPrice;
    private Integer buyCount;
    private BigDecimal sumPrice;


}
