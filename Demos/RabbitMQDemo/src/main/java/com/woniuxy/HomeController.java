package com.woniuxy;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class HomeController {


    @RequestMapping("/")
    public String root(){
        return "index";
    }


    @RequestMapping("/sendMsg")
    @ResponseBody
    public String sendMsg(Integer mqType){

        System.out.println(mqType);
        Date date = new Date();
        if (mqType==1){

            return "单消费者ok "+date;
        } else if (mqType==2) {

            return "多消费者ok "+date;
        } else if (mqType==3) {

            return "Fanout 交换机-广播模式 ok "+date;
        } else if (mqType==4) {

            return "Direct 交换机-定向模式 ok "+date;
        } else if (mqType==5) {

            return "Topic 交换机-模糊匹配模式 "+date;
        }

        return "ok";
    }


}
