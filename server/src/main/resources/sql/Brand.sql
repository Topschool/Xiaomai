-- auto Generated on 2018-02-27 22:54:12 
-- DROP TABLE IF EXISTS `brand`; 
CREATE TABLE brand(
    `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `name` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'name',
    `logo_url` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'logoUrl',
    `description` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'description',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'brand';
