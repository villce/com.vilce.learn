package com.vilce.springboot_vue.service;

import com.vilce.springboot_vue.model.po.Book;
import com.vilce.springboot_vue.model.vo.request.BookReq;
import com.vilce.springboot_vue.model.vo.respones.BookRes;

import java.util.List;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.service.BookService
 * @Author: 雷才哲
 * @Date: 2019/12/23 11:27
 * @Version: 1.0
 */
public interface BookService {
    List<BookRes> getAllBooks();

    boolean addOrUpdateBook(BookReq req);

    boolean deleteBookById(Long eid);

    List<BookRes> getBooksByCategory(Long cid);

    List<BookRes> getBookByNameOrAuthor(String str);
}
