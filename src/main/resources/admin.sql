-- MySQL dump 10.13  Distrib 5.7.24-27, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: airport
-- ------------------------------------------------------
-- Server version	5.7.24-27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE = @@TIME_ZONE */;
/*!40103 SET TIME_ZONE = '+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;
/*!50717
SELECT COUNT(*) INTO @rocksdb_has_p_s_session_variables
FROM INFORMATION_SCHEMA.TABLES
WHERE TABLE_SCHEMA = 'performance_schema'
  AND TABLE_NAME = 'session_variables' */;
/*!50717 SET @rocksdb_get_is_supported = IF(@rocksdb_has_p_s_session_variables,
                                            'SELECT COUNT(*) INTO @rocksdb_is_supported FROM performance_schema.session_variables WHERE VARIABLE_NAME=\'rocksdb_bulk_load\'',
                                            'SELECT 0') */;
/*!50717 PREPARE s FROM @rocksdb_get_is_supported */;
/*!50717 EXECUTE s */;
/*!50717 DEALLOCATE PREPARE s */;
/*!50717 SET @rocksdb_enable_bulk_load =
    IF(@rocksdb_is_supported, 'SET SESSION rocksdb_bulk_load = 1', 'SET @rocksdb_dummy_bulk_load = 0') */;
/*!50717 PREPARE s FROM @rocksdb_enable_bulk_load */;
/*!50717 EXECUTE s */;
/*!50717 DEALLOCATE PREPARE s */;

--
-- Current Database: `airport`
--
-- 创建数据库
CREATE DATABASE /*!32312 IF NOT EXISTS */ `airport` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `airport`;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin`
(
  `username` varchar(200) NOT NULL COMMENT '用户名',
  `password` varchar(200) NOT NULL COMMENT '密码',
  `identity` int(11)      NOT NULL COMMENT '账户等级',
  PRIMARY KEY (`username`),
  UNIQUE KEY `username` (`username`, `identity`),
  KEY `user_identity` (`identity`),
  CONSTRAINT `user_identity` FOREIGN KEY (`identity`) REFERENCES `identity` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin`
  DISABLE KEYS */;
INSERT INTO `admin`
VALUES ('fanchen0', '739288dc9f489466f04cae89e123db31', 0),
       ('fanchen1', '739288dc9f489466f04cae89e123db31', 1),
       ('fanchen10', '739288dc9f489466f04cae89e123db31', 10),
       ('fanchen11', '739288dc9f489466f04cae89e123db31', 11),
       ('fanchen12', '739288dc9f489466f04cae89e123db31', 12),
       ('fanchen2', '739288dc9f489466f04cae89e123db31', 2),
       ('fanchen3', '739288dc9f489466f04cae89e123db31', 3),
       ('fanchen4', '739288dc9f489466f04cae89e123db31', 4),
       ('fanchen5', '739288dc9f489466f04cae89e123db31', 5),
       ('fanchen6', '739288dc9f489466f04cae89e123db31', 6),
       ('fanchen7', '739288dc9f489466f04cae89e123db31', 7),
       ('fanchen8', '739288dc9f489466f04cae89e123db31', 8),
       ('fanchen9', '739288dc9f489466f04cae89e123db31', 9);
/*!40000 ALTER TABLE `admin`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `identity`
--

DROP TABLE IF EXISTS `identity`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `identity`
(
  `id`       int(11)      NOT NULL COMMENT '账户等级ID',
  `describe` varchar(255) NOT NULL COMMENT '账户等级描述',
  `name`     varchar(255) NOT NULL COMMENT '用户角色名称',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `identity`
--

LOCK TABLES `identity` WRITE;
/*!40000 ALTER TABLE `identity`
  DISABLE KEYS */;
INSERT INTO `identity`
VALUES (0, '超级管理员', 'admin'),
       (1, '值机一级帐号', 'checkIn1'),
       (2, '值机二级帐号', 'checkIn2'),
       (3, '清洁队一级账号', 'clean1'),
       (4, '清洁队二级账号', 'clean2'),
       (5, '站坪车辆一级账号', 'station1'),
       (6, '站坪车辆二级账号', 'station2'),
       (7, '综合服务一级帐号', 'integratedService1'),
       (8, '综合服务二级帐号', 'integratedService2'),
       (9, '行查一级帐号', 'baggage1'),
       (10, '行查二级账号', 'baggage2'),
       (11, '货运二级账号', 'freight1'),
       (12, '货运二级账号', 'freight2');
