-- auto Generated on 2018-02-27 22:54:25 
-- DROP TABLE IF EXISTS `brand_food`; 
CREATE TABLE brand_food(
    `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `name` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'name',
    `price` DECIMAL(14,4) NOT NULL DEFAULT 0 COMMENT 'price',
    `brand_id` BIGINT NOT NULL DEFAULT -1 COMMENT 'brandId',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'brand_food';
