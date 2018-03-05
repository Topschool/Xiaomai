SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `account`;
DROP TABLE IF EXISTS `transaction _record`;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE `account` (
  `id` VARCHAR(255) NOT NULL,
  `balance` DECIMAL NOT NULL,
  `status` VARCHAR(10) NOT NULL,
  `is_sys_account` BOOLEAN NOT NULL,
  `update_time` BIGINT NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `transaction _record` (
  `id` BIGINT NOT NULL,
  `payer_id` VARCHAR(255) NOT NULL,
  `payee_id` VARCHAR(255) NOT NULL,
  `amount` DECIMAL NOT NULL,
  `remark` VARCHAR(255),
  `status` VARCHAR(10) NOT NULL,
  `create_time` BIGINT NOT NULL,
  `finish_time` BIGINT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

ALTER TABLE `transaction _record` ADD FOREIGN KEY (`payer_id`) REFERENCES `account`(`id`);
ALTER TABLE `transaction _record` ADD FOREIGN KEY (`payee_id`) REFERENCES `account`(`id`);