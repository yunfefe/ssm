package com.ly.controller;

import com.ly.bean.Information;
import com.ly.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@SessionAttributes("list")
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping("/book/addbook")
    public String addbook(Information information, MultipartFile myfile,HttpServletRequest request) {
        String path=request.getRealPath("/uploadimage");//要保存文件的文件夹
        try {
            myfile.transferTo(new File(path+"/"+myfile.getOriginalFilename()));//复制
        } catch (IOException e) {
            e.printStackTrace();
        }
        request.setAttribute("filename",myfile.getOriginalFilename());//
        bookService.insertSelective(information);
        return "redirect:/book/getbooklist";
    }


    @RequestMapping("/book/getbooklist")
    public String getbooklist(Information information,
                              ModelMap map) {
        System.out.println(information.getTypeid());
        List list = bookService.getbooklist(information);
        map.put("list",list);
        map.put("informationname",information.getInformationname());
        return "/book/list-ziliao";
    }
}
