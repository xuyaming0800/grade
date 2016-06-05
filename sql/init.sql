-- MySQL dump 10.13  Distrib 5.6.19, for osx10.7 (i386)
--
-- Host: localhost    Database: grade
-- ------------------------------------------------------
-- Server version	5.6.25

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `aof_segment_table_config`
--

DROP TABLE IF EXISTS `aof_segment_table_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aof_segment_table_config` (
  `ID` bigint(20) NOT NULL,
  `INDEX_TABLE_FK` bigint(20) DEFAULT NULL,
  `TABLE_NAME` varchar(300) DEFAULT NULL,
  `INDEX_VALUE` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aof_segment_table_config`
--

LOCK TABLES `aof_segment_table_config` WRITE;
/*!40000 ALTER TABLE `aof_segment_table_config` DISABLE KEYS */;
/*!40000 ALTER TABLE `aof_segment_table_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `aof_server_monitor`
--

DROP TABLE IF EXISTS `aof_server_monitor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aof_server_monitor` (
  `IP` varchar(255) NOT NULL,
  `VERSION` varchar(100) DEFAULT NULL,
  `MTIME` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`IP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aof_server_monitor`
--

LOCK TABLES `aof_server_monitor` WRITE;
/*!40000 ALTER TABLE `aof_server_monitor` DISABLE KEYS */;
INSERT INTO `aof_server_monitor` VALUES ('192.168.120.206','1.0.0-RELEASE','2016-06-03 17:14:38');
/*!40000 ALTER TABLE `aof_server_monitor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grade_batch_info`
--

DROP TABLE IF EXISTS `grade_batch_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grade_batch_info` (
  `id` int(32) NOT NULL,
  `batch_name` varchar(45) DEFAULT NULL,
  `create_time` int(32) DEFAULT NULL,
  `update_time` int(32) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grade_batch_info`
--

LOCK TABLES `grade_batch_info` WRITE;
/*!40000 ALTER TABLE `grade_batch_info` DISABLE KEYS */;
INSERT INTO `grade_batch_info` VALUES (1,'2016年中期考核',1,1,0);
/*!40000 ALTER TABLE `grade_batch_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grade_batch_item`
--

DROP TABLE IF EXISTS `grade_batch_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grade_batch_item` (
  `id` int(32) NOT NULL,
  `batch_id` int(32) DEFAULT NULL,
  `item_id` int(32) DEFAULT NULL,
  `create_time` int(32) DEFAULT NULL,
  `update_time` int(32) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `INDEX_BATCH_ID` (`batch_id`),
  KEY `INDEX_ITEM_ID` (`item_id`),
  KEY `INDEX_STATUS` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grade_batch_item`
--

LOCK TABLES `grade_batch_item` WRITE;
/*!40000 ALTER TABLE `grade_batch_item` DISABLE KEYS */;
INSERT INTO `grade_batch_item` VALUES (1,1,1,1,1,0),(2,1,2,1,1,0),(3,1,3,1,1,0);
/*!40000 ALTER TABLE `grade_batch_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grade_item_info`
--

DROP TABLE IF EXISTS `grade_item_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grade_item_info` (
  `id` int(32) NOT NULL,
  `item_name` varchar(45) DEFAULT NULL,
  `create_time` int(32) DEFAULT NULL,
  `update_time` int(32) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grade_item_info`
--

LOCK TABLES `grade_item_info` WRITE;
/*!40000 ALTER TABLE `grade_item_info` DISABLE KEYS */;
INSERT INTO `grade_item_info` VALUES (1,'态度',1,1,0),(2,'效率',1,1,0),(3,'成果',1,1,NULL);
/*!40000 ALTER TABLE `grade_item_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grade_org_info`
--

DROP TABLE IF EXISTS `grade_org_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grade_org_info` (
  `id` int(32) NOT NULL,
  `batch_id` int(32) DEFAULT NULL,
  `org_id` int(32) DEFAULT NULL,
  `user_id` int(32) DEFAULT NULL,
  `grade_id` int(32) DEFAULT NULL,
  `creata_time` int(32) DEFAULT NULL,
  `update_time` int(32) DEFAULT NULL,
  `score` double DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grade_org_info`
--

LOCK TABLES `grade_org_info` WRITE;
/*!40000 ALTER TABLE `grade_org_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `grade_org_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grade_user_info`
--

DROP TABLE IF EXISTS `grade_user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grade_user_info` (
  `id` int(32) NOT NULL,
  `op_user_id` int(32) DEFAULT NULL,
  `user_id` int(32) DEFAULT NULL,
  `grade_id` int(32) DEFAULT NULL,
  `batch_id` int(32) DEFAULT NULL,
  `org_id` int(32) DEFAULT NULL,
  `create_time` int(32) DEFAULT NULL,
  `update_time` int(32) DEFAULT NULL,
  `grade` int(3) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grade_user_info`
--

LOCK TABLES `grade_user_info` WRITE;
/*!40000 ALTER TABLE `grade_user_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `grade_user_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `org_info`
--

DROP TABLE IF EXISTS `org_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `org_info` (
  `id` int(32) NOT NULL,
  `org_name` varchar(100) DEFAULT NULL,
  `org_desc` varchar(500) DEFAULT NULL,
  `create_time` int(32) DEFAULT NULL,
  `update_time` int(32) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `INDEX_ORG_NAME` (`org_name`,`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `org_info`
--

LOCK TABLES `org_info` WRITE;
/*!40000 ALTER TABLE `org_info` DISABLE KEYS */;
INSERT INTO `org_info` VALUES (1,'测试组织',NULL,1,1,0);
/*!40000 ALTER TABLE `org_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_id_index`
--

DROP TABLE IF EXISTS `user_id_index`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_id_index` (
  `ID` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `SHARD_ID` bigint(20) DEFAULT NULL,
  `MIGRATION_LOCK` bigint(20) DEFAULT NULL,
  `OLD_SHARD_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `USER_ID_INDEX_unique` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_id_index`
--

LOCK TABLES `user_id_index` WRITE;
/*!40000 ALTER TABLE `user_id_index` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_id_index` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_info` (
  `id` int(32) NOT NULL,
  `org_id` int(32) DEFAULT NULL,
  `user_type` int(1) DEFAULT NULL,
  `user_name` varchar(45) DEFAULT NULL,
  `uid` varchar(45) DEFAULT NULL,
  `user_pass` varchar(45) DEFAULT NULL,
  `create_time` int(32) DEFAULT NULL,
  `update_time` int(32) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id_UNIQUE` (`uid`),
  KEY `index_status` (`status`),
  KEY `index_o_s` (`org_id`,`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_info`
--

LOCK TABLES `user_info` WRITE;
/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
INSERT INTO `user_info` VALUES (-1,-1,-1,'admin','admin','admin',-1,-1,0),(1,1,1,'ceshi','1001','1001',1,1,0),(2,1,1,'cs1','1002','1002',1,1,0),(3,1,1,'cs2','1003','1003',1,1,0);
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'grade'
--

--
-- Dumping routines for database 'grade'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-06-05 17:25:15
