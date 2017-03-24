-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: kainpos
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
-- Table structure for table `foodtable`
--

DROP TABLE IF EXISTS `foodtable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `foodtable` (
  `idMenuForToday` int(11) NOT NULL,
  `Description` varchar(45) DEFAULT NULL,
  `Price` double DEFAULT NULL,
  `isTodaysMenu` int(11) DEFAULT NULL,
  PRIMARY KEY (`idMenuForToday`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foodtable`
--

LOCK TABLES `foodtable` WRITE;
/*!40000 ALTER TABLE `foodtable` DISABLE KEYS */;
/*!40000 ALTER TABLE `foodtable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `studentinfo`
--

DROP TABLE IF EXISTS `studentinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `studentinfo` (
  `idStudentInfo` int(11) NOT NULL,
  `Firstname` varchar(45) DEFAULT NULL,
  `Middlename` varchar(45) DEFAULT NULL,
  `Lastname` varchar(45) DEFAULT NULL,
  `StudentCode` varchar(45) DEFAULT NULL,
  `FoodCredit` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idStudentInfo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studentinfo`
--

LOCK TABLES `studentinfo` WRITE;
/*!40000 ALTER TABLE `studentinfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `studentinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `systemusers`
--

DROP TABLE IF EXISTS `systemusers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `systemusers` (
  `UserId` int(11) NOT NULL AUTO_INCREMENT,
  `Username` varchar(45) DEFAULT NULL,
  `Password` varchar(45) DEFAULT NULL,
  `UserTypeId` varchar(45) DEFAULT NULL,
  `SystemUserType_idSystemUserType` int(11) NOT NULL,
  PRIMARY KEY (`UserId`),
  UNIQUE KEY `UserId_UNIQUE` (`UserId`),
  KEY `fk_SystemUsers_SystemUserType_idx` (`SystemUserType_idSystemUserType`),
  CONSTRAINT `fk_SystemUsers_SystemUserType` FOREIGN KEY (`SystemUserType_idSystemUserType`) REFERENCES `systemusertype` (`idSystemUserType`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `systemusers`
--

LOCK TABLES `systemusers` WRITE;
/*!40000 ALTER TABLE `systemusers` DISABLE KEYS */;
INSERT INTO `systemusers` VALUES (1,'admin','admin','1',1),(2,'guest','guest','1',1);
/*!40000 ALTER TABLE `systemusers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `systemusertype`
--

DROP TABLE IF EXISTS `systemusertype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `systemusertype` (
  `idSystemUserType` int(11) NOT NULL,
  `Description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idSystemUserType`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `systemusertype`
--

LOCK TABLES `systemusertype` WRITE;
/*!40000 ALTER TABLE `systemusertype` DISABLE KEYS */;
INSERT INTO `systemusertype` VALUES (1,'Admin');
/*!40000 ALTER TABLE `systemusertype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transactions`
--

DROP TABLE IF EXISTS `transactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transactions` (
  `idTransactions` int(11) NOT NULL,
  `Username` varchar(45) DEFAULT NULL,
  `StudentCode` varchar(45) DEFAULT NULL,
  `idMenuForToday` int(11) DEFAULT NULL,
  `DateOfTransaction` varchar(45) DEFAULT NULL,
  `TransactionAmount` varchar(45) DEFAULT NULL,
  `FoodTable_idMenuForToday` int(11) NOT NULL,
  `StudentInfo_idStudentInfo` int(11) NOT NULL,
  PRIMARY KEY (`idTransactions`),
  KEY `fk_Transactions_FoodTable1_idx` (`FoodTable_idMenuForToday`),
  KEY `fk_Transactions_StudentInfo1_idx` (`StudentInfo_idStudentInfo`),
  CONSTRAINT `fk_Transactions_FoodTable1` FOREIGN KEY (`FoodTable_idMenuForToday`) REFERENCES `foodtable` (`idMenuForToday`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Transactions_StudentInfo1` FOREIGN KEY (`StudentInfo_idStudentInfo`) REFERENCES `studentinfo` (`idStudentInfo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transactions`
--

LOCK TABLES `transactions` WRITE;
/*!40000 ALTER TABLE `transactions` DISABLE KEYS */;
/*!40000 ALTER TABLE `transactions` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-03-24 10:56:14
