/*
 Navicat Premium Data Transfer

 Source Server         : azdizi
 Source Server Type    : MySQL
 Source Server Version : 100615 (10.6.15-MariaDB-cll-lve-log)
 Source Host           : 103.221.221.44:3306
 Source Schema         : rryvhcjj_spet

 Target Server Type    : MySQL
 Target Server Version : 100615 (10.6.15-MariaDB-cll-lve-log)
 File Encoding         : 65001

 Date: 29/11/2023 18:19:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_gex1lmaqpg0ir5g1f5eftyaa1` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of account
-- ----------------------------
BEGIN;
INSERT INTO `account` (`id`, `created_at`, `updated_at`, `version`, `email`, `full_name`, `password`, `role`, `status`, `username`, `token`) VALUES (1, '2023-10-16 13:20:42.000000', '2023-10-16 13:20:42.000000', 0, 'admin@mail.com', 'admin', '$2a$10$FJpGMPPueWM8OCDgeicKk.r7yQI9Ga7yxsK7WpVlwinmVNOCXr38a', 'ADMIN', b'1', 'admin', NULL);
INSERT INTO `account` (`id`, `created_at`, `updated_at`, `version`, `email`, `full_name`, `password`, `role`, `status`, `username`, `token`) VALUES (2, '2023-10-16 13:20:42.000000', '2023-11-26 22:57:10.000000', 3, 'test@mail.com', 'test', '$2a$10$FJpGMPPueWM8OCDgeicKk.r7yQI9Ga7yxsK7WpVlwinmVNOCXr38a', 'USER', b'1', 'test', 'cOT4O3edQiGCcq-gySLSq7:APA91bFtw5kzd8SrPmF7BFqvKnpfPjpyucpa3rezlRyixs0PzlGnpaYG3yDzowxuvhTSaoU-T5hIGwzhsxCpgVS8_LxzcSwE0IiFZBZ1PraBJWUB9eGhmoRtR_nMak3-ixL5YnyMeji1');
INSERT INTO `account` (`id`, `created_at`, `updated_at`, `version`, `email`, `full_name`, `password`, `role`, `status`, `username`, `token`) VALUES (3, '2023-11-25 14:47:58.000000', '2023-11-25 14:47:58.000000', 0, 'ddd@gmil.com', 'dd', '$2a$10$ItQSGDHJIhw6PrviQ8jD6.Ki8AMHqn33gkEdtDXcRkxBzqNtwbPZK', 'HOSPITAL', b'1', 'ddd@gmil.com', NULL);
INSERT INTO `account` (`id`, `created_at`, `updated_at`, `version`, `email`, `full_name`, `password`, `role`, `status`, `username`, `token`) VALUES (4, '2023-11-25 14:50:43.000000', '2023-11-28 23:11:15.000000', 3, 'bs@gmail.com', 'bs', '$2a$10$FJpGMPPueWM8OCDgeicKk.r7yQI9Ga7yxsK7WpVlwinmVNOCXr38a', 'HOSPITAL', b'1', 'bs', 'cOT4O3edQiGCcq-gySLSq7:APA91bFtw5kzd8SrPmF7BFqvKnpfPjpyucpa3rezlRyixs0PzlGnpaYG3yDzowxuvhTSaoU-T5hIGwzhsxCpgVS8_LxzcSwE0IiFZBZ1PraBJWUB9eGhmoRtR_nMak3-ixL5YnyMeji1');
COMMIT;

-- ----------------------------
-- Table structure for activity_record
-- ----------------------------
DROP TABLE IF EXISTS `activity_record`;
CREATE TABLE `activity_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `activity_date` datetime(6) DEFAULT NULL,
  `activity_type` varchar(255) DEFAULT NULL,
  `notes` varchar(255) DEFAULT NULL,
  `pet_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKl23c7k4fhsi39l26yv1r7mvkg` (`pet_id`),
  CONSTRAINT `FKl23c7k4fhsi39l26yv1r7mvkg` FOREIGN KEY (`pet_id`) REFERENCES `pet` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of activity_record
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for booking
-- ----------------------------
DROP TABLE IF EXISTS `booking`;
CREATE TABLE `booking` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `end_time` varchar(255) DEFAULT NULL,
  `start_time` varchar(255) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `account_id` bigint(20) DEFAULT NULL,
  `hospital_id` bigint(20) DEFAULT NULL,
  `date` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7hunottedmjhtdcvhv4sx6x4a` (`account_id`),
  KEY `FKb5o8s46kssa7nkiadk56feog0` (`hospital_id`),
  CONSTRAINT `FK7hunottedmjhtdcvhv4sx6x4a` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`),
  CONSTRAINT `FKb5o8s46kssa7nkiadk56feog0` FOREIGN KEY (`hospital_id`) REFERENCES `hospital` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of booking
-- ----------------------------
BEGIN;
INSERT INTO `booking` (`id`, `created_at`, `updated_at`, `version`, `description`, `end_time`, `start_time`, `status`, `title`, `account_id`, `hospital_id`, `date`) VALUES (7, '2023-11-26 22:41:59.000000', '2023-11-26 22:44:27.000000', 1, 'kham benh', '18:30', '18:30', 'APPROVED', 'test', 2, 1, '27/11/2023');
INSERT INTO `booking` (`id`, `created_at`, `updated_at`, `version`, `description`, `end_time`, `start_time`, `status`, `title`, `account_id`, `hospital_id`, `date`) VALUES (8, '2023-11-26 22:57:27.000000', '2023-11-26 23:09:55.000000', 1, 'a', '06:57', '06:57', 'APPROVED', 'a', 2, 1, '28/11/2023');
INSERT INTO `booking` (`id`, `created_at`, `updated_at`, `version`, `description`, `end_time`, `start_time`, `status`, `title`, `account_id`, `hospital_id`, `date`) VALUES (9, '2023-11-26 22:57:33.000000', '2023-11-28 23:23:18.000000', 1, 'a', '22:57', '22:57', 'APPROVED', 'a', 2, 1, '28/11/2023');
INSERT INTO `booking` (`id`, `created_at`, `updated_at`, `version`, `description`, `end_time`, `start_time`, `status`, `title`, `account_id`, `hospital_id`, `date`) VALUES (10, '2023-11-26 23:06:04.000000', '2023-11-28 23:21:35.000000', 2, 'd', '06:05', '06:05', 'PENDING', 'd', 2, 1, '27/11/2023');
INSERT INTO `booking` (`id`, `created_at`, `updated_at`, `version`, `description`, `end_time`, `start_time`, `status`, `title`, `account_id`, `hospital_id`, `date`) VALUES (11, '2023-11-26 23:07:27.000000', '2023-11-28 23:12:39.000000', 1, '1', '06:07', '06:07', 'PENDING', '1', 2, 1, '27/11/2023');
INSERT INTO `booking` (`id`, `created_at`, `updated_at`, `version`, `description`, `end_time`, `start_time`, `status`, `title`, `account_id`, `hospital_id`, `date`) VALUES (12, '2023-11-26 23:08:50.000000', '2023-11-26 23:08:57.000000', 1, '1', '06:07', '06:07', 'PENDING', '11', 2, 1, '27/11/2023');
COMMIT;

-- ----------------------------
-- Table structure for health_record
-- ----------------------------
DROP TABLE IF EXISTS `health_record`;
CREATE TABLE `health_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `health` varchar(255) DEFAULT NULL,
  `height` double DEFAULT NULL,
  `record_date` date DEFAULT NULL,
  `weight` double DEFAULT NULL,
  `pet_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcsocitqwdhh7eri5obf8q80by` (`pet_id`),
  CONSTRAINT `FKcsocitqwdhh7eri5obf8q80by` FOREIGN KEY (`pet_id`) REFERENCES `pet` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of health_record
-- ----------------------------
BEGIN;
INSERT INTO `health_record` (`id`, `created_at`, `updated_at`, `version`, `health`, `height`, `record_date`, `weight`, `pet_id`) VALUES (1, '2023-11-01 22:52:14.000000', '2023-11-01 23:13:23.000000', 1, 'good 22', 11, NULL, 11, 1);
INSERT INTO `health_record` (`id`, `created_at`, `updated_at`, `version`, `health`, `height`, `record_date`, `weight`, `pet_id`) VALUES (2, '2023-11-01 23:03:07.000000', '2023-11-01 23:03:07.000000', 0, 'good 1', 10, NULL, 9, 1);
INSERT INTO `health_record` (`id`, `created_at`, `updated_at`, `version`, `health`, `height`, `record_date`, `weight`, `pet_id`) VALUES (3, '2023-11-06 21:20:07.000000', '2023-11-06 21:20:07.000000', 0, '10', 10, NULL, 10, 2);
INSERT INTO `health_record` (`id`, `created_at`, `updated_at`, `version`, `health`, `height`, `record_date`, `weight`, `pet_id`) VALUES (4, '2023-11-06 21:20:47.000000', '2023-11-06 21:20:47.000000', 0, '10', 10, NULL, 10, 2);
INSERT INTO `health_record` (`id`, `created_at`, `updated_at`, `version`, `health`, `height`, `record_date`, `weight`, `pet_id`) VALUES (5, '2023-11-07 22:01:22.000000', '2023-11-07 22:01:22.000000', 0, '10', 10, NULL, 10, 2);
INSERT INTO `health_record` (`id`, `created_at`, `updated_at`, `version`, `health`, `height`, `record_date`, `weight`, `pet_id`) VALUES (6, '2023-11-07 22:02:34.000000', '2023-11-07 22:02:34.000000', 0, '1', 1, NULL, 1, 2);
INSERT INTO `health_record` (`id`, `created_at`, `updated_at`, `version`, `health`, `height`, `record_date`, `weight`, `pet_id`) VALUES (7, '2023-11-07 22:03:51.000000', '2023-11-07 22:03:51.000000', 0, 'A', 1, NULL, 1, 2);
INSERT INTO `health_record` (`id`, `created_at`, `updated_at`, `version`, `health`, `height`, `record_date`, `weight`, `pet_id`) VALUES (8, '2023-11-20 21:19:54.000000', '2023-11-20 21:19:54.000000', 0, 'TOT', 10, NULL, 10, 2);
INSERT INTO `health_record` (`id`, `created_at`, `updated_at`, `version`, `health`, `height`, `record_date`, `weight`, `pet_id`) VALUES (9, '2023-11-22 22:41:45.000000', '2023-11-22 22:41:45.000000', 0, 'TOT', 10, NULL, 10, 2);
INSERT INTO `health_record` (`id`, `created_at`, `updated_at`, `version`, `health`, `height`, `record_date`, `weight`, `pet_id`) VALUES (10, '2023-11-22 22:41:52.000000', '2023-11-22 22:41:52.000000', 0, '1', 1, NULL, 1, 2);
COMMIT;

-- ----------------------------
-- Table structure for hospital
-- ----------------------------
DROP TABLE IF EXISTS `hospital`;
CREATE TABLE `hospital` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `account` varbinary(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `account_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of hospital
-- ----------------------------
BEGIN;
INSERT INTO `hospital` (`id`, `created_at`, `updated_at`, `version`, `account`, `address`, `email`, `name`, `phone`, `status`, `account_id`) VALUES (1, '2023-11-26 13:22:13.000000', '2023-11-26 13:22:13.000000', 0, NULL, 'tphcm', 'khama@gmail.com', 'Phòng khám a', '0987653542', b'1', 4);
COMMIT;

-- ----------------------------
-- Table structure for media
-- ----------------------------
DROP TABLE IF EXISTS `media`;
CREATE TABLE `media` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `pet_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnr46it9nxslc6lqcrwhnd4syq` (`pet_id`),
  CONSTRAINT `FKnr46it9nxslc6lqcrwhnd4syq` FOREIGN KEY (`pet_id`) REFERENCES `pet` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of media
-- ----------------------------
BEGIN;
INSERT INTO `media` (`id`, `created_at`, `updated_at`, `version`, `path`, `status`, `pet_id`) VALUES (1, '2023-10-20 23:12:58.000000', '2023-10-20 23:12:58.000000', 0, '/images/db404759-70ff-4cdf-a6bc-715b069db79a.jpg', b'1', 2);
INSERT INTO `media` (`id`, `created_at`, `updated_at`, `version`, `path`, `status`, `pet_id`) VALUES (2, '2023-10-20 23:15:30.000000', '2023-10-20 23:15:30.000000', 0, '/images/e70c31e4-5c5c-4e2c-82f3-d0ec7d170f37.jpg', b'1', 1);
COMMIT;

-- ----------------------------
-- Table structure for medical_record
-- ----------------------------
DROP TABLE IF EXISTS `medical_record`;
CREATE TABLE `medical_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `appointment_date` datetime(6) DEFAULT NULL,
  `diagnosis` varchar(255) DEFAULT NULL,
  `notes` varchar(255) DEFAULT NULL,
  `prescription` varchar(255) DEFAULT NULL,
  `docter_id` bigint(20) DEFAULT NULL,
  `pet_id` bigint(20) DEFAULT NULL,
  `hospital_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK311n3d0hvggvmi97c04b8df8d` (`docter_id`),
  KEY `FKbhn8ua3wkghyv8hsmbrangqeh` (`pet_id`),
  KEY `FK1dwbc0qn49qgbejvc1663kk8h` (`hospital_id`),
  CONSTRAINT `FK1dwbc0qn49qgbejvc1663kk8h` FOREIGN KEY (`hospital_id`) REFERENCES `hospital` (`id`),
  CONSTRAINT `FKbhn8ua3wkghyv8hsmbrangqeh` FOREIGN KEY (`pet_id`) REFERENCES `pet` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of medical_record
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for owner
-- ----------------------------
DROP TABLE IF EXISTS `owner`;
CREATE TABLE `owner` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `account_id` bigint(20) DEFAULT NULL,
  `pet_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKc4c2upgfje2ub3yrch383l8d7` (`account_id`),
  KEY `FK4q9fnj4jal9hn5hpm3glcls9c` (`pet_id`),
  CONSTRAINT `FK4q9fnj4jal9hn5hpm3glcls9c` FOREIGN KEY (`pet_id`) REFERENCES `pet` (`id`),
  CONSTRAINT `FKc4c2upgfje2ub3yrch383l8d7` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of owner
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for pet
-- ----------------------------
DROP TABLE IF EXISTS `pet`;
CREATE TABLE `pet` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  `color` varchar(255) DEFAULT NULL,
  `health` varchar(255) DEFAULT NULL,
  `height` double DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `species` varchar(255) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `weight` double DEFAULT NULL,
  `owner_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4vcf3fdbl5cdv3ifliy75m660` (`owner_id`),
  CONSTRAINT `FK4vcf3fdbl5cdv3ifliy75m660` FOREIGN KEY (`owner_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of pet
-- ----------------------------
BEGIN;
INSERT INTO `pet` (`id`, `created_at`, `updated_at`, `version`, `avatar`, `birth_date`, `color`, `health`, `height`, `name`, `species`, `status`, `weight`, `owner_id`) VALUES (1, '2023-10-18 21:59:01.000000', '2023-11-06 21:07:34.000000', 4, '/images/b58c7057-0b1b-4338-a2ce-8e2c14002369.jpg', '2023-10-18', 'Nâu Đỏ', 'good good', 15, 'Alaska Standard', 'CHÓ NGOẠI', b'1', 15, 1);
INSERT INTO `pet` (`id`, `created_at`, `updated_at`, `version`, `avatar`, `birth_date`, `color`, `health`, `height`, `name`, `species`, `status`, `weight`, `owner_id`) VALUES (2, '2023-10-18 21:59:01.000000', '2023-11-22 22:41:52.000000', 2, '/images/fee2789f-9719-46cb-9995-26a27cb21442.jpeg', '2023-10-18', 'Trắng Kem', '1', 1, 'Bichon Frise', 'CHÓ NGOẠI', b'1', 1, 2);
INSERT INTO `pet` (`id`, `created_at`, `updated_at`, `version`, `avatar`, `birth_date`, `color`, `health`, `height`, `name`, `species`, `status`, `weight`, `owner_id`) VALUES (3, '2023-10-18 21:59:01.000000', '2023-10-18 21:59:01.000000', 0, '/images/f1329a5f-01b0-4200-b2e5-509be23b851e.jpeg', '2023-10-18', 'Trắng Kem', 'GOOD', 15, 'Mèo Ba Tư', 'MÈO NGOẠI', b'1', 3, 2);
COMMIT;

-- ----------------------------
-- Table structure for schedule
-- ----------------------------
DROP TABLE IF EXISTS `schedule`;
CREATE TABLE `schedule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `activity_date` date DEFAULT NULL,
  `activity_type` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `end_time` varchar(255) DEFAULT NULL,
  `repeat_interval` varchar(255) DEFAULT NULL,
  `start_time` varchar(255) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `pet_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKp1oc0uvmbwdb9olxtmj9nd3kl` (`pet_id`),
  CONSTRAINT `FKp1oc0uvmbwdb9olxtmj9nd3kl` FOREIGN KEY (`pet_id`) REFERENCES `pet` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of schedule
-- ----------------------------
BEGIN;
INSERT INTO `schedule` (`id`, `created_at`, `updated_at`, `version`, `activity_date`, `activity_type`, `description`, `end_time`, `repeat_interval`, `start_time`, `status`, `title`, `pet_id`) VALUES (4, '2023-11-24 15:23:21.000000', '2023-11-24 15:23:21.000000', 0, '2023-11-25', 'TIÊM CHỦNG', 'CHÓ NGOẠI', '2:25', 'KHÔNG LẶP LẠI', '14:23', b'1', 'Trắng Kem', 2);
INSERT INTO `schedule` (`id`, `created_at`, `updated_at`, `version`, `activity_date`, `activity_type`, `description`, `end_time`, `repeat_interval`, `start_time`, `status`, `title`, `pet_id`) VALUES (17, '2023-11-26 10:42:15.000000', '2023-11-26 10:42:15.000000', 0, '2023-11-25', 'TIÊM CHỦNG', 'tiêm định kỳ', '15:00', 'LẶP LẠI', '13:00', b'1', 'tiêm định kỳ', 1);
INSERT INTO `schedule` (`id`, `created_at`, `updated_at`, `version`, `activity_date`, `activity_type`, `description`, `end_time`, `repeat_interval`, `start_time`, `status`, `title`, `pet_id`) VALUES (18, '2023-11-28 23:33:46.000000', '2023-11-28 23:33:46.000000', 0, '2023-11-25', 'TIÊM CHỦNG', 'a', '4:33', 'LẶP THEO TUẦN', '7:33', b'1', 'a', 2);
COMMIT;

-- ----------------------------
-- Table structure for vaccination
-- ----------------------------
DROP TABLE IF EXISTS `vaccination`;
CREATE TABLE `vaccination` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `pet_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1or5ygij4jpiu06n5yfortb0f` (`pet_id`),
  CONSTRAINT `FK1or5ygij4jpiu06n5yfortb0f` FOREIGN KEY (`pet_id`) REFERENCES `pet` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of vaccination
-- ----------------------------
BEGIN;
INSERT INTO `vaccination` (`id`, `created_at`, `updated_at`, `version`, `date`, `name`, `status`, `pet_id`) VALUES (1, '2023-10-16 13:20:42.000000', '2023-10-16 13:20:42.000000', 0, '2023-11-10', 'Vaccin', 'PENDING', 1);
INSERT INTO `vaccination` (`id`, `created_at`, `updated_at`, `version`, `date`, `name`, `status`, `pet_id`) VALUES (2, '2023-10-16 13:20:42.000000', '2023-10-16 13:20:42.000000', 0, '2023-11-10', 'Vaccin', 'PENDING', 2);
INSERT INTO `vaccination` (`id`, `created_at`, `updated_at`, `version`, `date`, `name`, `status`, `pet_id`) VALUES (10, '2023-11-07 22:46:20.000000', '2023-11-07 22:46:20.000000', 0, '2023-11-30', 'A', 'PENDING', 2);
INSERT INTO `vaccination` (`id`, `created_at`, `updated_at`, `version`, `date`, `name`, `status`, `pet_id`) VALUES (11, '2023-11-07 22:54:27.000000', '2023-11-07 22:54:27.000000', 0, '2023-11-27', 'B', 'PENDING', 2);
INSERT INTO `vaccination` (`id`, `created_at`, `updated_at`, `version`, `date`, `name`, `status`, `pet_id`) VALUES (12, '2023-11-07 22:59:56.000000', '2023-11-07 22:59:56.000000', 0, '2023-11-23', 'ioio', 'PENDING', 2);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
