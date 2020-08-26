package com.vilce.springboot_vue.service;

import com.vilce.springboot_vue.model.po.Book;
import com.vilce.springboot_vue.model.vo.respones.BookRes;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Description: 图书相关服务
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.service.BookService
 * @Author: 雷才哲
 * @Date: 2019/12/23 11:27
 * @Version: 1.0
 */
public interface BookService {

    /**
     * 获取所有图书
     *
     * @return
     */
    List<BookRes> getAllBooks();

    /**
     * 添加或更新图书信息
     *
     * @param book
     * @return
     */
    boolean addOrUpdateBooks(Book book);

    /**
     * 根据id删除图书信息
     *
     * @param id
     * @return
     */
    boolean deleteBookById(int id);


    /**
     * 条件搜索图书信息
     *
     * @param keywords
     * @return
     */
    List<BookRes> searchBooks(String keywords);

    /**
     * 根据分类获取图书信息
     *
     * @param cid
     * @return
     */
    List<BookRes> listBooksByCategory(int cid);

    /**
     * 上传图书封面图片
     *
     * @param file
     * @return
     */
    String coversUpload(MultipartFile file);
}
