---- 人民币账户
CREATE TABLE `account_rmb` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(128) COLLATE utf8mb4_bin NOT NULL,
  `balance` decimal(10,0) NOT NULL COMMENT '用户余额',
  `freeze_amount` decimal(10,0) NOT NULL COMMENT '冻结金额，扣款暂存余额',
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

---- 美元账户
CREATE TABLE `account_dollar` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(128) COLLATE utf8mb4_bin NOT NULL,
  `balance` decimal(10,0) NOT NULL COMMENT '用户余额',
  `freeze_amount` decimal(10,0) NOT NULL COMMENT '冻结金额，扣款暂存余额',
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;


INSERT INTO `test`.`account_dollar`(`id`, `user_id`, `balance`, `freeze_amount`, `create_time`, `update_time`) VALUES (1, 'A001', 1, 0, '2020-12-18 22:40:44', '2020-12-19 17:54:41');
INSERT INTO `test`.`account_rmb`(`id`, `user_id`, `balance`, `freeze_amount`, `create_time`, `update_time`) VALUES (1, 'A001', 0, 0, '2020-12-18 22:39:28', NULL);

INSERT INTO `test1`.`account_dollar`(`id`, `user_id`, `balance`, `freeze_amount`, `create_time`, `update_time`) VALUES (1, 'A002', 0, 0, '2020-12-18 22:41:49', NULL);
INSERT INTO `test1`.`account_rmb`(`id`, `user_id`, `balance`, `freeze_amount`, `create_time`, `update_time`) VALUES (1, 'A002', 7, 0, '2020-12-18 22:41:49', '2020-12-19 17:54:46');