/*!40000 ALTER TABLE `identity`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permission`
(
  `id`   bigint(20) NOT NULL AUTO_INCREMENT,
  `url`  varchar(255) DEFAULT NULL COMMENT 'url地址',
  `name` varchar(64)  DEFAULT NULL COMMENT 'url描述',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission`
  DISABLE KEYS */;
INSERT INTO `permission`
VALUES (1, '/user', 'user:user'),
       (2, '/user/add', 'user:add'),
       (3, '/user/delete', 'user:delete');
/*!40000 ALTER TABLE `permission`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_permission`
--

DROP TABLE IF EXISTS `role_permission`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_permission`
(
  `identity`     int(20)    DEFAULT NULL COMMENT '角色id',
  `permissionId` bigint(20) DEFAULT NULL COMMENT '权限id',
  KEY `identity` (`identity`),
  KEY `permissionId` (`permissionId`),
  CONSTRAINT `role_permission_ibfk_1` FOREIGN KEY (`identity`) REFERENCES `identity` (`id`),
  CONSTRAINT `role_permission_ibfk_2` FOREIGN KEY (`permissionId`) REFERENCES `permission` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_permission`
--

LOCK TABLES `role_permission` WRITE;
/*!40000 ALTER TABLE `role_permission`
  DISABLE KEYS */;
INSERT INTO `role_permission`
VALUES (0, 1),
       (0, 2);
/*!40000 ALTER TABLE `role_permission`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student`
(
  `studentId`   int(11)      NOT NULL,
  `studentName` varchar(200) NOT NULL,
  `age`         int(11) DEFAULT NULL,
  PRIMARY KEY (`studentId`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student`
  DISABLE KEYS */;
INSERT INTO `student`
VALUES (15041225, 'chen', 18),
       (15041311, 'yan yu', 18);
/*!40000 ALTER TABLE `student`
  ENABLE KEYS */;
UNLOCK TABLES;
/*!50112 SET @disable_bulk_load = IF(@is_rocksdb_supported, 'SET SESSION rocksdb_bulk_load = @old_rocksdb_bulk_load',
                                     'SET @dummy_rocksdb_bulk_load = 0') */;
