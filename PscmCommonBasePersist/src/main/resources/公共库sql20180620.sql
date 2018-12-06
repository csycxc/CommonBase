-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: com_municipal_bridge_housing_dept
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `eng_division`
--

DROP TABLE IF EXISTS `eng_division`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `eng_division` (
  `div_item_code` varchar(50) NOT NULL COMMENT '工程划分编码',
  `div_name` varchar(50) DEFAULT NULL,
  `skill` varchar(50) DEFAULT NULL,
  `div_level` char(1) DEFAULT NULL,
  `parent_div_item_code` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`div_item_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='从单位工程到工序的所有划分';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `hazards`
--

DROP TABLE IF EXISTS `hazards`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hazards` (
  `hazards_code` varchar(50) NOT NULL COMMENT '危险源编码',
  `div_item_code` varchar(50) DEFAULT NULL COMMENT '工程划分项编码',
  `div_level` char(1) DEFAULT NULL,
  `hazards_factors` varchar(50) DEFAULT NULL COMMENT '危险源及危害因素',
  `hazards_level` varchar(50) DEFAULT NULL COMMENT '风险级别',
  `accidents` varchar(500) DEFAULT NULL COMMENT '可能造成的事故事件',
  `controls_measures` varchar(500) DEFAULT NULL COMMENT '控制措施',
  `description` varchar(500) DEFAULT NULL COMMENT '备注说明',
  PRIMARY KEY (`hazards_code`),
  KEY `FK_Reference_2` (`div_item_code`),
  CONSTRAINT `FK_Reference_2` FOREIGN KEY (`div_item_code`) REFERENCES `eng_division` (`div_item_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工程危险源';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `hidden_trouble`
--

DROP TABLE IF EXISTS `hidden_trouble`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hidden_trouble` (
  `trouble_code` varchar(50) NOT NULL,
  `div_item_code` varchar(50) DEFAULT NULL COMMENT '划分项编码',
  `div_level` char(1) DEFAULT NULL,
  `trobule_cate` varchar(50) DEFAULT NULL COMMENT '\n            \n            ',
  `trobule_level` varchar(50) DEFAULT NULL COMMENT '可忽略隐患、较大隐患、中度隐患、重大隐患、巨大隐患',
  `invest_item` varchar(50) DEFAULT NULL,
  `invest_content` varchar(100) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`trouble_code`),
  KEY `FK_Reference_4` (`div_item_code`),
  CONSTRAINT `FK_Reference_4` FOREIGN KEY (`div_item_code`) REFERENCES `eng_division` (`div_item_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='记录所有类型的工程的所有隐患，构成隐患清单\n';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `item_cons_scheme`
--

DROP TABLE IF EXISTS `item_cons_scheme`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_cons_scheme` (
  `scheme_code` varchar(50) NOT NULL COMMENT '施工方案编码',
  `div_item_code` varchar(50) DEFAULT NULL COMMENT '分项工程编码',
  `div_level` char(1) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL COMMENT '施工方案名称',
  `sch_type` varchar(50) DEFAULT NULL COMMENT '施工方案类型',
  `paths` varchar(500) DEFAULT NULL COMMENT '方案附件路径',
  `description` varchar(500) DEFAULT NULL COMMENT '说明',
  PRIMARY KEY (`scheme_code`),
  KEY `FK_Reference_3` (`div_item_code`),
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`div_item_code`) REFERENCES `eng_division` (`div_item_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='一般情况下为分项工程施工方案，但允许其它划分。';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sub_div_work`
--

DROP TABLE IF EXISTS `sub_div_work`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sub_div_work` (
  `sub_div_code` varchar(50) NOT NULL COMMENT '分项工程编码',
  `name` varchar(50) DEFAULT NULL COMMENT '项目名称',
  `charact_des` varchar(500) DEFAULT NULL COMMENT '项目特征描述',
  `unit` varchar(50) DEFAULT NULL COMMENT '计量单位',
  `number` double DEFAULT NULL COMMENT '工程量',
  `comp_unit_price` double DEFAULT NULL COMMENT '综合单价',
  `temporary_measure_price` double DEFAULT NULL COMMENT '暂估价',
  `quota_manual_fee` double DEFAULT NULL COMMENT '定额人工费',
  PRIMARY KEY (`sub_div_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分项工程清单信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sub_div_work_quota`
--

DROP TABLE IF EXISTS `sub_div_work_quota`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sub_div_work_quota` (
  `res_code` varchar(50) NOT NULL,
  `sub_div_code` varchar(50) DEFAULT NULL COMMENT '分项工程编码',
  `quota_code` varchar(50) DEFAULT NULL COMMENT '定额编码',
  `item_name` varchar(50) DEFAULT NULL COMMENT '项目名称',
  `item_detail_name` varchar(50) DEFAULT NULL COMMENT '项目细分名称',
  `resources_type` varchar(50) DEFAULT NULL COMMENT '资源类型为：人工、材料、机械',
  `res_type_level` varchar(50) DEFAULT NULL,
  `res_detail_type` varchar(50) DEFAULT NULL COMMENT '资源类型细分',
  `unit` varchar(50) DEFAULT NULL COMMENT '资源单位',
  `used_num` double DEFAULT NULL COMMENT '单位分项工程的资源消耗量。',
  `loss_rate` double DEFAULT NULL COMMENT '对于物资为损耗率，对于人工，为人工幅度差系数，对于机械设备为机械幅度差系数。',
  `save_excess_rate` double DEFAULT NULL,
  PRIMARY KEY (`res_code`),
  KEY `FK_Reference_1` (`sub_div_code`),
  CONSTRAINT `FK_Reference_1` FOREIGN KEY (`sub_div_code`) REFERENCES `sub_div_work` (`sub_div_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分项工程定额数据表';
/*!40101 SET character_set_client = @saved_cs_client */;
