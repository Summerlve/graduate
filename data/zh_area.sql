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
  `territory` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zh_area`
--

LOCK TABLES `zh_area` WRITE;
/*!40000 ALTER TABLE `zh_area` DISABLE KEYS */;
INSERT INTO `zh_area` VALUES (1,'顶秀青溪','万业湖墅金典规划为滨河居住区，斜港路南侧地块为规划中的文化娱乐用地，湖墅路东侧规划有城市绿地公园、教育配套设施以及社区公共设施。靠近轻轨2号线，站点是通达路站。','1.jpg','xxx物业公司',3,'江苏省徐州市泉山区'),(2,'中景濠庭','万业湖墅金典规划为滨河居住区，斜港路南侧地块为规划中的文化娱乐用地，湖墅路东侧规划有城市绿地公园、教育配套设施以及社区公共设施。靠近轻轨2号线，站点是通达路站。','2.jpg','无',3,'江苏省徐州市泉山区'),(3,'文庭雅苑','万业湖墅金典规划为滨河居住区，斜港路南侧地块为规划中的文化娱乐用地，湖墅路东侧规划有城市绿地公园、教育配套设施以及社区公共设施。靠近轻轨2号线，站点是通达路站。','3.jpg','无',3,'江苏省徐州市泉山区'),(4,'美景天城','万业湖墅金典规划为滨河居住区，斜港路南侧地块为规划中的文化娱乐用地，湖墅路东侧规划有城市绿地公园、教育配套设施以及社区公共设施。靠近轻轨2号线，站点是通达路站。','4.jpg','无',3,'江苏省苏州市高新区'),(5,'绣江南','万业湖墅金典规划为滨河居住区，斜港路南侧地块为规划中的文化娱乐用地，湖墅路东侧规划有城市绿地公园、教育配套设施以及社区公共设施。靠近轻轨2号线，站点是通达路站。','4.jpg','无',3,'江苏省苏州市高新区'),(10,'季景沁园','万业湖墅金典规划为滨河居住区，斜港路南侧地块为规划中的文化娱乐用地，湖墅路东侧规划有城市绿地公园、教育配套设施以及社区公共设施。靠近轻轨2号线，站点是通达路站。','51f5134a-0f2e-40b8-b61a-580d67252054.jpg','无',1,'江苏省苏州市高新区'),(11,'顺驰蓝调国际','万业湖墅金典规划为滨河居住区，斜港路南侧地块为规划中的文化娱乐用地，湖墅路东侧规划有城市绿地公园、教育配套设施以及社区公共设施。靠近轻轨2号线，站点是通达路站。','edad0228-4fb5-4c29-9e4b-38938b4b36f4.jpg','无',2,'江苏省苏州市高新区'),(16,'世外桃苑','万业湖墅金典规划为滨河居住区，斜港路南侧地块为规划中的文化娱乐用地，湖墅路东侧规划有城市绿地公园、教育配套设施以及社区公共设施。靠近轻轨2号线，站点是通达路站。','c6d9e3a0-d9cb-4023-905f-c288ba65aabf.jpg','无',1,'江苏省徐州市泉山区'),(17,'测试用','万业湖墅金典规划为滨河居住区，斜港路南侧地块为规划中的文化娱乐用地，湖墅路东侧规划有城市绿地公园、教育配套设施以及社区公共设施。靠近轻轨2号线，站点是通达路站。','d81b3f1b-3b05-46f8-9ebc-908c291b36a7.jpg','无',1,'江苏省徐州市泉山区');
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

-- Dump completed on 2016-05-11 16:41:11
