/*
SQLyog Community v13.3.0 (64 bit)
MySQL - 8.0.18 : Database - rmt
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`rmt` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `rmt`;

/*Table structure for table `population` */

DROP TABLE IF EXISTS `population`;

CREATE TABLE `population` (
  `jmbg` varchar(14) NOT NULL,
  `passport` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `lastname` varchar(50) NOT NULL,
  PRIMARY KEY (`jmbg`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `population` */

insert  into `population`(`jmbg`,`passport`,`name`,`lastname`) values 
('0000000000000','88888888','Marko','Markovic'),
('2222222222222','77777777','Zika','Zikic'),
('2611002154214','11111111','Aco','Brajic');

/*Table structure for table `trip` */

DROP TABLE IF EXISTS `trip`;

CREATE TABLE `trip` (
  `name` varchar(50) NOT NULL,
  `lastname` varchar(50) NOT NULL,
  `jmbg` varchar(50) NOT NULL,
  `passport` varchar(50) NOT NULL,
  `destination` varchar(50) NOT NULL,
  `entrydate` date NOT NULL,
  `exitdate` date NOT NULL,
  `transport` varchar(50) NOT NULL,
  `idtrip` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`idtrip`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `trip` */

insert  into `trip`(`name`,`lastname`,`jmbg`,`passport`,`destination`,`entrydate`,`exitdate`,`transport`,`idtrip`) values 
('Stevo','Braja','0203099265125','11111211','Spanija','2024-12-15','2024-12-25','BUS',1),
('Stevo','Stevo','020399154214','25252522','Grcka','2024-12-17','2024-12-25','PLANE',2),
('hahha','hahha','25120036666','22222222','Njemacka','2024-12-25','2024-12-30','MOTORBIKE',3),
('Aco','Aco','2611002154214','111111111','aaa','2024-12-15','2024-12-25','CAR',4),
('Aco','Aco','2611002154214','11111111','Spanijah','2025-12-12','2025-12-15','BUS',5),
('Aco','Brajic','2611002154214','11111111','Grcka','2025-02-15','2025-03-15','MOTORBIKE',6),
('Aco','Aco','2611003154214','22222222','Grcka','2024-12-25','2024-12-30','CAR',7),
('asd','asd','asd','sasasa','asd','2024-12-15','2024-12-25','CAR',8),
('Aco','Brajic','2611002154214','11111111','Kazahstan','2024-12-16','2024-12-28','CAR',9),
('Aco','Brajic','2611002154214','11111111','HAHA','2024-12-18','2024-12-25','CAR',10),
('Aco','Brajic','2611002154214','11111111','Hhii','2024-12-25','2024-12-29','CAR',11),
('Aco','Brajic','2611002154214','11111111','Himalaji','2025-12-12','2025-12-15','CAR',12),
('Marko','Markovic','0000000000000','88888888','Spanija','2025-01-12','2025-01-17','CAR',13),
('Marko','Markovic','0000000000000','88888888','Italija','2025-11-15','2025-11-25','CAR',14),
('Aco','Brajic','2611002154214','11111111','Hrvatska','2025-02-12','2025-02-17','CAR',15),
('Aco','Brajic','2611002154214','11111111','Njemacka','2025-03-12','2025-03-25','MOTORBIKE',16);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `jmbg` varchar(14) NOT NULL,
  `name` varchar(50) NOT NULL,
  `lastname` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `passportno` varchar(50) NOT NULL,
  PRIMARY KEY (`jmbg`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `user` */

insert  into `user`(`jmbg`,`name`,`lastname`,`email`,`username`,`password`,`passportno`) values 
('2611002154214','Aco','Brajic','akibrajic@gmail.com','aco','aco','11111111'),
('4454454','stevo','stevo','aaa','a','a','22222');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
