package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 模板
 *
 * @author 池灿淼
 * @date 21:29
 */
@Controller
public class PublishController {
    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }
}
