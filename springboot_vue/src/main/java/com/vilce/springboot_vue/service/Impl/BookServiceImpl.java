package com.vilce.springboot_vue.service.Impl;

import com.vilce.common.utils.JSONUtils;
import com.vilce.springboot_vue.dao.BookMapper;
import com.vilce.springboot_vue.model.po.Book;
import com.vilce.springboot_vue.model.po.Category;
import com.vilce.springboot_vue.model.vo.request.BookReq;
import com.vilce.springboot_vue.model.vo.respones.BookRes;
import com.vilce.springboot_vue.service.BookService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
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

    /**
     * 获取所有的书
     *
     * @return
     */
    @Override
    public List<BookRes> getAllBooks() {
        List<Book> bookList = bookMapper.getAllBooks();
        return coverBook(bookList);
    }

    /**
     * 更新书或者添加书
     *
     * @param req
     * @return
     */
    @Override
    public boolean addOrUpdateBook(BookReq req) {
        System.out.println(JSONUtils.toJson(req));
        // 当eid不为空时，更新书信息
        if (ObjectUtils.isNotEmpty(req.getEid())) {
            return bookMapper.updateBook(req);
        } else {
            if (getBookByNameAndAuthor(req)) {
                return false;
            }
            // 当eid为空，但书名、作者相同时
            return bookMapper.addBook(req);
        }

    }

    @Override
    public boolean deleteBookById(Long eid) {
        System.out.println(eid);
        return bookMapper.deleteBookById(eid);
    }

    @Override
    public List<BookRes> getBooksByCategory(Long cid) {
        if (cid.equals(0L)) {
            return getAllBooks();
        }
        List<Book> bookList = bookMapper.getBooksByCategory(cid);
        List<BookRes> bookResList = new ArrayList<>();
        for (Book book : bookList) {
            Category category = bookMapper.getCategory(cid);
            BookRes bookRes = BookRes.create(book, category);
            bookResList.add(bookRes);
        }
        return bookResList;
    }

    @Override
    public List<BookRes> getBookByNameOrAuthor(String str) {
        str = StringUtils.join('%', str, '%');
        List<Book> bookList = bookMapper.getBookByNameOrAuthor(str);
        return coverBook(bookList);
    }

    public boolean getBookByNameAndAuthor(BookReq book) {
        Book result = bookMapper.getBookByNameAndAuthor(book);
        if (ObjectUtils.isNotEmpty(result)) {
            // 存在相同的书名和作者时，返回true
            return true;
        }
        // 不存在该书名和作者时，返回false
        return false;
    }

    public List<BookRes> coverBook(List<Book> bookList) {
        List<BookRes> bookResList = new ArrayList<>();
        for (Book book:bookList) {
            Category category = bookMapper.getCategory(book.getCid());
            BookRes bookRes = BookRes.create(book, category);
            bookResList.add(bookRes);
        }
        return bookResList;
    }
}
