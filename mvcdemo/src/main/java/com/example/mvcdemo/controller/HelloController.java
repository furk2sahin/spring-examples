package com.example.mvcdemo.controller;

import com.example.mvcdemo.model.Message;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return "Hello Spring MVC";
    }

    @RequestMapping("/hellojsp")
    public ModelAndView getMessage(){
        ModelAndView mav = new ModelAndView();
        Message message = new Message();
        message.setMessage("Hello Spring MVC -> JSP");
        mav.addObject("message", message.getMessage());
        mav.setViewName("hellojsp");
        return mav;
    }
}
