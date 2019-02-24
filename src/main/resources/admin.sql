-- MySQL dump 10.13  Distrib 8.0.13-4, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: airport
-- ------------------------------------------------------
-- Server version	8.0.13-4

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*!50717 SELECT COUNT(*) INTO @rocksdb_has_p_s_session_variables FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'performance_schema' AND TABLE_NAME = 'session_variables' */;
/*!50717 SET @rocksdb_get_is_supported = IF (@rocksdb_has_p_s_session_variables, 'SELECT COUNT(*) INTO @rocksdb_is_supported FROM performance_schema.session_variables WHERE VARIABLE_NAME=\'rocksdb_bulk_load\'', 'SELECT 0') */;
/*!50717 PREPARE s FROM @rocksdb_get_is_supported */;
/*!50717 EXECUTE s */;
/*!50717 DEALLOCATE PREPARE s */;
/*!50717 SET @rocksdb_enable_bulk_load = IF (@rocksdb_is_supported, 'SET SESSION rocksdb_bulk_load = 1', 'SET @rocksdb_dummy_bulk_load = 0') */;
/*!50717 PREPARE s FROM @rocksdb_enable_bulk_load */;
/*!50717 EXECUTE s */;
/*!50717 DEALLOCATE PREPARE s */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `admin` (
  `id` int not null auto_increment comment '记录id',
  `username` varchar(200) NOT NULL COMMENT '用户名',
  `password` varchar(200) NOT NULL COMMENT '密码',
  `identity` int(11) NOT NULL COMMENT '账户等级',
  PRIMARY KEY (`username`),
  UNIQUE KEY `username` (`username`,`identity`),
  KEY `user_identity` (`identity`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES ('fanchen0','739288dc9f489466f04cae89e123db31',0),('fanchen1','739288dc9f489466f04cae89e123db31',1),('fanchen10','739288dc9f489466f04cae89e123db31',10),('fanchen11','739288dc9f489466f04cae89e123db31',11),('fanchen12','739288dc9f489466f04cae89e123db31',12),('fanchen2','739288dc9f489466f04cae89e123db31',2),('fanchen3','739288dc9f489466f04cae89e123db31',3),('fanchen4','739288dc9f489466f04cae89e123db31',4),('fanchen5','739288dc9f489466f04cae89e123db31',5),('fanchen6','739288dc9f489466f04cae89e123db31',6),('fanchen7','739288dc9f489466f04cae89e123db31',7),('fanchen8','739288dc9f489466f04cae89e123db31',8),('fanchen9','739288dc9f489466f04cae89e123db31',9);
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `baggage`
--

DROP TABLE IF EXISTS `baggage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `baggage` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '记录id',
  `flightInformationId` int(11) NOT NULL COMMENT '航班信息记录表的ID',
  `arrivedTime` datetime NOT NULL COMMENT '行李车到位时间',
  `readyTime` datetime NOT NULL COMMENT '传送带到位时间',
  `specialCase` varchar(255) DEFAULT NULL COMMENT '特殊情况说明',
  `createTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='行查记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `baggage`
--

LOCK TABLES `baggage` WRITE;
/*!40000 ALTER TABLE `baggage` DISABLE KEYS */;
/*!40000 ALTER TABLE `baggage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `checkIn`
--

DROP TABLE IF EXISTS `checkIn`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `checkIn` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '记录id',
  `flightInformationId` int(11) NOT NULL COMMENT '航班信息记录表的ID',
  `realNumber` int(11) NOT NULL COMMENT '实际人数',
  `luggageNumber` int(11) NOT NULL COMMENT '实际行李件数',
  `specialCase` varchar(255) DEFAULT NULL COMMENT '特殊情况说明',
  `createTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='值机信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `checkIn`
--

LOCK TABLES `checkIn` WRITE;
/*!40000 ALTER TABLE `checkIn` DISABLE KEYS */;
/*!40000 ALTER TABLE `checkIn` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clean`
--

DROP TABLE IF EXISTS `clean`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `clean` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '记录id',
  `flightInformationId` int(11) NOT NULL COMMENT '航班信息记录表的ID',
  `readTime` datetime NOT NULL COMMENT '到位时间',
  `usedTime` int(11) NOT NULL COMMENT '清洁用时',
  `specialCase` varchar(255) DEFAULT NULL COMMENT '特殊情况说明',
  `createTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='清洁信息记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clean`
--

LOCK TABLES `clean` WRITE;
/*!40000 ALTER TABLE `clean` DISABLE KEYS */;
/*!40000 ALTER TABLE `clean` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flightInformation`
--

DROP TABLE IF EXISTS `flightInformation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `flightInformation` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `flightNumber` varchar(20) NOT NULL COMMENT '航班号',
  `planeNumber` varchar(20) NOT NULL COMMENT '机号',
  `boardingTime` datetime NOT NULL COMMENT '登机时间',
  `gatePosition` varchar(20) NOT NULL COMMENT '停机位',
  `departureStation` varchar(20) NOT NULL COMMENT '始发站',
  `destination` varchar(20) NOT NULL COMMENT '目的地',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录添加时间',
  `special` varchar(512) DEFAULT NULL COMMENT '特殊情况说明',
  PRIMARY KEY (`flightNumber`,`planeNumber`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='航班信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flightInformation`
--

LOCK TABLES `flightInformation` WRITE;
/*!40000 ALTER TABLE `flightInformation` DISABLE KEYS */;
INSERT INTO `flightInformation` VALUES (2,'fanchen001','9527','2019-01-30 11:00:09','693','上海','赣州','2019-01-30 03:00:20','测试数据');
/*!40000 ALTER TABLE `flightInformation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flightInformation_passengerTag`
--

DROP TABLE IF EXISTS `flightInformation_passengerTag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `flightInformation_passengerTag` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `flightId` int(11) NOT NULL COMMENT '航班记录id',
  `tagId` int(11) NOT NULL COMMENT '标签id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `flightId` (`flightId`,`tagId`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='航班信息和旅客标记联系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flightInformation_passengerTag`
--

LOCK TABLES `flightInformation_passengerTag` WRITE;
/*!40000 ALTER TABLE `flightInformation_passengerTag` DISABLE KEYS */;
INSERT INTO `flightInformation_passengerTag` VALUES (1,2,1),(2,2,3),(3,2,6),(4,2,9);
/*!40000 ALTER TABLE `flightInformation_passengerTag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flightInformation_specialFlight`
--

DROP TABLE IF EXISTS `flightInformation_specialFlight`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `flightInformation_specialFlight` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `flightId` int(11) NOT NULL COMMENT '航班记录id',
  `specialFlight` int(11) NOT NULL COMMENT '标签id',
  PRIMARY KEY (`flightId`,`specialFlight`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='航班信息和特殊航班联系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flightInformation_specialFlight`
--

LOCK TABLES `flightInformation_specialFlight` WRITE;
/*!40000 ALTER TABLE `flightInformation_specialFlight` DISABLE KEYS */;
INSERT INTO `flightInformation_specialFlight` VALUES (1,2,1);
/*!40000 ALTER TABLE `flightInformation_specialFlight` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `freight`
--

DROP TABLE IF EXISTS `freight`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `freight` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '记录id',
  `flightInformationId` int(11) NOT NULL COMMENT '航班信息记录表的ID',
  `closeTime` datetime NOT NULL COMMENT '关闭货仓时间',
  `specialCase` varchar(255) DEFAULT NULL COMMENT '特殊情况说明',
  `createTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='货运信息记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `freight`
--

LOCK TABLES `freight` WRITE;
/*!40000 ALTER TABLE `freight` DISABLE KEYS */;
/*!40000 ALTER TABLE `freight` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `identity`
--

DROP TABLE IF EXISTS `identity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `identity` (
  `id` int(11) NOT NULL COMMENT '账户等级ID',
  `describe` varchar(255) NOT NULL COMMENT '账户等级描述',
  `name` varchar(255) NOT NULL COMMENT '用户角色名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `identity`
--

LOCK TABLES `identity` WRITE;
/*!40000 ALTER TABLE `identity` DISABLE KEYS */;
INSERT INTO `identity` VALUES (0,'超级管理员','admin'),(1,'值机一级帐号','checkIn1'),(2,'值机二级帐号','checkIn2'),(3,'清洁队一级账号','clean1'),(4,'清洁队二级账号','clean2'),(5,'站坪车辆一级账号','station1'),(6,'站坪车辆二级账号','station2'),(7,'综合服务一级帐号','integratedService1'),(8,'综合服务二级帐号','integratedService2'),(9,'行查一级帐号','baggage1'),(10,'行查二级账号','baggage2'),(11,'货运二级账号','freight1'),(12,'货运二级账号','freight2');
/*!40000 ALTER TABLE `identity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `integratedService`
--

DROP TABLE IF EXISTS `integratedService`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `integratedService` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '记录id',
  `flightInformationId` int(11) NOT NULL COMMENT '航班信息记录表的ID',
  `boardingTime` datetime NOT NULL COMMENT '登机时间',
  `readyTime` datetime NOT NULL COMMENT '客齐时间',
  `closeTime` datetime NOT NULL COMMENT '关闭客舱时间',
  `specialCase` varchar(255) DEFAULT NULL COMMENT '特殊情况说明',
  `createTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='综合服务信息记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `integratedService`
--

LOCK TABLES `integratedService` WRITE;
/*!40000 ALTER TABLE `integratedService` DISABLE KEYS */;
/*!40000 ALTER TABLE `integratedService` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `passengerTag`
--

DROP TABLE IF EXISTS `passengerTag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `passengerTag` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '旅客标记id',
  `describe` varchar(255) NOT NULL COMMENT '标记说明',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='旅客标记标签表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `passengerTag`
--

LOCK TABLES `passengerTag` WRITE;
/*!40000 ALTER TABLE `passengerTag` DISABLE KEYS */;
INSERT INTO `passengerTag` VALUES (1,'VVIP'),(2,'VIP'),(3,'白金卡'),(4,'金卡'),(5,'无陪儿童'),(6,'轮椅旅客'),(7,'押解犯人'),(8,'活体器官'),(9,'头等舱');
/*!40000 ALTER TABLE `passengerTag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) DEFAULT NULL COMMENT 'url地址',
  `name` varchar(64) DEFAULT NULL COMMENT 'url描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` VALUES (1,'/user','user:user'),(2,'/user/add','user:add'),(3,'/user/delete','user:delete');
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_permission`
--

DROP TABLE IF EXISTS `role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `role_permission` (
  `identity` int(20) DEFAULT NULL COMMENT '角色id',
  `permissionId` bigint(20) DEFAULT NULL COMMENT '权限id',
  KEY `identity` (`identity`),
  KEY `permissionId` (`permissionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_permission`
--

LOCK TABLES `role_permission` WRITE;
/*!40000 ALTER TABLE `role_permission` DISABLE KEYS */;
INSERT INTO `role_permission` VALUES (0,1),(0,2);
/*!40000 ALTER TABLE `role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `specialFlight`
--

DROP TABLE IF EXISTS `specialFlight`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `specialFlight` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标签id',
  `describe` varchar(255) NOT NULL COMMENT '标签说明',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='特殊航班标记标签表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `specialFlight`
--

LOCK TABLES `specialFlight` WRITE;
/*!40000 ALTER TABLE `specialFlight` DISABLE KEYS */;
INSERT INTO `specialFlight` VALUES (1,'特殊团队'),(2,'过站航班');
/*!40000 ALTER TABLE `specialFlight` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stand`
--

DROP TABLE IF EXISTS `stand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `stand` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '记录id',
  `flightInformationId` int(11) NOT NULL COMMENT '航班信息记录表的ID',
  `vipTime` datetime NOT NULL COMMENT 'vip车辆到位时间时间',
  `cartTime` datetime NOT NULL COMMENT '推车到位时间',
  `specialCase` varchar(255) DEFAULT NULL COMMENT '特殊情况说明',
  `createTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='创建站坪信息记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stand`
--

LOCK TABLES `stand` WRITE;
/*!40000 ALTER TABLE `stand` DISABLE KEYS */;
/*!40000 ALTER TABLE `stand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `student` (
  `studentId` int(11) NOT NULL,
  `studentName` varchar(200) NOT NULL,
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`studentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (15041225,'chen',18),(15041311,'yan yu',18);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;
/*!50112 SET @disable_bulk_load = IF (@is_rocksdb_supported, 'SET SESSION rocksdb_bulk_load = @old_rocksdb_bulk_load', 'SET @dummy_rocksdb_bulk_load = 0') */;
/*!50112 PREPARE s FROM @disable_bulk_load */;
/*!50112 EXECUTE s */;
/*!50112 DEALLOCATE PREPARE s */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-30 20:05:26
