/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.5.40-log : Database - schedule
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`schedule` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `schedule`;

/*Table structure for table `dict` */

DROP TABLE IF EXISTS `dict`;

CREATE TABLE `dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `types` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `descs` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `beginweek` int(11) DEFAULT NULL,
  `endweek` int(11) DEFAULT NULL,
  `period` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

/*Data for the table `dict` */

insert  into `dict`(`id`,`types`,`descs`,`beginweek`,`endweek`,`period`) values (13,'高数','123',2,3,1),(14,'线代','1234',4,6,NULL),(15,'马哲','1234',8,10,NULL),(17,'马经','1114444',1,5,NULL),(19,'物理','1234',NULL,NULL,3),(20,'体育','333',NULL,NULL,4),(21,'C语言','111',NULL,NULL,3),(22,'数据库','33',NULL,NULL,8),(23,'心理健康','1234',2,4,NULL);

/*Table structure for table `major` */

DROP TABLE IF EXISTS `major`;

CREATE TABLE `major` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `content` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `type` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `classes` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

/*Data for the table `major` */

insert  into `major`(`id`,`pid`,`name`,`content`,`type`,`classes`) values (1,NULL,'计算机学院','测试1111','college',NULL),(2,NULL,'文学院','123123','college',NULL),(3,NULL,'法学院','12344444455','college',NULL),(5,2,'古文学','1234444','major','马哲,马经,体育'),(6,1,'信息技术','123','major','高数,线代,马哲,C语言,数据库'),(8,NULL,'测试学院','111','college',NULL),(10,2,'测试专业','1234','major','高数,马哲,体育,C语言,数据库'),(11,1,'通讯工程','123','major','高数,体育,C语言,数据库,心理健康'),(12,NULL,'医学院','12314123','college',NULL),(13,12,'心理健康','1234123','major','高数,马哲,物理,体育,C语言,数据库'),(14,1,'医疗物流项目','asdas','major','高数,线代,马哲,马经,物理'),(15,NULL,'地理信息学院','123','college',NULL);

/*Table structure for table `room` */

DROP TABLE IF EXISTS `room`;

CREATE TABLE `room` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `address` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `personcount` int(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

/*Data for the table `room` */

insert  into `room`(`id`,`name`,`address`,`personcount`) values (5,'11号教室','3-201',NULL),(6,'12号教室','1234',NULL),(7,'测试教室','5号楼1111',NULL),(8,'13号教室','3-203',NULL);

/*Table structure for table `temp` */

DROP TABLE IF EXISTS `temp`;

CREATE TABLE `temp` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roomid` int(11) DEFAULT NULL,
  `a1` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `a2` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `a3` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `a4` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `b1` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `b2` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `b3` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `b4` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `c1` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `c2` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `c3` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `c4` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `d1` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `d2` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `d3` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `d4` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `e1` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `e2` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `e3` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `e4` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

/*Data for the table `temp` */

insert  into `temp`(`id`,`roomid`,`a1`,`a2`,`a3`,`a4`,`b1`,`b2`,`b3`,`b4`,`c1`,`c2`,`c3`,`c4`,`d1`,`d2`,`d3`,`d4`,`e1`,`e2`,`e3`,`e4`) values (1,NULL,'周一_1','周一_2','周一_3','周一_4','周二_1','周二_2','周二_3','周二_4','周三_1','周三_2','周三_3','周三_4','周四_1','周四_2','周四_3','周四_4','周五_1','周五_2','周五_3','周五_4');

/*Table structure for table `temp1` */

DROP TABLE IF EXISTS `temp1`;

CREATE TABLE `temp1` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `week` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `classes` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

/*Data for the table `temp1` */

insert  into `temp1`(`id`,`week`,`classes`) values (1,'周一','1'),(2,'周一','2'),(3,'周一','3'),(4,'周一','4'),(5,'周二','1'),(6,'周二','2'),(7,'周二','3'),(8,'周二','4'),(9,'周三','1'),(10,'周三','2'),(11,'周三','3'),(12,'周三','4'),(13,'周四','1'),(14,'周四','2'),(15,'周四','3'),(16,'周四','4'),(17,'周五','1'),(18,'周五','2'),(19,'周五','3'),(20,'周五','4');

/*Table structure for table `timetable` */

DROP TABLE IF EXISTS `timetable`;

CREATE TABLE `timetable` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `majorid` int(255) DEFAULT NULL,
  `classes` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `teacherid` int(11) DEFAULT NULL,
  `roomid` int(11) DEFAULT NULL,
  `week` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `time` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `start` int(11) DEFAULT NULL,
  `end` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=793 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

