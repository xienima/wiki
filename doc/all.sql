DROP TABLE IF EXISTS `test`;
CREATE TABLE `test`  (
                         `id` bigint NOT NULL COMMENT 'id',
                         `name` varchar(50) COMMENT '名称',
                         `password` varchar(50) comment '密码',
                       PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB default charset= utf8mb4 comment='测试';
insert into `test`(id,name,password) values (1,'测试','password');

DROP TABLE IF EXISTS `demo`;
CREATE TABLE `demo`  (
                         `id` bigint NOT NULL COMMENT 'id',
                         `name` varchar(50) COMMENT '名称',
                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB default charset= utf8mb4 comment='测试';
insert into `demo`(id,name) values (1,'测试');