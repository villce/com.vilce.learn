package com.vilce.springboot_vue.mapper;

import com.vilce.springboot_vue.model.po.Book;
import com.vilce.springboot_vue.model.po.Category;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description: 图书相关数据Mapper
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.mapper.BookMapper
 * @Author: 雷才哲
 * @Date: 2020/8/26 16:36
 * @Version: 1.0
 */
@Component
public interface BookMapper {

    /**
     * 获取所有图书
     *
     * @return
     */
    List<Book> getAllBooks();

    /**
     * 添加图书
     *
     * @param book
     * @return
     */
    boolean addBook(Book book);

    /**
     * 更新图书信息
     *
     * @param book
     * @return
     */
    boolean updateBook(Book book);

    /**
     * 根据id删除图书
     *
     * @param eid
     * @return
     */
    boolean deleteBookById(int eid);

    /**
     * 根据分类id获取图书
     *
     * @param cid
     * @return
     */
    List<Book> getBooksByCategory(int cid);

    /**
     * 根据图书名和作者获取图书
     *
     * @param book
     * @return
     */
    Book getBookByNameAndAuthor(Book book);

    /**
     * 根据分类id获取分类信息
     *
     * @param cid
     * @return
     */
    Category getCategory(int cid);

    /**
     * 模糊查询图书
     *
     * @param keywords
     * @return
     */
    List<Book> searchBooks(String keywords);
}
