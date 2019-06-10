package com.example.demo.controller;

import com.example.demo.dto.AccessTokenDTO;
import com.example.demo.dto.GithubUser;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * @Classname AuthorizeController
 * @Description 供Github回调的控制类接口
 * @Date 2019/6/9 10:10
 * @Created by 池灿淼
 */
@RestController
public class AuthorizeController {

    @Autowired                                          //自动把spring容器内的对象的实例加载到当前使用的上下文
    private GithubProvider githubProvider;

    @Autowired
    private UserMapper userMapper;

    @Value("${github.client.id}")                       //自动注入配置文件中的配置
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;

    /**
     * @Author 池灿淼
     * @Date 2019/6/10 12:52
     * @Description Github回调的接口，参数问GitHub返回的code和status。
     **/
    @GetMapping("/callback")
    public void callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response) throws IOException {
        AccessTokenDTO accessTokenDto = new AccessTokenDTO();
        accessTokenDto.setClient_id(clientId);
        accessTokenDto.setClient_secret(clientSecret);
        accessTokenDto.setCode(code);
        accessTokenDto.setRedirect_uri(redirectUri);
        accessTokenDto.setState(state);
        String accessToken = githubProvider.getAccessTolen(accessTokenDto);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        if (githubUser != null) {
            //登陆成功
            User user = new User();
            String token = UUID.randomUUID().toString();          //UUID: 通用唯一辨识码，由32位十六进制数组成("20ce33b2-644f-422d-9fc5-bbea05f25e0a")
            user.setToken(token);
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setName(githubUser.getName());
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            //将用户信息写入数据库
            userMapper.insert(user);
            response.addCookie(new Cookie("token",token));

            //登陆成功后回到首页
            response.sendRedirect("/");
        } else {
            //登陆失败，重新登陆
            response.sendRedirect("/");
        }
    }
}
