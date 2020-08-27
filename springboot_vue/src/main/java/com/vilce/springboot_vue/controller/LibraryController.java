package com.vilce.springboot_vue.controller;

import com.vilce.springboot_vue.model.po.Book;
import com.vilce.springboot_vue.model.vo.respones.BookRes;
import com.vilce.springboot_vue.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

/**
 * @Description: 图书馆相关API
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.controller.LibraryController
 * @Author: 雷才哲
 * @Date: 2020/8/26 16:36
 * @Version: 1.0
 */
@RestController
@RequestMapping("/library")
@Api(tags = "图书馆相关API")
public class LibraryController {

    @Autowired
    private BookService bookService;

//    @GetMapping("/api/books")
    @GetMapping("listBooks")
    @ApiOperation(value = "获取所有图书信息")
    public List<BookRes> listBooks() {
        return bookService.getAllBooks();
    }

//    @PostMapping("/api/admin/content/books")
    @PostMapping("addOrUpdateBooks")
    @ApiOperation(value = "添加或更新图书信息")
    public boolean addOrUpdateBooks(@RequestBody @Valid Book book) {
        return bookService.addOrUpdateBooks(book);
    }

//    @PostMapping("/api/admin/content/books/delete")
    @PostMapping("deleteBook")
    @ApiOperation(value = "根据id删除图书信息")
    public boolean deleteBook(@RequestBody @Valid Book book) {
        return  bookService.deleteBookById(book.getId());
    }

//    @GetMapping("/api/search")
    @GetMapping("searchBooks")
    @ApiOperation(value = "条件搜索图书信息")
    public List<BookRes> searchBooks(String keywords) {
        if (StringUtils.isEmpty(keywords)) {
            return bookService.getAllBooks();
        } else {
            return bookService.searchBooks(keywords);
        }
    }

//    @GetMapping("/api/categories/{cid}/books")
    @GetMapping("listBooksByCategory")
    @ApiOperation(value = "根据分类获取图书信息")
    public List<BookRes> listBooksByCategory(int cid) {
        if (0 != cid) {
            return bookService.listBooksByCategory(cid);
        } else {
            return bookService.getAllBooks();
        }
    }

//    @PostMapping("/api/admin/content/books/covers")
    @PostMapping("coversUpload")
    @ApiOperation(value = "上传封面图片")
    public String coversUpload(MultipartFile file) {
        return bookService.coversUpload(file);
    }

}
