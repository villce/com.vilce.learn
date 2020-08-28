package com.vilce.springboot_vue.service.Impl;

import com.vilce.common.model.po.BaseResponse;
import com.vilce.springboot_vue.mapper.BookMapper;
import com.vilce.springboot_vue.model.po.Book;
import com.vilce.springboot_vue.model.po.Category;
import com.vilce.springboot_vue.model.vo.respones.BookRes;
import com.vilce.springboot_vue.service.BookService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @Description: 图书相关服务实现
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
    @Value("${covers.url}")
    private String coversUrl;

    /**
     * 获取所有的图书
     *
     * @return
     */
    @Override
    public List<BookRes> getAllBooks() {
        // 获取数据库中所有的图书
        List<Book> bookList = bookMapper.getAllBooks();
        // 对图书进行映射，获取分类后返回
        return coverBook(bookList);
    }

    /**
     * 更新书或者添加书
     *
     * @param book
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResponse addOrUpdateBooks(Book book) {
        BaseResponse baseResponse;
        if (book.getId() != 0) {
            // 当id不为空时，更新书信息
            if (bookMapper.updateBook(book)) {
                baseResponse = BaseResponse.buildResponse(0, "更新图书信息成功！");
            } else {
                baseResponse = BaseResponse.buildResponse(-1, "更新图书信息失败！");
            }
        } else {
            // 根据书名和作者获取图书信息
            Book result = bookMapper.getBookByNameAndAuthor(book);
            if (ObjectUtils.isNotEmpty(result)) {
                // 当eid为空，但书名、作者相同时，不能添加
                baseResponse = BaseResponse.buildResponse(-1, "添加图书失败，存在相同的书名和作者名");
            } else {
                // 当eid为空，但书名、作者不相同时，能添加
                if (bookMapper.addBook(book)) {
                    baseResponse = BaseResponse.buildResponse(0, "添加图书成功！");
                } else {
                    baseResponse = BaseResponse.buildResponse(-1, "添加图书失败！");
                }
            }
        }
        return baseResponse;
    }

    /**
     * 根据id删除图书信息
     *
     * @param id
     * @return
     */
    @Override
    public BaseResponse deleteBookById(int id) {
        if (bookMapper.deleteBookById(id)) {
            return BaseResponse.buildResponse(0, "删除图书成功！");
        } else {
            return BaseResponse.buildResponse(-1, "删除图书失败！");
        }
    }

    /**
     * 条件搜索图书信息
     *
     * @param keywords
     * @return
     */
    @Override
    public List<BookRes> searchBooks(String keywords) {
        // 拼凑查询模糊条件
        keywords = StringUtils.join('%', keywords, '%');
        List<Book> bookList = bookMapper.searchBooks(keywords);
        return coverBook(bookList);
    }

    /**
     * 根据分类获取图书信息
     *
     * @param cid
     * @return
     */
    @Override
    public List<BookRes> listBooksByCategory(int cid) {
        List<Book> bookList = bookMapper.getBooksByCategory(cid);
        return coverBook(bookList);
    }

    /**
     * 上传图书封面图片
     *
     * @param file
     * @return
     */
    @Override
    public String coversUpload(MultipartFile file) {
        File imageFolder = new File(coversUrl);
        File f = new File(imageFolder, DateFormatUtils.format(new Date(), "yyyy-MM-dd-hh-mm-ss") + file.getOriginalFilename()
                .substring(file.getOriginalFilename().length() - 4));
        if (!f.getParentFile().exists()) {
            f.getParentFile().mkdirs();
        }
        try {
            file.transferTo(f);
            String imgURL = "http://localhost:8006/image/file/" + f.getName();
            return imgURL;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 获取图书分类并进行映射
     *
     * @param bookList
     * @return
     */
    public List<BookRes> coverBook(List<Book> bookList) {
        List<BookRes> bookResList = new LinkedList<>();
        for (Book book : bookList) {
            Category category = bookMapper.getCategory(book.getCid());
            BookRes bookRes = BookRes.create(book, category);
            bookResList.add(bookRes);
        }
        return bookResList;
    }
}