/*!50112 PREPARE s FROM @disable_bulk_load */;
/*!50112 EXECUTE s */;
/*!50112 DEALLOCATE PREPARE s */;
/*!40103 SET TIME_ZONE = @OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;

-- Dump completed on 2019-01-06 19:16:34

# 创建航班信息表
create table if not exists `airport`.`flightInformation`
(
  `id`               int auto_increment not null comment '记录ID',
  `flightNumber`     varchar(20)        not null comment '航班号',
  `planeNumber`      varchar(20)        not null comment '机号',
  `boardingTime`     datetime           not null comment '登机时间',
  `gatePosition`     varchar(20)        not null comment '停机位',
  `departureStation` varchar(20)        not null comment '始发站',
  `destination`      varchar(20)        not null comment '目的地',
  `time`             timestamp          not null default current_timestamp comment '记录添加时间',
  key (id),
  primary key (flightNumber, planeNumber)
) COMMENT '航班信息表'
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

#  创建重点旅客标记表
create table if not exists `airport`.`passengerTag`
(
  `id`       int auto_increment not null primary key comment '旅客标记id',
  `describe` varchar(255)       not null comment '标记说明'
) COMMENT '旅客标记标签表'
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

# 添加重点旅客标记信息
insert into `airport`.`passengerTag` (`describe`)
values ('VVIP'),
       ('VIP'),
       ('白金卡'),
       ('金卡'),
       ('无陪儿童'),
       ('轮椅旅客'),
       ('押解犯人'),
       ('活体器官'),
       ('头等舱');


#  重点旅客标记与一趟航班的联系
create table if not exists `airport`.`flightInformation_passengerTag`
(
  `id`       int not null comment 'id',
  `flightId` int not null comment '航班记录id',
  `tagId`    int not null comment '标签id',
  key (`id`),
  primary key (`flightId`, `tagId`),
  CONSTRAINT `flightInformation_passengerTag_1` FOREIGN KEY (`flightId`) REFERENCES `flightInformation` (`id`),
  CONSTRAINT `flightInformation_passengerTag_2` FOREIGN KEY (`tagId`) REFERENCES `passengerTag` (`id`)
) COMMENT '航班信息和旅客标记联系表'
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

# 创建特殊航班标记表
create table if not exists `airport`.`specialFlight`
(
  `id`       int          not null auto_increment comment '标签id',
  `describe` varchar(255) not null comment '标签说明',
  primary key (`id`)
) COMMENT '特殊航班标记标签表'
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

insert into `airport`.`specialFlight` (`describe`)
values ('特殊团队'),
       ('过站航班');

#  特殊航班与一趟航班的标记关系
create table if not exists `airport`.`flightInformation_specialFlight`
(
  `id`            int not null comment 'id',
  `flightId`      int not null comment '航班记录id',
  `specialFlight` int not null comment '标签id',
  key (`id`),
  primary key (`flightId`, `specialFlight`),
  CONSTRAINT `flightInformation_specialFlight_1` FOREIGN KEY (`flightId`) REFERENCES `flightInformation` (`id`),
  CONSTRAINT `flightInformation_specialFlight_2` FOREIGN KEY (`specialFlight`) REFERENCES `specialFlight` (`id`)
) COMMENT '航班信息和特殊航班联系表'
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

# 创建值机信息表
create table if not exists `airport`.`checkIn`
(
  `id`                  int auto_increment not null primary key comment '记录id',
  `flightInformationId` int                not null comment '航班信息记录表的ID',
  `realNumber`          int                not null comment '实际人数',
  `luggageNumber`       int                not null comment '实际行李件数',
  `specialCase`         varchar(255) comment '特殊情况说明',
  `createTime`          timestamp default current_timestamp comment '最后修改时间',
  constraint `checkIn_1` foreign key (`flightInformationId`) references `flightInformation` (`id`)
) comment '值机信息表'
  engine = InnoDB
  default charset = utf8;

# 创建清洁队记录表
create table if not exists `airport`.`clean`
(
  `id`                  int auto_increment not null primary key comment '记录id',
  `flightInformationId` int                not null comment '航班信息记录表的ID',
  `readTime`            datetime           not null comment '到位时间',
  `usedTime`            int                not null comment '清洁用时',
  `specialCase`         varchar(255) comment '特殊情况说明',
  `createTime`          timestamp default current_timestamp comment '最后修改时间',
  constraint `clean_1` foreign key (`flightInformationId`) references `flightInformation` (`id`)
) comment '清洁信息记录表'
  engine = InnoDB
  default charset = utf8;

#  创建货运记录表
create table if not exists `airport`.`freight`
(
  `id`                  int auto_increment not null primary key comment '记录id',
  `flightInformationId` int                not null comment '航班信息记录表的ID',
  `closeTime`           datetime           not null comment '关闭货仓时间',
  `specialCase`         varchar(255) comment '特殊情况说明',
  `createTime`          timestamp default current_timestamp comment '最后修改时间',
  constraint `freight_1` foreign key (`flightInformationId`) references `flightInformation` (`id`)
) comment '货运信息记录表'
  engine = InnoDB
  default charset = utf8;

#  创建行查记录表
create table if not exists `airport`.`baggage`
(
  `id`                  int auto_increment not null primary key comment '记录id',
  `flightInformationId` int                not null comment '航班信息记录表的ID',
  `arrivedTime`         datetime           not null comment '行李车到位时间',
  `readyTime`           datetime           not null comment '传送带到位时间',
  `specialCase`         varchar(255) comment '特殊情况说明',
  `createTime`          timestamp default current_timestamp comment '最后修改时间',
  constraint `baggage_1` foreign key (`flightInformationId`) references `flightInformation` (`id`)
) comment '行查记录表'
  engine = InnoDB
  default charset = utf8;

# 创建综合服务信息记录表  integratedService
create table if not exists `airport`.`integratedService`
(
  `id`                  int auto_increment not null primary key comment '记录id',
  `flightInformationId` int                not null comment '航班信息记录表的ID',
  `boardingTime`        datetime           not null comment '登机时间',
  `readyTime`           datetime           not null comment '客齐时间',
  `closeTime`           datetime           not null comment '关闭客舱时间',
  `specialCase`         varchar(255) comment '特殊情况说明',
  `createTime`          timestamp default current_timestamp comment '最后修改时间',
  constraint `integratedService_1` foreign key (`flightInformationId`) references `flightInformation` (`id`)
) comment '综合服务信息记录表'
  engine = InnoDB
  default charset = utf8;

# 创建站坪信息记录表  stand
create table if not exists `airport`.`stand`
(
  `id`                  int auto_increment not null primary key comment '记录id',
  `flightInformationId` int                not null comment '航班信息记录表的ID',
  `vipTime`             datetime           not null comment 'vip车辆到位时间时间',
  `cartTime`            datetime           not null comment '推车到位时间',
  `specialCase`         varchar(255) comment '特殊情况说明',
  `createTime`          timestamp default current_timestamp comment '最后修改时间',
  constraint `stand_1` foreign key (`flightInformationId`) references `flightInformation` (`id`)
) comment '创建站坪信息记录表'
  engine = InnoDB
  default charset = utf8;