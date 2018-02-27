-- auto Generated on 2018-02-27 22:59:27 
-- DROP TABLE IF EXISTS `order_record`; 
CREATE TABLE order_record(
    `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `food_id` BIGINT NOT NULL DEFAULT -1 COMMENT 'foodId',
    `user_id` BIGINT NOT NULL DEFAULT -1 COMMENT 'userId',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'createTime',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'order_record';
