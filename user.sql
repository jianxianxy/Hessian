USE `java`;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_name` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '员工姓名',
  `password` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '密码',
  `number` varchar(24) COLLATE utf8_bin NOT NULL COMMENT '员工编号',
  `role` int(2) NOT NULL COMMENT '角色(1:开发  2:测试  3:运维  4:管理员)',
  `email` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '员工邮箱',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '组状态: 1 启用 0 停用',
  `created_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `_number_index` (`number`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `user` */

insert  into `user`(`id`,`user_name`,`password`,`number`,`role`,`email`,`status`,`created_at`) values (1,'宋扬(600591)','96e79218965eb72c92a549dd5a330112','600591',2,'songyang600591@sf-express.com',1,'2015-12-10 15:17:05'),(2,'黄中雷','96e79218965eb72c92a549dd5a330112','686107',2,'huangzhonglei@sf-express.com',1,'2015-12-10 16:42:27'),(3,'余浩苗','96e79218965eb72c92a549dd5a330112','698549',2,'yu.haomiao@sf-express.com',1,'2015-12-20 22:22:07'),(4,'admin','96e79218965eb72c92a549dd5a330112','000000',1,'admin@admin.com',1,'2016-02-22 11:10:49'),(5,'周亚玲','e3fa1c517aef0b557d03ce507d055e73','824529',3,'zhou.yaling@sf-express.com',1,'2016-02-22 11:18:43'),(6,'陈涛','ddf3f248634e81954ad9d8813b0f35f0','696193',3,'chentao696193@sf-express.com',1,'2016-02-22 11:19:52'),(7,'王昀嫣','77b52af23ec32342098a14423d364f20','357085',1,'wangyunyan@sf-express.com',1,'2016-02-22 11:22:43'),(8,'李泽明','b2890856220545390be476b62431e40c','717165',1,'lizeming@sf-express.com',1,'2016-02-22 11:23:39'),(9,'周军龙','442d8e51d7195b738ee9d73f600b9213','835038',1,'zhoujunlong@sf-express.com',1,'2016-02-22 11:24:20'),(11,'商业BG-优选-研发中心运维组','04fc711301f3c784d66955d98d399afb','000001',2,'sfbestsa@sf-express.com',1,'2016-02-22 14:43:36'),(12,'商业BG-优选-研发中心测试组','768c1c687efe184ae6dd2420710b8799','000002',2,'sfbesttest@sf-express.com',1,'2016-02-22 14:44:13');
