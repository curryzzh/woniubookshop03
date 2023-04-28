package com.woniuxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
public class BookTypeController {

    @Autowired
    BookTypeService bookTypeService;

    @RequestMapping("/")
    public String root(){
        return "types";
    }

    @RequestMapping("all")
    @ResponseBody
    public List<Booktype> all(){
        long start = System.currentTimeMillis();
        List<Booktype> types = bookTypeService.getAll();
        long end = System.currentTimeMillis();
        System.out.println("bookTypeService.getAll 耗时 "+(end-start)+" ms");
        return types;
    }

    @RequestMapping("/add")
    public String add(){
        return "add";
    }

    @RequestMapping("doAdd")
    public String doAdd(String name){
        Booktype booktype = new Booktype();
        booktype.setName(name);
        booktype.setCreatetime(new Date());
        booktype.setUpdatetime(new Date());

        bookTypeService.add(booktype);

        return "redirect:/";
    }


    @RequestMapping("/update")
    public String update(Integer typeId,Model model){
        Booktype type = bookTypeService.getById(typeId);
        model.addAttribute("type",type);

        return "update";
    }

    @RequestMapping("doUpdate")
    public String doAdd(Integer id,String name){
        Booktype booktype = bookTypeService.getById(id);
        booktype.setName(name);
        booktype.setUpdatetime(new Date());

        bookTypeService.updateById(booktype);

        return "redirect:/";
    }

    @RequestMapping("delete")
    public String doAdd(Integer typeId){
        bookTypeService.deleteById(typeId);

        return "redirect:/";
    }







}
