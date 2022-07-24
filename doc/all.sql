DROP TABLE IF EXISTS `test`;
CREATE TABLE `test`  (
                         `id` bigint NOT NULL COMMENT 'id',
                         `name` varchar(50) COMMENT '名称',
                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB default charset= utf8mb4 comment='测试';