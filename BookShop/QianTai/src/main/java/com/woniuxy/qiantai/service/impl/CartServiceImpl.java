package com.woniuxy.qiantai.service.impl;

import com.woniuxy.dal.entity.Book;
import com.woniuxy.dal.mapper.BookMapper;
import com.woniuxy.qiantai.service.CartService;
import com.woniuxy.servicelayer.vo.CartItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    RedisTemplate<String,Object> stringObjectRedisTemplate;

    @Autowired
    BookMapper bookMapper;

    @Override
    public void addBook(Long bookId, Long currentUserId) {
        HashOperations<String, Object, Object> opsForHash = stringObjectRedisTemplate.opsForHash();
        //如果购物这种还没有该商品直接写入新数据
        //如果购物车中已经加购过,那就只需要更新计数和小计

        Book book = bookMapper.selectById(bookId);

        CartItemVO cartItem = (CartItemVO)opsForHash.get(currentUserId.toString(), bookId.toString());
        if (cartItem == null){
            CartItemVO cartItemVO = new CartItemVO();
            cartItemVO.setBookId(book.getId());
            cartItemVO.setBookName(book.getName());
            cartItemVO.setBookPrice(book.getPrice());
            cartItemVO.setBuyCount(1);
            cartItemVO.setBookImgSrc(book.getImgsrc());
            cartItemVO.setSumPrice(book.getPrice());

            opsForHash.put(currentUserId.toString(), bookId.toString(),cartItemVO);
        }else {
            cartItem.setBuyCount( cartItem.getBuyCount()+1 );
            cartItem.setSumPrice( cartItem.getSumPrice().add(cartItem.getBookPrice()) );

            opsForHash.put(currentUserId.toString(), bookId.toString(),cartItem);
        }

    }

}
