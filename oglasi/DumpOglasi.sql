CREATE DATABASE  IF NOT EXISTS `rzk` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `rzk`;
-- MySQL dump 10.13  Distrib 5.5.32, for Linux (x86_64)
--
-- Host: nastava.is.pmf.uns.ac.rs    Database: rzk
-- ------------------------------------------------------
-- Server version	5.1.73

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
-- Table structure for table `Oglas`
--

DROP TABLE IF EXISTS `Oglas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Oglas` (
  `idOglas` int(11) NOT NULL AUTO_INCREMENT,
  `text` varchar(255) DEFAULT NULL,
  `brojPregleda` int(11) DEFAULT NULL,
  `idKorisnik` int(11) NOT NULL,
  PRIMARY KEY (`idOglas`),
  KEY `fk_Oglas_OglasKorisnik_idx` (`idKorisnik`),
  CONSTRAINT `fk_Oglas_OglasKorisnik` FOREIGN KEY (`idKorisnik`) REFERENCES `OglasKorisnik` (`idKorisnik`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Oglas`
--

LOCK TABLES `Oglas` WRITE;
/*!40000 ALTER TABLE `Oglas` DISABLE KEYS */;
INSERT INTO `Oglas` VALUES (1,'Tekst za neki oglas.',4,1),(2,'Tekst za jos neki oglas.',4,1),(3,'Opet tekst za oglas.',3,1),(4,'Neki novi oglas.',0,1),(5,'Jos neki novi oglas.',2,1);
/*!40000 ALTER TABLE `Oglas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `OglasPrijava`
--

DROP TABLE IF EXISTS `OglasPrijava`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `OglasPrijava` (
  `idPrijava` int(11) NOT NULL AUTO_INCREMENT,
  `text` varchar(45) DEFAULT NULL,
  `idOglas` int(11) NOT NULL,
  `idKorisnik` int(11) NOT NULL,
  PRIMARY KEY (`idPrijava`),
  KEY `fk_OglasPrijava_Oglas1_idx` (`idOglas`),
  KEY `fk_OglasPrijava_OglasKorisnik1_idx` (`idKorisnik`),
  CONSTRAINT `fk_OglasPrijava_Oglas1` FOREIGN KEY (`idOglas`) REFERENCES `Oglas` (`idOglas`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_OglasPrijava_OglasKorisnik1` FOREIGN KEY (`idKorisnik`) REFERENCES `OglasKorisnik` (`idKorisnik`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OglasPrijava`
--

LOCK TABLES `OglasPrijava` WRITE;
/*!40000 ALTER TABLE `OglasPrijava` DISABLE KEYS */;
INSERT INTO `OglasPrijava` VALUES (1,'Javljam se.',1,1),(2,'Samo tetki lek da odnesem.',4,1);
/*!40000 ALTER TABLE `OglasPrijava` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `OglasKorisnik`
--

DROP TABLE IF EXISTS `OglasKorisnik`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `OglasKorisnik` (
  `idKorisnik` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `nickname` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idKorisnik`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OglasKorisnik`
--

LOCK TABLES `OglasKorisnik` WRITE;
/*!40000 ALTER TABLE `OglasKorisnik` DISABLE KEYS */;
INSERT INTO `OglasKorisnik` VALUES (1,'rzk','rzk','Neki Korisnik');
/*!40000 ALTER TABLE `OglasKorisnik` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-12-04 10:58:48
