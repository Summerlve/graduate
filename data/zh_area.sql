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
INSERT INTO `zh_area` VALUES (1,'小区1','万业湖墅金典规划为滨河居住区，斜港路南侧地块为规划中的文化娱乐用地，湖墅路东侧规划有城市绿地公园、教育配套设施以及社区公共设施。靠近轻轨2号线，站点是通达路站。','1.jpg',NULL,3),(2,'小区2','万业湖墅金典规划为滨河居住区，斜港路南侧地块为规划中的文化娱乐用地，湖墅路东侧规划有城市绿地公园、教育配套设施以及社区公共设施。靠近轻轨2号线，站点是通达路站。','2.jpg',NULL,3),(3,'小区3','万业湖墅金典规划为滨河居住区，斜港路南侧地块为规划中的文化娱乐用地，湖墅路东侧规划有城市绿地公园、教育配套设施以及社区公共设施。靠近轻轨2号线，站点是通达路站。','3.jpg',NULL,3),(4,'小区4','万业湖墅金典规划为滨河居住区，斜港路南侧地块为规划中的文化娱乐用地，湖墅路东侧规划有城市绿地公园、教育配套设施以及社区公共设施。靠近轻轨2号线，站点是通达路站。','4.jpg',NULL,3),(5,'小区5','万业湖墅金典规划为滨河居住区，斜港路南侧地块为规划中的文化娱乐用地，湖墅路东侧规划有城市绿地公园、教育配套设施以及社区公共设施。靠近轻轨2号线，站点是通达路站。','4.jpg',NULL,3);
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

-- Dump completed on 2016-04-20 14:13:53
