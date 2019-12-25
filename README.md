## 39社区项目，练习使用
## 资料
[码匠视频](https://www.bilibili.com/video/av65117012)
[SpringBoot 文档](https://spring.io/projects/spring-boot)
[Spring 文档](https://spring.io)
[Bootstrap 文档](https://v3.bootcss.com/)
[GitHub QAuth](https://developer.github.com/apps/building-oauth-apps/creating-an-oauth-app/)
[SpringBoot dataSource](https://docs.spring.io/spring-boot/docs/2.2.2.RELEASE/reference/html/spring-boot-features.html#boot-features-sql)
[SpringBoot Mybatis](http://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/)
[]()

## 工具
[Git](https://git-scm.com/)
[Flyway](https://flywaydb.org/documentation/maven/)

## jar
[fastJson](https://mvnrepository.com/artifact/com.alibaba/fastjson)
[okHttp](https://mvnrepository.com/artifact/com.squareup.okhttp3/okhttp)
[lombok](https://projectlombok.org/setup/maven)

## sql脚本
```sql
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) DEFAULT NULL COMMENT '名字',
  `userName` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `accountId` varchar(100) DEFAULT NULL COMMENT 'API识别码',
  `token` char(36) DEFAULT NULL COMMENT '唯一识别码',
  `gmtCreate` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `gmtModify` bigint(20) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UQ_User_userName` (`userName`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8
```
```bash
mvn flyway:migrate
```
### 注意(我已屏蔽上传)
src/main/resources/config/accessToken.properties
cookie.maxAge=1800
github.client.id=？
github.client.secret=？
github.client.uri=？