/*Data for the table `timetable` */

insert  into `timetable`(`id`,`majorid`,`classes`,`teacherid`,`roomid`,`week`,`time`,`start`,`end`) values (764,14,'马经',42,8,'周五','2',1,5),(765,13,'体育',44,8,'周五','2',11,14),(766,10,'高数',37,8,'周五','3',2,3),(767,14,'高数',37,8,'周五','3',2,3),(768,6,'线代',40,8,'周五','3',4,6),(769,5,'马经',42,8,'周五','4',1,5),(770,14,'马哲',41,8,'周五','2',8,10),(771,10,'体育',44,8,'周五','3',11,14),(772,14,'线代',40,8,'周五','2',4,6),(773,6,'C语言',45,8,'周五','4',11,13),(774,14,'物理',43,8,'周五','4',21,23),(775,11,'高数',37,8,'周五','1',2,3),(776,13,'C语言',45,8,'周五','4',5,7),(777,10,'马哲',41,8,'周五','4',8,10),(778,11,'C语言',45,8,'周五','4',18,20),(779,10,'C语言',45,8,'周五','2',15,17),(780,6,'马哲',41,8,'周五','3',8,10),(781,5,'体育',44,8,'周五','4',14,17),(782,6,'高数',37,8,'周五','2',2,3),(783,5,'马哲',41,8,'周五','4',8,10),(784,13,'物理',43,8,'周五','4',11,13),(785,11,'数据库',46,8,'周五','3',15,22),(786,13,'数据库',46,8,'周五','2',15,22),(787,10,'数据库',46,8,'周五','1',4,11),(788,13,'高数',37,8,'周五','1',2,3),(789,11,'体育',44,8,'周五','2',11,14),(790,6,'数据库',46,8,'周五','4',14,21),(791,13,'马哲',41,8,'周五','2',8,10),(792,11,'心理健康',48,8,'周五','4',2,4);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `no` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `username` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `birthday` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `tel` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `sex` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `post` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `isadmin` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `status` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `college` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `major` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `classes` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

/*Data for the table `user` */

insert  into `user`(`id`,`no`,`username`,`password`,`name`,`birthday`,`tel`,`sex`,`post`,`email`,`isadmin`,`status`,`college`,`major`,`classes`) values (1,NULL,'admin','123','超管','444333','123',NULL,NULL,'2222','0',NULL,NULL,NULL,NULL),(35,'0001','stu','123','测试学生','2022-01-08','12311111111','男',NULL,'123@23.COM','2',NULL,'2','5',NULL),(36,'0002','stu1','123','ceshi1','2020-03-01','11111','女',NULL,'2222','2',NULL,'1','11','14'),(37,NULL,'tea','123','毛概老师','2020-03-01','12311111111','男','组长','123@23.COM','1',NULL,NULL,NULL,'高数'),(38,'0003','stu2','123','测试学生3','2020-03-01','12311111111','男',NULL,'123@23.COM','2',NULL,'2','10','17'),(39,'0004','stu3','123','测试女生','2020-03-01','13411111111','女',NULL,'123@23.COM','2',NULL,'1','6','13'),(40,NULL,'tea1','123','高数老师1','2020-03-01','123','男','123','123','1',NULL,NULL,NULL,'线代'),(41,NULL,'tea2','123','马哲老师','2020-03-01','12311111111','男','123','123@23.COM','1',NULL,NULL,NULL,'马哲'),(42,NULL,'tea2','123','邓论老师','2020-03-01','123','男','123','123','1',NULL,NULL,NULL,'马经'),(43,NULL,'tea4','123','计算机科学与技术老师','2020-03-02','123','男','123','123','1',NULL,NULL,NULL,'物理'),(44,NULL,'tea5','123','语言老师','2020-03-03','12311111111','男','123','123','1',NULL,NULL,NULL,'体育'),(45,NULL,'tea6','123','数据库老师','2020-03-04','123','男','123','123','1',NULL,NULL,NULL,'C语言'),(46,NULL,'tea7','123','心理学老师','2020-03-02','123','男','123','123@23.COM','1',NULL,NULL,NULL,'数据库'),(47,'0000001','stu99','123','测试99','2020-03-08','123','男',NULL,'123344223@23.COM','2',NULL,'2','10',NULL),(48,NULL,'tea10','123','心理健康老师','2020-04-26','111','男','教研组长','123@23.COM','1',NULL,NULL,NULL,'心理健康');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
