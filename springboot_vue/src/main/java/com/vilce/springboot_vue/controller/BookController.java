package com.vilce.springboot_vue.controller;

import com.vilce.springboot_vue.model.po.Book;
import com.vilce.springboot_vue.model.vo.request.BookReq;
import com.vilce.springboot_vue.model.vo.respones.BookRes;
import com.vilce.springboot_vue.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.springboot_vue.controller.BookController
 * @Author: 雷才哲
 * @Date: 2019/12/23 14:13
 * @Version: 1.0
 */
@RestController
@RequestMapping("/book")
@Api(tags = "书API")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("getAllBooks")
    @ApiOperation("获取所有书籍")
    public List<BookRes> getAllBooks(){
        return bookService.getAllBooks();
    }

    @PostMapping("addOrUpdateBook")
    @ApiOperation("添加书籍")
    public boolean addOrUpdateBook(BookReq book){
        return bookService.addOrUpdateBook(book);
    }

    @PostMapping("deleteBookById")
    @ApiOperation("删除书籍")
    public boolean deleteBookById(Long eid){
        return bookService.deleteBookById(eid);
    }

    @GetMapping("getBookByNameOrAuthor")
    @ApiOperation("根据作者名或书名搜索书")
    public List<BookRes> getBookByNameOrAuthor(String str){
        return bookService.getBookByNameOrAuthor(str);
    }

    @GetMapping("getBooksByCategory")
    @ApiOperation("根据分类查询书籍")
    public List<BookRes> getBooksByCategory(Long cid){
        return bookService.getBooksByCategory(cid);
    }
}
