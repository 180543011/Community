package com.zhiling.z.community.controller;

import com.zhiling.z.community.dto.PageDTO;
import com.zhiling.z.community.model.Question;
import com.zhiling.z.community.model.User;
import com.zhiling.z.community.service.QuestionService;
import com.zhiling.z.community.service.UserService;
import com.zhiling.z.community.utils.VerificationCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  系统控制，用于跳转加工
 * @Author zlhl
 * @Date 2019/12/21
 * @Version V1.0
 **/
@Controller
@PropertySource("classpath:config/accessToken.properties")
public class SystemController {
    @Value("${cookie.maxAge}")
    private Long maxAge;
    private UserService userService;
    private QuestionService questionService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    /**
     *  主页
     */
    @RequestMapping(path = {"/", "/index", "/index.html"})
    public String toIndex(Model model,
                          @RequestParam(value = "index", required = false, defaultValue = "1") Integer pageIndex,
                          @RequestParam(value = "size", required = false, defaultValue = "10") Integer pageSize){
        //创建Page对象
        PageDTO pageDTO = new PageDTO();
        pageDTO.setPageSize(pageSize);
        pageDTO.setPageIndex(pageIndex);
        //获取问题信息
        List<Question> questions = questionService.listQuestion(pageDTO);
        model.addAttribute("questions", questions);
        model.addAttribute("pageDTO", pageDTO);
        return "index";
    }


    /**
     *  登录后台
     * @param user 用户输入对象
     */
    @ResponseBody
    @PostMapping("/login")
    public Map<String, String> loginBackstage(User user, HttpServletRequest request,HttpServletResponse response,
                                              @RequestParam(value = "vfCode", required = false) String vfCode){
        //定义验证码类型
        String vCodeType = "loginCode";
        Map<String, String> data = new HashMap<>(2);
        String vCode = (String) request.getSession().getAttribute(vCodeType);
        request.getSession().removeAttribute(vCodeType);
        //判断验证码是否正确
        if (vfCode.equalsIgnoreCase(vCode)) {
            //验证码正确，验证用户信息是否正确
            User loginUser = userService.login(user);
            if (loginUser!=null && user.getPassword().equals(loginUser.getPassword())){
                //创建cookie
                Cookie communityToken = new Cookie("communityToken", loginUser.getToken());
                //设置cookie过期时间
                communityToken.setMaxAge(Math.toIntExact(maxAge));
                response.addCookie(communityToken);
                data.put("type","success");
            }else {
                data.put("type","error");
                data.put("message","用户名或密码错误！");
            }
        }else {
            data.put("type","error");
            data.put("message","验证码错误");
        }
        return data;
    }

    /**
     *  用户注册
     */
    @PostMapping("/register")
    @ResponseBody
    public Map<String, String> registerUser(User user, HttpServletResponse response){
        Map<String, String> data = new HashMap<>(2);
        String token = userService.register(user);
        if (token != null){
            //创建cookie
            Cookie communityToken = new Cookie("communityToken", token);
            //设置cookie过期时间
            communityToken.setMaxAge(Math.toIntExact(maxAge));
            response.addCookie(communityToken);
            data.put("type","success");
        }else {
            data.put("type","error");
            data.put("message","用户名已存在");
        }
        return data;
    }

    /**
     *  用户登出
     */
    @ResponseBody
    @RequestMapping("/logout")
    public Map<String,String> logout(HttpSession session, HttpServletResponse response){
        Map<String, String> data = new HashMap<>(2);
        //创建cookie
        Cookie communityToken = new Cookie("communityToken",null);
        //设置cookie过期时间
        communityToken.setMaxAge(0);
        response.addCookie(communityToken);
        session.invalidate();
        data.put("type","success");
        return data;
    }

    /**
     * 验证码生成方法
     * 本系统所有验证码均用此方法实现
     * @param vCodeLen 验证码长度
     * @param width 验证码图片宽度
     * @param height 验证码图片高度
     * @param vfCodeType:用来区别验证码的类型，传入字符串
     * @param request HttpServletRequest对象
     * @param response  HttpServletResponse对象
     */
    @RequestMapping(value="/get_vfCode",method= RequestMethod.GET)
    public void generateVerification(
            @RequestParam(name="vl",required=false,defaultValue="4") Integer vCodeLen,
            @RequestParam(name="w",required=false,defaultValue="100") Integer width,
            @RequestParam(name="h",required=false,defaultValue="30") Integer height,
            @RequestParam(name="type", defaultValue="loginCode") String vfCodeType,
            HttpServletRequest request,
            HttpServletResponse response){
        VerificationCodeUtil vfUtil = new VerificationCodeUtil(vCodeLen, width, height);
        String generatorCode = vfUtil.generatorCode();
        request.getSession().setAttribute(vfCodeType, generatorCode);
        BufferedImage generatorRotateCodeImage = vfUtil.generatorRotateCodeImage(generatorCode, true);
        try {
            ImageIO.write(generatorRotateCodeImage, "gif", response.getOutputStream());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
