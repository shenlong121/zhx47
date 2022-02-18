/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.7.35-log : Database - qxx
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE = ''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS */`qxx` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `qxx`;

/*Table structure for table `activation_code` */
DROP TABLE IF EXISTS `activation_code`;
CREATE TABLE `activation_code`
(
    `id`          int(11)    NOT NULL AUTO_INCREMENT COMMENT '激活码ID',
    `code`        varchar(50) DEFAULT NULL COMMENT '激活码',
    `day`         int(11)     DEFAULT NULL COMMENT '激活天数',
    `enable`      int(1)      DEFAULT '0' COMMENT '是否使用[0:未使用 1:已使用]',
    `create_user` bigint(20) NOT NULL COMMENT '创建用户',
    `create_time` datetime   NOT NULL COMMENT '创建时间',
    `update_user` bigint(20)  DEFAULT NULL COMMENT '更新用户',
    `update_time` datetime    DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `activation_code_code_uindex` (`code`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 692
  DEFAULT CHARSET = utf8 COMMENT ='激活码';

/*Table structure for table `persistent_logins` */
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins`
(
    `username`  varchar(64) NOT NULL,
    `series`    varchar(64) NOT NULL,
    `token`     varchar(64) NOT NULL,
    `last_used` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`series`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*Table structure for table `platform_info` */
DROP TABLE IF EXISTS `platform_info`;
CREATE TABLE `platform_info`
(
    `type`        varchar(50) NOT NULL COMMENT '类型',
    `key`         varchar(50) NOT NULL COMMENT '平台配置的key',
    `value`       blob COMMENT '平台配置的值',
    `desc`        varchar(50) DEFAULT NULL COMMENT '描述',
    `create_user` bigint(20)  NOT NULL COMMENT '创建用户',
    `create_time` datetime    NOT NULL COMMENT '创建时间',
    `update_user` bigint(20)  DEFAULT NULL COMMENT '更新用户',
    `update_time` datetime    DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`key`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='平台基础信息';
insert into `platform_info`(`type`, `key`, `value`, `desc`, `create_user`, `create_time`, `update_user`, `update_time`)
values ('alipay', 'alipay_public_key', '', '支付宝公钥', 1, '2021-12-31 08:43:09', NULL, NULL),
       ('alipay', 'app_id', '', '应用ID', 1, '2021-12-31 08:43:09', NULL, NULL),
       ('alipay', 'app_private_key', '', '应用私钥', 1, '2021-12-31 08:43:09', NULL, NULL),
       ('commission', 'bonusMonth1', '12', '好友充值一月会员提成', 1, '2021-12-29 16:51:00', NULL, NULL),
       ('commission', 'bonusMonth12', '80', '好友充值年费会员提成', 1, '2021-12-29 16:51:00', NULL, NULL),
       ('commission', 'bonusMonth3', '25', '好友充值三月会员提成', 1, '2021-12-29 16:51:00', NULL, NULL),
       ('commission', 'bonusMonth6', '50', '好友充值六月会员提成', 1, '2021-12-29 16:51:00', NULL, NULL),
       ('commission', 'bonusWeek1', '2', '好友充值一周会员提成', 1, '2021-12-29 16:51:00', NULL, NULL),
       ('system', 'file_path', '', '前端保存路径', 1, '2021-12-28 10:13:48', NULL, NULL),
       ('price', 'freeMonth1', '25', '会员价格：一月', 1, '2021-12-29 16:51:00', NULL, NULL),
       ('price', 'freeMonth12', '200', '会员价格：年费', 1, '2021-12-29 16:51:00', NULL, NULL),
       ('price', 'freeMonth3', '60', '会员价格：三月', 1, '2021-12-29 16:51:00', NULL, NULL),
       ('price', 'freeMonth6', '120', '会员价格：六月', 1, '2021-12-29 16:51:00', NULL, NULL),
       ('price', 'freeWeek1', '5', '会员价格：一周', 1, '2021-12-29 16:51:00', NULL, NULL),
       ('alipay', 'gateway', '', '支付网关', 1, '2021-12-31 08:43:09', NULL, NULL),
       ('price', 'priceMonth1', '25', '会员价格：一月', 1, '2021-12-29 16:51:00', NULL, NULL),
       ('price', 'priceMonth12', '200', '会员价格：年费', 1, '2021-12-29 16:51:00', NULL, NULL),
       ('price', 'priceMonth3', '60', '会员价格：三月', 1, '2021-12-29 16:51:00', NULL, NULL),
       ('price', 'priceMonth6', '120', '会员价格：六月', 1, '2021-12-29 16:51:00', NULL, NULL),
       ('price', 'priceWeek1', '5', '会员价格：一周', 1, '2021-12-29 16:51:00', NULL, NULL),
       ('system', 'qdd_cookie', '', '抢夕夕官方cookie', 1, '2021-12-22 00:00:00', NULL, NULL),
       ('system', 'qdd_password', '', '抢夕夕官方密码', 1, '2021-12-22 00:00:00', NULL, NULL),
       ('system', 'qdd_phone', '', '抢夕夕官方账号', 1, '2021-12-22 00:00:00', NULL, NULL),
       ('system', 'qdd_url', 'https://ppd.zhuohuaroofing.cn', '抢夕夕官方网址', 1, '2021-12-22 00:00:00', -1, '2021-12-28 16:17:33'),
       ('system', 'qxx_api', '', '抢夕夕后端地址', 1, '2021-12-30 10:42:25', NULL, NULL),
       ('system', 'qxx_front', '', '抢夕夕前端地址', 1, '2021-12-30 10:42:25', NULL, NULL),
       ('alipay', 'seller_id', '', '支付宝开放平台账号ID', 1, '2022-01-08 21:39:00', NULL, NULL);

/*Table structure for table `sys_config` */
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`
(
    `id`            bigint(15)   NOT NULL AUTO_INCREMENT COMMENT 'id',
    `version`       varchar(50)  NOT NULL DEFAULT '' COMMENT 'version',
    `version_apk`   varchar(50)  NOT NULL DEFAULT '' COMMENT 'versionApk',
    `version_ios`   varchar(50)  NOT NULL DEFAULT '' COMMENT 'versionIos',
    `check_version` int(11)      NOT NULL DEFAULT '-1' COMMENT 'checkVersion',
    `jiguang`       varchar(100) NOT NULL DEFAULT '' COMMENT 'jiguang',
    `juhe_key`      varchar(100) NOT NULL DEFAULT '' COMMENT 'juheKey',
    `juhe`          varchar(100) NOT NULL DEFAULT '' COMMENT 'juhe',
    `domain`        varchar(100) NOT NULL DEFAULT '' COMMENT 'domain',
    `create_user`   bigint(20)   NOT NULL COMMENT '创建用户',
    `create_time`   datetime     NOT NULL COMMENT '创建时间',
    `update_user`   bigint(20)            DEFAULT NULL COMMENT '更新用户',
    `update_time`   datetime              DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8mb4 COMMENT ='系统设置';

/*Data for the table `sys_config` */
insert into `sys_config`(`id`, `version`, `version_apk`, `version_ios`, `check_version`, `jiguang`, `juhe_key`, `juhe`,
                         `domain`, `create_user`, `create_time`, `update_user`, `update_time`)
values (1, '3.9.99', '2.2.40', '2.2.0', 1, 'Basic MjFkNTY3Y2U4NTFmNDUxMDIwNzRmZDRhOjQwOWMyN2RkMzQ1ZWQ3OGMzNmRjYjRmZQ==',
        'fc5e345a0b074c5573334fc62597d7aa', '187735', 'http://127.0.0.1', 1, '2021-12-22 00:00:00', -1,
        '2022-02-10 20:33:37');

/*Table structure for table `sys_notice` */
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`
(
    `id`          bigint(15)    NOT NULL AUTO_INCREMENT COMMENT 'id',
    `is_on`       int(11)       NOT NULL DEFAULT '-1' COMMENT 'isOn',
    `title`       varchar(500)  NOT NULL DEFAULT '' COMMENT 'title',
    `content`     varchar(5000) NOT NULL DEFAULT '' COMMENT 'content',
    `create_user` bigint(20)    NOT NULL COMMENT '创建用户',
    `create_time` datetime      NOT NULL COMMENT '创建时间',
    `update_user` bigint(20)             DEFAULT NULL COMMENT '更新用户',
    `update_time` datetime               DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8mb4 COMMENT ='系统公告';

/*Data for the table `sys_notice` */
insert into `sys_notice`(`id`, `is_on`, `title`, `content`, `create_user`, `create_time`, `update_user`, `update_time`)
values (1, 1, '2022-02-10 20:33:35自动更新！！！', '推荐10个真实粉丝点击升级代理，可获1个月会员福利，终身享受推荐福利！<br/>【修复平台】花仙子 喵喵喵<br/>【新增平台】星淘网', 1,
        '2021-12-22 00:00:00', -1, '2022-02-10 20:33:35');

/*Table structure for table `sys_order` */
DROP TABLE IF EXISTS `sys_order`;
CREATE TABLE `sys_order`
(
    `out_trade_no`   bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '商家订单号',
    `trade_no`       varchar(30)          DEFAULT NULL COMMENT '支付宝交易号，支付宝交易凭证号',
    `total_amount`   varchar(10) NOT NULL COMMENT '订单金额。单位为人民币（元），精确到小数点后 2 位',
    `receipt_amount` varchar(10)          DEFAULT NULL COMMENT '实收金额',
    `is_pay`         tinyint(1)  NOT NULL DEFAULT '0' COMMENT '是否付款 -- [0：未付款  1：已付款]',
    `create_user`    bigint(20)  NOT NULL COMMENT '创建用户',
    `create_time`    datetime    NOT NULL COMMENT '创建时间',
    `update_user`    bigint(20)           DEFAULT NULL COMMENT '更新用户',
    `update_time`    datetime             DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`out_trade_no`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 46
  DEFAULT CHARSET = utf8 COMMENT ='支付订单';

/*Table structure for table `sys_product` */
DROP TABLE IF EXISTS `sys_product`;
CREATE TABLE `sys_product`
(
    `id`          int(11)      NOT NULL AUTO_INCREMENT COMMENT '礼品ID',
    `title`       varchar(50)           DEFAULT NULL COMMENT '礼品名称',
    `image`       varchar(200) NOT NULL DEFAULT 'https://gitee.com/zhangxu47/pic-go/raw/master/20220115165307.png' COMMENT '礼品图片',
    `is_code`     int(11)      NOT NULL DEFAULT '0',
    `num`         int(11)      NOT NULL DEFAULT '0',
    `origin`      int(11)      NOT NULL DEFAULT '0',
    `points`      int(11)      NOT NULL DEFAULT '0',
    `price`       int(11)      NOT NULL DEFAULT '0',
    `sales`       int(11)      NOT NULL DEFAULT '0',
    `description` varchar(50)  NOT NULL DEFAULT '暂无描述' COMMENT '礼品描述',
    `create_user` bigint(20)   NOT NULL COMMENT '创建用户',
    `create_time` datetime     NOT NULL COMMENT '创建时间',
    `update_user` bigint(20)            DEFAULT NULL COMMENT '更新用户',
    `update_time` datetime              DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8 COMMENT ='礼品表格';

/*Table structure for table `sys_site` */
DROP TABLE IF EXISTS `sys_site`;
CREATE TABLE `sys_site`
(
    `site_name`      varchar(50) DEFAULT NULL COMMENT 'siteName',
    `site_id`        varchar(50) NOT NULL COMMENT 'siteId',
    `is_free`        int(11)     DEFAULT NULL COMMENT 'isFree',
    `is_show`        int(11)     DEFAULT NULL COMMENT 'isShow',
    `is_log`         int(11)     DEFAULT NULL COMMENT 'isLog',
    `weight`         int(11)     DEFAULT NULL COMMENT 'weight',
    `is_hold`        int(11)     DEFAULT NULL COMMENT 'isHold',
    `is_android`     int(11)     DEFAULT NULL COMMENT 'isAndroid',
    `is_ios`         int(11)     DEFAULT NULL COMMENT 'isIos',
    `is_rank_show`   int(11)     DEFAULT NULL COMMENT 'isRankShow',
    `is_rank_search` int(11)     DEFAULT NULL COMMENT 'isRankSearch',
    `is_hide`        int(11)     DEFAULT NULL COMMENT 'isHide',
    `create_user`    bigint(20)  NOT NULL COMMENT '创建用户',
    `create_time`    datetime    NOT NULL COMMENT '创建时间',
    `update_user`    bigint(20)  DEFAULT NULL COMMENT '更新用户',
    `update_time`    datetime    DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`site_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='系统所有平台';

/*Table structure for table `user` */
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`          int(11)       NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `phone`       varchar(11)   NOT NULL COMMENT '手机号',
    `password`    varchar(1024) NOT NULL DEFAULT '' COMMENT '密码',
    `expire_time` date          NOT NULL DEFAULT '1970-01-01' COMMENT '会员过期时间',
    `bonus`       int(11)       NOT NULL DEFAULT '0' COMMENT '余额',
    `record_id`   varchar(11)   NOT NULL DEFAULT '无权限' COMMENT '邀请码',
    `is_withdraw` int(11)       NOT NULL DEFAULT '0' COMMENT 'isWithdraw',
    `is_agent`    int(11)       NOT NULL DEFAULT '0' COMMENT '是否是代理 0-不是 1-是',
    `role`        varchar(50)   NOT NULL DEFAULT 'VIP0' COMMENT '会员等级',
    `points`      int(11)       NOT NULL DEFAULT '0' COMMENT '积分',
    `is_code`     int(11)       NOT NULL DEFAULT '0' COMMENT 'isCode',
    `parend_id`   int(11)                DEFAULT NULL COMMENT '邀请人ID',
    `name`        varchar(20)            DEFAULT NULL COMMENT '礼品收货人姓名',
    `cell`        varchar(20)            DEFAULT NULL COMMENT '礼品收货人联系方式',
    `address`     varchar(100)           DEFAULT NULL COMMENT '礼品收货人地址',
    `create_user` bigint(20)    NOT NULL COMMENT '创建用户',
    `create_time` datetime      NOT NULL COMMENT '创建时间',
    `update_user` bigint(20)             DEFAULT NULL COMMENT '更新用户',
    `update_time` datetime               DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `user_phone_uindex` (`phone`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 372
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户表';

/*Table structure for table `user_site_backup` */
DROP TABLE IF EXISTS `user_site_backup`;
CREATE TABLE `user_site_backup`
(
    `user_id`     int(11)    NOT NULL COMMENT '用户ID',
    `info`        blob COMMENT '备份的JSON字符串',
    `password`    varchar(20) DEFAULT NULL COMMENT '密码',
    `create_user` bigint(20) NOT NULL COMMENT '创建用户',
    `create_time` datetime   NOT NULL COMMENT '创建时间',
    `update_user` bigint(20)  DEFAULT NULL COMMENT '更新用户',
    `update_time` datetime    DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='用户备份信息表';

/*Table structure for table `user_site_collect` */
DROP TABLE IF EXISTS `user_site_collect`;
CREATE TABLE `user_site_collect`
(
    `site_id`     varchar(50)  DEFAULT '' COMMENT 'siteId',
    `group`       varchar(50)  DEFAULT '' COMMENT 'group',
    `index`       varchar(50)  DEFAULT '' COMMENT 'index',
    `site_name`   varchar(255) DEFAULT NULL,
    `collect_id`  bigint(15) NOT NULL AUTO_INCREMENT,
    `user_id`     int(11)    NOT NULL COMMENT '用户ID',
    `create_user` bigint(20) NOT NULL COMMENT '创建用户',
    `create_time` datetime   NOT NULL COMMENT '创建时间',
    `update_user` bigint(20)   DEFAULT NULL COMMENT '更新用户',
    `update_time` datetime     DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`collect_id`) USING BTREE,
    KEY `collect_user_id_fk` (`user_id`),
    CONSTRAINT `collect_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2649
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户平台信息表';

/*Table structure for table `user_site_sort` */
DROP TABLE IF EXISTS `user_site_sort`;
CREATE TABLE `user_site_sort`
(
    `order`       blob COMMENT '排序',
    `user_id`     int(11)    NOT NULL COMMENT '用户ID',
    `create_user` bigint(20) NOT NULL COMMENT '创建用户',
    `create_time` datetime   NOT NULL COMMENT '创建时间',
    `update_user` bigint(20) DEFAULT NULL COMMENT '更新用户',
    `update_time` datetime   DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户平台排序表';

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;
