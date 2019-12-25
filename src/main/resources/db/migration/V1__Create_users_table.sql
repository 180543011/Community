CREATE TABLE `users`
(
    `id`        int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `name`      varchar(50)  DEFAULT NULL COMMENT '名字',
    `userName`  varchar(50)  DEFAULT NULL COMMENT '用户名',
    `password`  varchar(50)  DEFAULT '123456' COMMENT '密码',
    `accountId` varchar(100) DEFAULT NULL COMMENT 'API识别码',
    `token`     char(36)     DEFAULT NULL COMMENT '唯一识别码',
    `gmtCreate` bigint(20)   DEFAULT NULL COMMENT '创建时间',
    `gmtModify` bigint(20)   DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `UQ_User_userName` (`userName`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8;