package com.example.demo.controller;

import com.example.demo.dto.AccessTokenDTO;
import com.example.demo.dto.GithubUser;
import com.example.demo.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Classname AuthorizeController
 * @Description TODO
 * @Date 2019/6/9 10:10
 * @Created by hp
 */
@Controller
public class AuthorizeController {

    @Autowired              //自动把spring容器内的对象的实例加载到当前使用的上下文
    private GithubProvider githubProvider;


    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state) {
        AccessTokenDTO accessTokenDto = new AccessTokenDTO();
        accessTokenDto.setClient_id("9506e58c24ec6462f20f");
        accessTokenDto.setClient_secret("84dfb4b288ae06662844fa87bbdb359fd9434edc");
        accessTokenDto.setCode(code);
        accessTokenDto.setRedirect_uri("http://localhost:8887/callback");
        accessTokenDto.setState(state);
        String accessTolen = githubProvider.getAccessTolen(accessTokenDto);
        GithubUser user = githubProvider.getUser(accessTolen);
        System.out.println(user.getName());
        return "index";
    }
}
