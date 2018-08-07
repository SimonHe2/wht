package com.hjh.wht.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping("/home")
    public String index(){
        return "/view/index.html";
    }

    @RequestMapping("/")
    public String home(){
        return "/view/index.html";
    }
}
