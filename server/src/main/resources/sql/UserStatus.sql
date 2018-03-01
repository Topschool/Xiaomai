-- auto Generated on 2018-03-01 13:43:49 
-- DROP TABLE IF EXISTS `user_status`; 
CREATE TABLE user_status(
    `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    scratch_card_status BOOLEAN DEFAULT FALSE ,
    order_food_status BOOLEAN DEFAULT FALSE ,
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'user_status';
