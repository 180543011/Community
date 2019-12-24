package com.zhiling.z.community.controller;

import com.zhiling.z.community.dto.AccessTokenDTO;
import com.zhiling.z.community.model.User;
import com.zhiling.z.community.provider.GitHubProvider;
import com.zhiling.z.community.provider.GitHubUser;
import com.zhiling.z.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 *  用于GitHub授权登录创建用户
 * @author zlhl
 */
@Controller
@PropertySource("classpath:config/accessToken.properties")
public class AuthorizeController {
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.client.uri}")
    private String clientUrl;

    private GitHubProvider gitHubProvider;
    private UserService userService;

    @Autowired
    public void setGitHubProvider(GitHubProvider gitHubProvider) {
        this.gitHubProvider = gitHubProvider;
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletResponse response){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(clientUrl);
        accessTokenDTO.setState(state);
        String accessToken = gitHubProvider.getAccessToken(accessTokenDTO);
        GitHubUser gitHubUser = gitHubProvider.getUser(accessToken);
        if (gitHubUser != null){
            //登录成功
            User user = new User();
            //生成唯一识别码
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(gitHubUser.getName());
            user.setAccountId(String.valueOf(gitHubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModify(user.getGmtCreate());
            user.setAvatarUrl(gitHubUser.getAvatarUrl());
            userService.insertUser(user);
            //创建cookie
            Cookie communityToken = new Cookie("communityToken", token);
            //设置cookie过期时间
            communityToken.setMaxAge(60*5);
            response.addCookie(communityToken);
            return "redirect:/index";
        }else {
            //登录失败，重新登录
            return "redirect:/";
        }
    }

}
