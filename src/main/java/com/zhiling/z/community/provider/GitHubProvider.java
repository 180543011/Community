package com.zhiling.z.community.provider;

import com.alibaba.fastjson.JSON;
import com.zhiling.z.community.dto.AccessTokenDTO;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

/**
 *  提供解析GitHub返回code
 * @author zlhl
 */
@Component
public class GitHubProvider {

    public String getAccessToken(AccessTokenDTO accessTokenDTO){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        String content = JSON.toJSONString(accessTokenDTO);
        RequestBody body = RequestBody.create(mediaType, content);
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try(Response response = client.newCall(request).execute()) {
            String string = Objects.requireNonNull(response.body()).string();
            String[] split = string.split("&");
            String tokenStr = split[0];
            return tokenStr.split("=")[1];
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public GitHubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = Objects.requireNonNull(response.body()).string();
            return JSON.parseObject(string, GitHubUser.class);
        } catch (IOException e) {
            //可能有延时异常
            return null;
        }
    }

}
