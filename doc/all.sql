# 电子书表
drop table if exists `ebook`;
create table `ebook` (
                         `id` bigint not null comment 'id',
                         `name` varchar(50) comment '名称',
                         `category1_id` bigint comment '分类1',
                         `category2_id` bigint comment '分类2',
                         `description` varchar(200) comment '描述',
                         `cover` varchar(200) comment '封面',
                         `doc_count` int not null default 0 comment '文档数',
                         `view_count` int not null default 0 comment '阅读数',
                         `vote_count` int not null default 0 comment '点赞数',
                         primary key (`id`)
) engine=innodb default charset=utf8mb4 comment='电子书';

insert into `ebook` (id, name, description) values (1, 'Spring Boot 入门教程', '零基础入门 Java 开发，企业级应用开发最佳首选框架');
insert into `ebook` (id, name, description) values (2, 'Vue 入门教程', '零基础入门 Vue 开发，企业级应用开发最佳首选框架');
insert into `ebook` (id, name, description) values (3, 'Python 入门教程', '零基础入门 Python 开发，企业级应用开发最佳首选框架');
insert into `ebook` (id, name, description) values (4, 'Mysql 入门教程', '零基础入门 Mysql 开发，企业级应用开发最佳首选框架');
insert into `ebook` (id, name, description) values (5, 'Oracle 入门教程', '零基础入门 Oracle 开发，企业级应用开发最佳首选框架');



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

# 分类
create  table  `category` (
                              `id` bigint not null comment 'id',
                              `parent` bigint not null default 0 comment '父id',
                              `name` varchar(50) not null comment '名称',
                              `sort` int comment '顺序',
                              primary key (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment ='分类';

insert into `category` (id,parent,name,sort) value (100,000,'前端开发',100);
insert into `category` (id,parent,name,sort) value (101,100,'VUE',101);
insert into `category` (id,parent,name,sort) value (102,100,'HTML',102);
insert into `category` (id,parent,name,sort) value (200,000,'JAVA',200);
insert into `category` (id,parent,name,sort) value (201,200,'基础应用',201);
insert into `category` (id,parent,name,sort) value (202,200,'框架应用',202);
insert into `category` (id,parent,name,sort) value (300,000,'Python',300);
insert into `category` (id,parent,name,sort) value (301,300,'基础应用',301);
insert into `category` (id,parent,name,sort) value (302,300,'进阶方向应用',302);
insert into `category` (id,parent,name,sort) value (400,000,'数据库',400);
insert into `category` (id,parent,name,sort) value (401,400,'Mysql',401);
insert into `category` (id,parent,name,sort) value (402,400,'redis',402);
insert into `category` (id,parent,name,sort) value (500,000,'其他',500);
insert into `category` (id,parent,name,sort) value (501,500,'开发工具',501);
insert into `category` (id,parent,name,sort) value (502,500,'热门服务端语言',502);


-- 文档表 提交
drop table if exists `doc`;
create table `doc` (
                       `id` bigint not null comment 'id',
                       `ebook_id` bigint not null default 0 comment '电子书id',
                       `parent` bigint not null default 0 comment '父id',
                       `name` varchar(50) not null comment '名称',
                       `sort` int comment '顺序',
                       `view_count` int default 0 comment '阅读数',
                       `vote_count` int default 0 comment '点赞数',
                       primary key (`id`)
) engine=innodb default charset=utf8mb4 comment='文档';

insert into `doc` (id, ebook_id, parent, name, sort, view_count, vote_count) values (1, 1, 0, '文档1', 1, 0, 0);
insert into `doc` (id, ebook_id, parent, name, sort, view_count, vote_count) values (2, 1, 1, '文档1.1', 1, 0, 0);
insert into `doc` (id, ebook_id, parent, name, sort, view_count, vote_count) values (3, 1, 0, '文档2', 2, 0, 0);
insert into `doc` (id, ebook_id, parent, name, sort, view_count, vote_count) values (4, 1, 3, '文档2.1', 1, 0, 0);
insert into `doc` (id, ebook_id, parent, name, sort, view_count, vote_count) values (5, 1, 3, '文档2.2', 2, 0, 0);
insert into `doc` (id, ebook_id, parent, name, sort, view_count, vote_count) values (6, 1, 5, '文档2.2.1', 1, 0, 0);
