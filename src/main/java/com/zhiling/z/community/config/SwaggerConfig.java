package com.zhiling.z.community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @Author zlhl
 * @Date 2019/12/26
 * @Version V1.0
 *  EnableSwagger2 开启swagger
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {


    /**
     * 配置swagger的Docket的bean实例
     */
    @Bean
    public Docket docket(Environment environment){
        //设置要显示的swagger环境
        Profiles pr = Profiles.of("dev", "test");

        //通过environment.acceptsProfiles判断是否处在自己设定的环境当中
        boolean flag = environment.acceptsProfiles(pr);

        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
                //是否启动swagger 如果为false则不能再浏览器中访问
            .enable(flag)
                //分组
            .groupName("zz")
            .select()
                //RequestHandlerSelectors 配置要扫描接口的方式
                //basePackage:指定要扫描的包
                //any():扫描全部
                //none():不扫描
                //withClassAnnotation: 扫描类上的注解
                //...
            .apis(RequestHandlerSelectors.any())
                //path 过滤什么路径
            .paths(PathSelectors.any())
            .build();
    }

    //配置swagger信息=apiInfo

    private ApiInfo apiInfo(){
        //作者信息
        Contact contact = new Contact("z","https://localhost:8080","370730477@qq.com");

        return new ApiInfo(
                "39社区",
                "学习交流",
                "v1.0",
                "https://localhost:8080",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>()
        );
    }

}
