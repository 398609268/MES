/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.7.31 : Database - mes
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`mes` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `mes`;

/*Table structure for table `factoryinfo` */

DROP TABLE IF EXISTS `factoryinfo`;

CREATE TABLE `factoryinfo` (
  `id` int(24) NOT NULL AUTO_INCREMENT,
  `PN` varchar(24) DEFAULT NULL,
  `SN` varchar(24) DEFAULT NULL,
  `MN` varchar(24) DEFAULT NULL,
  `IMEI` varchar(30) DEFAULT NULL,
  `CCID` varchar(30) DEFAULT NULL,
  `tableId` varchar(30) DEFAULT NULL,
  `banhao` varchar(30) DEFAULT NULL,
  `test2` varchar(30) DEFAULT NULL,
  `test3` varchar(30) DEFAULT NULL,
  `test4` varchar(30) DEFAULT NULL,
  `test5` varchar(30) DEFAULT NULL,
  `station` int(6) DEFAULT NULL,
  `status` int(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `SN` (`SN`),
  UNIQUE KEY `MN` (`MN`),
  KEY `IMEI` (`IMEI`),
  KEY `CCID` (`CCID`),
  KEY `order` (`tableId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
