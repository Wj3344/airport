-- 如果存在该数据库就删除
drop database if exists `airport`;
-- 创建数据库
create database if not exists airport default charset utf8;
use airport;
# 创建数据表
create table if not exists `student`
(
  `studentId`   int          not null,
  `studentName` varchar(200) not null,
  `age`         int,
  primary key (`studentId`)
) character set utf8
  collate utf8_general_ci;
# 插入测试数据
insert into student values (15041225,'chen',18);
insert into student values (15041311,'yan yu',18);
