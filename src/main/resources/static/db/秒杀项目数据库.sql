/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.5.61-MariaDB : Database - miaosha
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`miaosha` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `miaosha`;

/*Table structure for table `item` */

DROP TABLE IF EXISTS `item`;

CREATE TABLE `item` (
  `id` int(64) NOT NULL AUTO_INCREMENT,
  `title` varchar(64) NOT NULL,
  `price` decimal(10,2) NOT NULL DEFAULT '0.00',
  `description` varchar(500) NOT NULL,
  `sales` int(64) DEFAULT '0',
  `img_url` varchar(4000) NOT NULL COMMENT '描述图片地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

/*Data for the table `item` */

insert  into `item`(`id`,`title`,`price`,`description`,`sales`,`img_url`) values (16,'华为P30pro','6000.00','安卓机皇',34,'https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2063925784,893017630&fm=26&gp=0.jpg'),(17,'iphone11','9000.00','最新的苹果手机',113,'https://res.vmallres.com/pimages//product/6901443293513/428_428_1555464685019mp.png'),(18,'小米9','19999.00','雷军疯了',12,'https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3469288676,2126914832&fm=26&gp=0.jpg');

/*Table structure for table `item_stock` */

DROP TABLE IF EXISTS `item_stock`;

CREATE TABLE `item_stock` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stock` int(11) NOT NULL DEFAULT '0',
  `item_id` int(64) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `item` (`item_id`),
  CONSTRAINT `item` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `item_stock` */

insert  into `item_stock`(`id`,`stock`,`item_id`) values (6,110,16),(7,66,17),(8,18,18);

/*Table structure for table `orders` */

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `id` varchar(32) NOT NULL,
  `user_id` int(64) NOT NULL,
  `item_id` int(64) NOT NULL,
  `order_price` decimal(10,2) DEFAULT '0.00' COMMENT '购买时商品单价',
  `amount` int(11) DEFAULT '0' COMMENT '订单商品数量',
  `order_amount` decimal(10,2) DEFAULT '0.00' COMMENT '订单总额',
  `promo_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `orders` */

insert  into `orders`(`id`,`user_id`,`item_id`,`order_price`,`amount`,`order_amount`,`promo_id`) values ('2019092600000000',6,16,'6000.00',1,'6000.00',0),('2019092600000100',6,16,'6000.00',1,'6000.00',0),('2019092600000200',6,17,'9000.00',34,'306000.00',0),('2019092600000300',6,17,'9000.00',34,'306000.00',0),('2019092600000400',6,18,'19999.00',9,'179991.00',0),('2019092600000500',6,18,'19999.00',1,'19999.00',0),('2019092600000600',6,18,'19999.00',1,'19999.00',0),('2019092700000700',6,16,'100.00',1,'100.00',1);

/*Table structure for table `promo` */

DROP TABLE IF EXISTS `promo`;

CREATE TABLE `promo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `promo_name` varchar(32) NOT NULL COMMENT '秒杀活动的商品名称',
  `start_date` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '秒杀活动开始时间',
  `item_id` int(11) NOT NULL,
  `promo_item_price` decimal(10,2) NOT NULL COMMENT '秒杀时的商品价格',
  `end_date` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `promo` */

insert  into `promo`(`id`,`promo_name`,`start_date`,`item_id`,`promo_item_price`,`end_date`) values (1,'p30限时秒杀','2019-09-27 13:46:15',16,'100.00','2019-09-27 16:51:49'),(2,'随便','2019-09-28 13:54:05',17,'10.00','2019-09-29 13:54:16'),(3,'家伙','2019-09-27 13:56:46',18,'5.00','2019-09-27 13:57:09');

/*Table structure for table `sequence_info` */

DROP TABLE IF EXISTS `sequence_info`;

CREATE TABLE `sequence_info` (
  `name` varchar(64) NOT NULL,
  `current_value` int(11) NOT NULL DEFAULT '0',
  `step` int(11) NOT NULL DEFAULT '1',
  `max_value` int(11) NOT NULL DEFAULT '999999',
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sequence_info` */

insert  into `sequence_info`(`name`,`current_value`,`step`,`max_value`) values ('order_info',8,1,999999);

/*Table structure for table `user_info` */

DROP TABLE IF EXISTS `user_info`;

CREATE TABLE `user_info` (
  `id` int(64) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `gender` int(8) NOT NULL DEFAULT '0' COMMENT '//1代表男性,0代表女性',
  `age` int(11) NOT NULL,
  `phone` varchar(64) NOT NULL,
  `register_mode` varchar(64) NOT NULL COMMENT '//byphone,bywechat,byalipay',
  `third_party_id` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `telphone_unique` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `user_info` */

insert  into `user_info`(`id`,`name`,`gender`,`age`,`phone`,`register_mode`,`third_party_id`) values (1,'tom',1,12,'15645866051','byphone','4515'),(6,'admin',1,22,'123456','BYphone',NULL);

/*Table structure for table `user_password` */

DROP TABLE IF EXISTS `user_password`;

CREATE TABLE `user_password` (
  `id` int(64) NOT NULL AUTO_INCREMENT,
  `encrpt_password` varchar(128) NOT NULL COMMENT '加密密码',
  `user_id` int(64) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `userID` (`user_id`),
  CONSTRAINT `userID` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `user_password` */

insert  into `user_password`(`id`,`encrpt_password`,`user_id`) values (1,'qwertyuiop',1),(2,'098f6bcd4621d373cade4e832627b4f6',6);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
