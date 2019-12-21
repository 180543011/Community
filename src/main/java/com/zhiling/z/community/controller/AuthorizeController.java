package com.zhiling.z.community.controller;

import com.zhiling.z.community.dto.AccessTokenDTO;
import com.zhiling.z.community.provider.GitHubProvider;
import com.zhiling.z.community.provider.GitHubUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @Autowired
    public void setGitHubProvider(GitHubProvider gitHubProvider) {
        this.gitHubProvider = gitHubProvider;
    }

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(clientUrl);
        accessTokenDTO.setState(state);
        String accessToken = gitHubProvider.getAccessToken(accessTokenDTO);
        GitHubUser user = gitHubProvider.getUser(accessToken);
        System.out.println("user = " + user);
        return "index";
    }

}
