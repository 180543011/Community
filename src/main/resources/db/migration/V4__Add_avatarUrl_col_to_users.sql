ALTER TABLE `db_community`.`users`
    ADD COLUMN `avatarUrl` VARCHAR(100) NULL  COMMENT '头像地址' AFTER `bio`;