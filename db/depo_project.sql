/*
 Navicat Premium Data Transfer

 Source Server         : local-Mysql
 Source Server Type    : MySQL
 Source Server Version : 100420
 Source Host           : localhost:3306
 Source Schema         : depo_project

 Target Server Type    : MySQL
 Target Server Version : 100420
 File Encoding         : 65001

 Date: 04/09/2021 23:09:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `aid` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fullName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`aid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'rezan@mail.com', 'Rezzan Isik', '827ccb0eea8a706c4c34a16891f84e7b');

-- ----------------------------
-- Table structure for basketproducts
-- ----------------------------
DROP TABLE IF EXISTS `basketproducts`;
CREATE TABLE `basketproducts`  (
  `bp_id` int NOT NULL AUTO_INCREMENT,
  `bp_amount` int NOT NULL,
  `bp_code` bigint NOT NULL,
  `bp_status` bit(1) NOT NULL,
  `fk_cuId` int NULL DEFAULT NULL,
  `fk_pId` int NULL DEFAULT NULL,
  PRIMARY KEY (`bp_id`) USING BTREE,
  INDEX `FKihu6ters5k68vfrosmp48q4o3`(`fk_cuId`) USING BTREE,
  INDEX `FK2hotvk0sni95mus8wrph27x0i`(`fk_pId`) USING BTREE,
  CONSTRAINT `FK2hotvk0sni95mus8wrph27x0i` FOREIGN KEY (`fk_pId`) REFERENCES `product` (`p_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKihu6ters5k68vfrosmp48q4o3` FOREIGN KEY (`fk_cuId`) REFERENCES `customer` (`cu_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of basketproducts
-- ----------------------------
INSERT INTO `basketproducts` VALUES (1, 24, 711672974, b'1', 2, 1);
INSERT INTO `basketproducts` VALUES (2, 24, 711672974, b'1', 1, 2);
INSERT INTO `basketproducts` VALUES (5, 5, 715592089, b'1', 3, 3);
INSERT INTO `basketproducts` VALUES (22, 42, 755137088, b'1', 3, 2);
INSERT INTO `basketproducts` VALUES (23, 42, 756079385, b'1', 1, 2);
INSERT INTO `basketproducts` VALUES (24, 24, 757173450, b'1', 2, 2);
INSERT INTO `basketproducts` VALUES (25, 25, 785863507, b'1', 3, 5);

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `cu_id` int NOT NULL AUTO_INCREMENT,
  `cu_address` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cu_code` bigint NOT NULL,
  `cu_company_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cu_email` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cu_mobile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cu_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cu_password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cu_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cu_status` int NOT NULL,
  `cu_surname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cu_tax_administration` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cu_tax_number` int NOT NULL,
  PRIMARY KEY (`cu_id`) USING BTREE,
  FULLTEXT INDEX `cu_fulltext`(`cu_name`)
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES (1, 'Kartal', 710904904, 'HalıCenter', 'rezzan.sk@hotmail.com', '5343249356', 'Rezzan', '12345678', '21652563', 1, 'Işık', 'İstanbul', 12345678);
INSERT INTO `customer` VALUES (2, 'Maltepe', 711153605, 'BurasıHalı', 'ali@mail.com', '8652356', 'Ali', '23456', '5343852', 2, 'Bilmem', 'İstanbul', 45678);
INSERT INTO `customer` VALUES (3, 'Kadıköy', 711268008, 'Evkur', 'halil@mail.com', '213465', 'Halil', '23456', '234567', 1, 'Yaşar', 'İstanbul', 23456);
INSERT INTO `customer` VALUES (4, 'Hürriyet', 711324868, 'IşıklarHalı', 'mehmet@mail.com', '23456', 'Mehmet', '2345', '23465', 2, 'Işık', 'İstanbul', 2134567);

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `p_id` int NOT NULL AUTO_INCREMENT,
  `p_amount` int NOT NULL,
  `p_code` bigint NOT NULL,
  `p_detail` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `p_purchase_price` int NOT NULL,
  `p_sale_price` int NOT NULL,
  `p_tax` int NOT NULL,
  `p_title` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `p_unit` int NOT NULL,
  PRIMARY KEY (`p_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, 100, 4444, 'Akrilik halı', 850, 1350, 3, 'Merinos Halı', 0);
INSERT INTO `product` VALUES (2, 850, 711400324, 'Kaymaz halı', 750, 1150, 3, 'Dinarsu Halı', 2);
INSERT INTO `product` VALUES (3, 50000, 711438713, 'Yolluk', 50, 75, 2, 'Aksu Halı', 2);
INSERT INTO `product` VALUES (5, 30, 785822494, 'Akrilik halı', 1000, 1500, 3, 'Padişah Halı', 3);

-- ----------------------------
-- View structure for customer_count
-- ----------------------------
DROP VIEW IF EXISTS `customer_count`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `customer_count` AS SELECT COUNT(*) as cu_count FROM customer ;

-- ----------------------------
-- View structure for order_count
-- ----------------------------
DROP VIEW IF EXISTS `order_count`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `order_count` AS SELECT COUNT(*) as or_count FROM basketproducts  WHERE bp_status = 1 ;

-- ----------------------------
-- View structure for product_count
-- ----------------------------
DROP VIEW IF EXISTS `product_count`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `product_count` AS SELECT COUNT(*) as pr_count FROM product WHERE p_amount > 0 ;

-- ----------------------------
-- View structure for stock_count
-- ----------------------------
DROP VIEW IF EXISTS `stock_count`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `stock_count` AS SELECT SUM(p_amount * p_purchase_price) as p_total_price from product ;

-- ----------------------------
-- View structure for total_amount
-- ----------------------------
DROP VIEW IF EXISTS `total_amount`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `total_amount` AS SELECT SUM(p_amount) as p_total_amount from product ;

-- ----------------------------
-- View structure for total_sale_value
-- ----------------------------
DROP VIEW IF EXISTS `total_sale_value`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `total_sale_value` AS SELECT SUM(p_amount * p_sale_price) as p_total_sale from product ;

-- ----------------------------
-- Procedure structure for csSearchFullText
-- ----------------------------
DROP PROCEDURE IF EXISTS `csSearchFullText`;
delimiter ;;
CREATE PROCEDURE `csSearchFullText`(IN `searchData` varchar(50))
BEGIN
	
	SELECT * FROM customer as cu
	WHERE MATCH(cu.cu_name) 
	AGAINST (searchData IN BOOLEAN MODE)
	ORDER BY cu.cu_id DESC;

END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
