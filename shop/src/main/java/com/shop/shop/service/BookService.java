package com.shop.shop.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.shop.entity.Book;
import com.shop.shop.mapper.BookMapper;
import org.springframework.stereotype.Service;

/**
 * 图书业务层
 */
@Service
public class BookService extends ServiceImpl<BookMapper, Book> {
}
