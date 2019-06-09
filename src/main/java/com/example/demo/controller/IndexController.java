package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author 池灿淼
 * @Date 21:55 2019/6/8
 * @Description
 **/
@Controller
public class IndexController {
    @GetMapping("/")
    public String index() { return "index"; }
}
