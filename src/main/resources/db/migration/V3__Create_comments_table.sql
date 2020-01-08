CREATE TABLE `db_community`.`comments`
(
    `id`          BIGINT NOT NULL AUTO_INCREMENT COMMENT '评论id',
    `questionId`  BIGINT NOT NULL DEFAULT 0 COMMENT '回复问题id',
    `parentId`    BIGINT NOT NULL DEFAULT 0 COMMENT '评论父id',
    `type`        INT    NOT NULL COMMENT '父类类型',
    `commentator` INT    NOT NULL COMMENT '评论人id',
    `context`     TEXT COMMENT '评论内容',
    `gmtCreate`   BIGINT COMMENT '创建时间',
    `gmtModify`   BIGINT COMMENT '修改时间',
    `likeCount`   INT(11) COMMENT '点赞数',
    PRIMARY KEY (`id`)
) CHARSET = utf8;