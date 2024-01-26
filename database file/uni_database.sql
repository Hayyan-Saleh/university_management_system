CREATE DATABASE  IF NOT EXISTS `uni_database` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `uni_database`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: uni_database
-- ------------------------------------------------------
-- Server version	8.0.34

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `degrees`
--

DROP TABLE IF EXISTS `degrees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `degrees` (
  `id` int NOT NULL,
  `m1` int DEFAULT NULL,
  `m2` int DEFAULT NULL,
  `m3` int DEFAULT NULL,
  `m4` int DEFAULT NULL,
  `m5` int DEFAULT NULL,
  `m6` int DEFAULT NULL,
  `sum` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `degrees_ibfk_1` FOREIGN KEY (`id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `degrees`
--

LOCK TABLES `degrees` WRITE;
/*!40000 ALTER TABLE `degrees` DISABLE KEYS */;
INSERT INTO `degrees` VALUES (8,134,1234,23,58,585,98,2132),(9,134,1234,23,58,585,98,2132),(10,34,34,34,54,45,45,246),(11,1,1,1,1,1,1,6),(12,20,22,22,22,22,22,303),(13,134,1234,23,58,585,98,2132),(16,134,1234,23,58,585,98,2132),(18,23,234,234,324,43,51,909),(19,23,43,23,43,23,43,198),(21,23,234,234,234,134,234,1093),(22,134,1234,23,58,585,98,2132),(23,33,33,33,33,33,33,198),(26,2,234,324,333,3,3,899),(29,32,23,43,33,343,34,508),(32,23,23,23,23,23,23,138),(35,32,23,32,233,23,32,375);
/*!40000 ALTER TABLE `degrees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(40) DEFAULT NULL,
  `last_name` varchar(40) DEFAULT NULL,
  `address` varchar(40) DEFAULT NULL,
  `department` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (7,'Ai','melhem','Mazza Javbl - 86 Madrase','NE'),(8,'Ali','Almakdoni','Baramke / Al Alhmad street / 32','PE'),(9,'Aya','Hamed','Sahat Al Zen - Mazza 86','PE'),(10,'Laith','Seroni','Bab Al Jabe - Damascus / 24','AI'),(11,'Ahmed','Mohammad','Bab Al Jabe - Damascus / 24','AI'),(12,'Sarah','Khaled','Baramke / Al Alhmad street / 12','NE'),(13,'Ali','Halim','Bab Al Jabe - Damascus / 24','PE'),(14,'Nour','Omar','Baramke / Al Assad street / 34','AI'),(15,'Omar','Tareq','Sahat Al Zen - Mazza 86','NE'),(16,'Maryam','Khadija','Sahat Al Zen - Mazza 86s','PE'),(17,'Yusuf','Ibrahim','Damascus','AI'),(18,'Huda','Aisha','Damascus','NE'),(19,'Khaled','Mahmoud','Damascus','PE'),(20,'Samah','Fatima','Damascus','AI'),(21,'Fadi','Hassan','Damascus','NE'),(22,'Reema','Zainab','Damascus','PE'),(23,'Hussam','Jamal','Mazza Javbl - 86 Madrase','AI'),(24,'Leen','Alaa','Damascus','NE'),(25,'Mohammad','Ahmed','Mazza Javbl - 86 Madrase','PE'),(26,'Sara','Khaled','Damascus','AI'),(27,'Ali','Hafiz','Damascus','NE'),(28,'Nour','Omar','Damascus','PE'),(29,'Omar','Tareq','Damascus','AI'),(30,'Maryam','Khadija','Damascus','NE'),(31,'Yusuf','Ibrahim','Damascus','PE'),(32,'Hala','Ahmad','Souq Methat Basha - 58/ Al Barkr','PE'),(33,'Ahmad ','mdallal','Damascus - sahat al Mohafatha - /4','NE'),(34,'1','1','1',''),(35,'Haider','Ahmad','Damascus - Mazzeh / salm al alim street','AI');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_name` varchar(40) NOT NULL,
  `user_password` varchar(40) DEFAULT NULL,
  `department` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('Haider','1234','AI'),('Hayyan','0000','PE'),('Hussam','0','NE');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-26  3:28:44
