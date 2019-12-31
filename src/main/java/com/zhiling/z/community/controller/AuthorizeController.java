package com.zhiling.z.community.controller;

import com.zhiling.z.community.controller.exception.CustomizeException;
import com.zhiling.z.community.dto.AccessTokenDTO;
import com.zhiling.z.community.model.User;
import com.zhiling.z.community.provider.GitHubProvider;
import com.zhiling.z.community.dto.GitHubUser;
import com.zhiling.z.community.service.UserService;
import com.zhiling.z.community.utils.UserServiceUtil;
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

    @Value("${cookie.maxAge}")
    private Long maxAge;

    private GitHubProvider gitHubProvider;
    private UserService userService = UserServiceUtil.getUserService();

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
            int war = 0;
            //登录成功
            //判断是否存在AccountId
            User user = userService.getUserByAccountId(String.valueOf(gitHubUser.getId()));
            if (user == null){
                user = new User();
                //生成唯一识别码
                user.setToken(UUID.randomUUID().toString());
                user.setName(gitHubUser.getName());
                user.setAccountId(String.valueOf(gitHubUser.getId()));
                user.setGmtCreate(System.currentTimeMillis());
                user.setGmtModify(user.getGmtCreate());
                user.setAvatarUrl(gitHubUser.getAvatarUrl());
                user.setUserName(user.getToken());
                user.setBio(gitHubUser.getBio());
                war = userService.insertUser(user);
            }
            if (war >0){
                //创建cookie
                Cookie communityToken = new Cookie("communityToken", user.getToken());
                //设置cookie过期时间
                communityToken.setMaxAge(Math.toIntExact(maxAge));
                response.addCookie(communityToken);
                return "redirect:/index";
            }else {
                //登录失败，重新登录
                throw new CustomizeException("系统异常，稍后再试");
            }
        }else {
            //登录失败，重新登录
            throw new CustomizeException("连接超时，稍后再试");
        }
    }

}
