package com.woniuxy.qiantai.service;

import com.woniuxy.servicelayer.vo.CartItemVO;

import java.util.List;

public interface CartService {
    void addBook(Long bookId, Long currentUserId);

    List<CartItemVO> cartItemList(Long currentUserId);

    CartItemVO freshBuycount(Long currentUserId, Long bookId, Integer buyCount);
}
