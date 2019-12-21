package com.zhiling.z.community.controller;

import com.zhiling.z.community.dao.UserMapper;
import com.zhiling.z.community.dto.AccessTokenDTO;
import com.zhiling.z.community.model.User;
import com.zhiling.z.community.provider.GitHubProvider;
import com.zhiling.z.community.provider.GitHubUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
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
    private UserMapper userMapper;

    @Autowired
    public void setGitHubProvider(GitHubProvider gitHubProvider) {
        this.gitHubProvider = gitHubProvider;
    }
    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request){
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
            user.setToken(UUID.randomUUID().toString());
            user.setName(gitHubUser.getName());
            user.setAccountId(String.valueOf(gitHubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModify(user.getGmtCreate());
            userMapper.insertUser(user);
            request.getSession().setAttribute("loginUser",user);
            return "redirect:/index";
        }else {
            //登录失败，重新登录
            return "redirect:/index";
        }
    }

}
