package com.vilce.springboot_vue.dao;

import com.vilce.springboot_vue.model.po.Book;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.dao.BookMapper
 * @Author: 雷才哲
 * @Date: 2019/12/23 13:27
 * @Version: 1.0
 */
@Component
public interface BookMapper{
    List<Book> getAllBooks();
    boolean addBook(Book book);
    boolean updateBook(Book book);
    boolean deleteBookById(Long eid);
    List<Book> getBooksByCategory(Long cid);
}
