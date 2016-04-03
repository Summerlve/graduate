-- MySQL dump 10.13  Distrib 5.7.11, for osx10.11 (x86_64)
--
-- Host: localhost    Database: graduate
-- ------------------------------------------------------
-- Server version	5.7.11

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
-- Table structure for table `zh_area`
--

DROP TABLE IF EXISTS `zh_area`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zh_area` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `description` varchar(255) NOT NULL,
  `img` varchar(255) NOT NULL,
  `management_company` varchar(255) DEFAULT NULL,
  `building_num` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zh_area`
--

LOCK TABLES `zh_area` WRITE;
/*!40000 ALTER TABLE `zh_area` DISABLE KEYS */;
INSERT INTO `zh_area` VALUES (1,'小区1','1.金科观天下位于苏州相城区高铁新城澄阳路与南天成路交叉口，占地约6万方，总建19万方，小区绿化率达到37%\n\n2.以公园为基底，发扬“敬老、爱妻、亲子、睦邻、惜己”五大邻里主张，独创五大主题邻里景观，社区里的老人、大人、孩子各得其所。','1.jpg',NULL,NULL),(2,'小区2','3.社区专享亲子景观游泳池，家门口的水上世界。高品质景观串联丰富邻里交流空间，营造一个温馨的邻里生活住区。紧邻高铁北站和轻轨线\n\n4.结合项目及周边城市环境，通过多种布局方式的比较，从整体空间形态到建筑细节的精心推敲，不仅力争创造一个高品味、安全与健康的城市社区，同时营造一个符合绿色环保理念的可持续发展人居环境','2.jpg',NULL,NULL),(4,'小区3','3.社区专享亲子景观游泳池，家门口的水上世界。高品质景观串联丰富邻里交流空间，营造一个温馨的邻里生活住区。紧邻高铁北站和轻轨线\n\n4.结合项目及周边城市环境，通过多种布局方式的比较，从整体空间形态到建筑细节的精心推敲，不仅力争创造一个高品味、安全与健康的城市社区，同时营造一个符合绿色环保理念的可持续发展人居环境','3.jpg',NULL,NULL),(5,'小区4','3.社区专享亲子景观游泳池，家门口的水上世界。高品质景观串联丰富邻里交流空间，营造一个温馨的邻里生活住区。紧邻高铁北站和轻轨线\n\n4.结合项目及周边城市环境，通过多种布局方式的比较，从整体空间形态到建筑细节的精心推敲，不仅力争创造一个高品味、安全与健康的城市社区，同时营造一个符合绿色环保理念的可持续发展人居环境','4.jpg',NULL,NULL);
/*!40000 ALTER TABLE `zh_area` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-04-03 22:38:08
