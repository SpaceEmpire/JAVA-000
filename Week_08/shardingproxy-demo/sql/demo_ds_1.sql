

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_order_0
-- ----------------------------
DROP TABLE IF EXISTS `t_order_0`;
CREATE TABLE `t_order_0` (
  `order_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT 0 COMMENT '用户Id',
  `amount` int(11) NOT NULL DEFAULT 0 COMMENT '订单金额：分',
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '订单状态：0 待支付 1 已支付',
  `create_time` int(11) NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` int(11) NOT NULL DEFAULT 0 COMMENT '更新时间',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=543725524401762305 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='订单表';

-- ----------------------------
-- Table structure for t_order_1
-- ----------------------------
DROP TABLE IF EXISTS `t_order_1`;
CREATE TABLE `t_order_1` (
  `order_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT 0 COMMENT '用户Id',
  `amount` int(11) NOT NULL DEFAULT 0 COMMENT '订单金额：分',
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '订单状态：0 待支付 1 已支付',
  `create_time` int(11) NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` int(11) NOT NULL DEFAULT 0 COMMENT '更新时间',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=543725785706901506 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='订单表';

-- ----------------------------
-- Table structure for t_order_10
-- ----------------------------
DROP TABLE IF EXISTS `t_order_10`;
CREATE TABLE `t_order_10` (
  `order_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT 0 COMMENT '用户Id',
  `amount` int(11) NOT NULL DEFAULT 0 COMMENT '订单金额：分',
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '订单状态：0 待支付 1 已支付',
  `create_time` int(11) NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` int(11) NOT NULL DEFAULT 0 COMMENT '更新时间',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='订单表';

-- ----------------------------
-- Table structure for t_order_11
-- ----------------------------
DROP TABLE IF EXISTS `t_order_11`;
CREATE TABLE `t_order_11` (
  `order_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT 0 COMMENT '用户Id',
  `amount` int(11) NOT NULL DEFAULT 0 COMMENT '订单金额：分',
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '订单状态：0 待支付 1 已支付',
  `create_time` int(11) NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` int(11) NOT NULL DEFAULT 0 COMMENT '更新时间',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='订单表';

-- ----------------------------
-- Table structure for t_order_12
-- ----------------------------
DROP TABLE IF EXISTS `t_order_12`;
CREATE TABLE `t_order_12` (
  `order_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT 0 COMMENT '用户Id',
  `amount` int(11) NOT NULL DEFAULT 0 COMMENT '订单金额：分',
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '订单状态：0 待支付 1 已支付',
  `create_time` int(11) NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` int(11) NOT NULL DEFAULT 0 COMMENT '更新时间',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='订单表';

-- ----------------------------
-- Table structure for t_order_13
-- ----------------------------
DROP TABLE IF EXISTS `t_order_13`;
CREATE TABLE `t_order_13` (
  `order_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT 0 COMMENT '用户Id',
  `amount` int(11) NOT NULL DEFAULT 0 COMMENT '订单金额：分',
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '订单状态：0 待支付 1 已支付',
  `create_time` int(11) NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` int(11) NOT NULL DEFAULT 0 COMMENT '更新时间',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='订单表';

-- ----------------------------
-- Table structure for t_order_14
-- ----------------------------
DROP TABLE IF EXISTS `t_order_14`;
CREATE TABLE `t_order_14` (
  `order_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT 0 COMMENT '用户Id',
  `amount` int(11) NOT NULL DEFAULT 0 COMMENT '订单金额：分',
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '订单状态：0 待支付 1 已支付',
  `create_time` int(11) NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` int(11) NOT NULL DEFAULT 0 COMMENT '更新时间',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='订单表';

-- ----------------------------
-- Table structure for t_order_15
-- ----------------------------
DROP TABLE IF EXISTS `t_order_15`;
CREATE TABLE `t_order_15` (
  `order_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT 0 COMMENT '用户Id',
  `amount` int(11) NOT NULL DEFAULT 0 COMMENT '订单金额：分',
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '订单状态：0 待支付 1 已支付',
  `create_time` int(11) NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` int(11) NOT NULL DEFAULT 0 COMMENT '更新时间',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='订单表';

-- ----------------------------
-- Table structure for t_order_2
-- ----------------------------
DROP TABLE IF EXISTS `t_order_2`;
CREATE TABLE `t_order_2` (
  `order_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT 0 COMMENT '用户Id',
  `amount` int(11) NOT NULL DEFAULT 0 COMMENT '订单金额：分',
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '订单状态：0 待支付 1 已支付',
  `create_time` int(11) NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` int(11) NOT NULL DEFAULT 0 COMMENT '更新时间',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='订单表';

-- ----------------------------
-- Table structure for t_order_3
-- ----------------------------
DROP TABLE IF EXISTS `t_order_3`;
CREATE TABLE `t_order_3` (
  `order_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT 0 COMMENT '用户Id',
  `amount` int(11) NOT NULL DEFAULT 0 COMMENT '订单金额：分',
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '订单状态：0 待支付 1 已支付',
  `create_time` int(11) NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` int(11) NOT NULL DEFAULT 0 COMMENT '更新时间',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='订单表';

-- ----------------------------
-- Table structure for t_order_4
-- ----------------------------
DROP TABLE IF EXISTS `t_order_4`;
CREATE TABLE `t_order_4` (
  `order_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT 0 COMMENT '用户Id',
  `amount` int(11) NOT NULL DEFAULT 0 COMMENT '订单金额：分',
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '订单状态：0 待支付 1 已支付',
  `create_time` int(11) NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` int(11) NOT NULL DEFAULT 0 COMMENT '更新时间',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='订单表';

-- ----------------------------
-- Table structure for t_order_5
-- ----------------------------
DROP TABLE IF EXISTS `t_order_5`;
CREATE TABLE `t_order_5` (
  `order_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT 0 COMMENT '用户Id',
  `amount` int(11) NOT NULL DEFAULT 0 COMMENT '订单金额：分',
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '订单状态：0 待支付 1 已支付',
  `create_time` int(11) NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` int(11) NOT NULL DEFAULT 0 COMMENT '更新时间',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='订单表';

-- ----------------------------
-- Table structure for t_order_6
-- ----------------------------
DROP TABLE IF EXISTS `t_order_6`;
CREATE TABLE `t_order_6` (
  `order_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT 0 COMMENT '用户Id',
  `amount` int(11) NOT NULL DEFAULT 0 COMMENT '订单金额：分',
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '订单状态：0 待支付 1 已支付',
  `create_time` int(11) NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` int(11) NOT NULL DEFAULT 0 COMMENT '更新时间',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='订单表';

-- ----------------------------
-- Table structure for t_order_7
-- ----------------------------
DROP TABLE IF EXISTS `t_order_7`;
CREATE TABLE `t_order_7` (
  `order_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT 0 COMMENT '用户Id',
  `amount` int(11) NOT NULL DEFAULT 0 COMMENT '订单金额：分',
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '订单状态：0 待支付 1 已支付',
  `create_time` int(11) NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` int(11) NOT NULL DEFAULT 0 COMMENT '更新时间',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='订单表';

-- ----------------------------
-- Table structure for t_order_8
-- ----------------------------
DROP TABLE IF EXISTS `t_order_8`;
CREATE TABLE `t_order_8` (
  `order_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT 0 COMMENT '用户Id',
  `amount` int(11) NOT NULL DEFAULT 0 COMMENT '订单金额：分',
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '订单状态：0 待支付 1 已支付',
  `create_time` int(11) NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` int(11) NOT NULL DEFAULT 0 COMMENT '更新时间',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='订单表';

-- ----------------------------
-- Table structure for t_order_9
-- ----------------------------
DROP TABLE IF EXISTS `t_order_9`;
CREATE TABLE `t_order_9` (
  `order_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT 0 COMMENT '用户Id',
  `amount` int(11) NOT NULL DEFAULT 0 COMMENT '订单金额：分',
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '订单状态：0 待支付 1 已支付',
  `create_time` int(11) NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` int(11) NOT NULL DEFAULT 0 COMMENT '更新时间',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='订单表';

SET FOREIGN_KEY_CHECKS = 1;
