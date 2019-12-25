package com.vilce.springboot_vue.service.Impl;

import com.vilce.springboot_vue.dao.BookMapper;
import com.vilce.springboot_vue.model.po.Book;
import com.vilce.springboot_vue.model.vo.respones.BookRes;
import com.vilce.springboot_vue.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.service.Impl.BookServiceImpl
 * @Author: 雷才哲
 * @Date: 2019/12/23 14:08
 * @Version: 1.0
 */
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;

    @Override
    public List<BookRes> getAllBooks() {
        List<Book> bookList =  bookMapper.getAllBooks();
        List<BookRes> bookResList = new ArrayList<>();
        for (Book book:bookList){
            BookRes bookRes = BookRes.create(book);
            bookResList.add(bookRes);
        }
        return bookResList;
    }

    @Override
    public boolean addBook(Book book) {
        return bookMapper.addBook(book);
    }

    @Override
    public boolean updateBook(Book book) {
        return bookMapper.updateBook(book);
    }

    @Override
    public boolean deleteBookById(Long eid) {
        return bookMapper.deleteBookById(eid);
    }

    @Override
    public List<BookRes> getBooksByCategory(Long cid) {
        List<Book> bookList =  bookMapper.getBooksByCategory(cid);
        List<BookRes> bookResList = new ArrayList<>();
        for (Book book:bookList){
            BookRes bookRes = BookRes.create(book);
            bookResList.add(bookRes);
        }
        return bookResList;
    }
}
