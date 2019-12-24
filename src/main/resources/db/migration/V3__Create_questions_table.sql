CREATE TABLE `db_community`.`questions`
(
    `id`           BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
    `title`        VARCHAR(50) COMMENT '标题',
    `description`  TEXT COMMENT '详细内容',
    `creator`      int(11) COMMENT '创建者',
    `gmtCreate`    BIGINT COMMENT '创建时间',
    `gmtModify`    BIGINT COMMENT '修改时间',
    `commentCount` INT DEFAULT 0 COMMENT '评论数',
    `viewCount`    INT DEFAULT 0 COMMENT '浏览数',
    `likeCount`    INT DEFAULT 0 COMMENT '点赞数',
    `tag`         VARCHAR(256) COMMENT '标签集',
    PRIMARY KEY (`id`)
) CHARSET = utf8;