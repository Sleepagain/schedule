/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost:3306
 Source Schema         : schedule

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 08/01/2022 16:29:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dict
-- ----------------------------
DROP TABLE IF EXISTS `dict`;
CREATE TABLE `dict`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `types` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `descs` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `beginweek` int(11) NULL DEFAULT NULL,
  `endweek` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of dict
-- ----------------------------
INSERT INTO `dict` VALUES (13, '高数', '123', 2, 3);
INSERT INTO `dict` VALUES (14, '线代', '1234', 1, 5);
INSERT INTO `dict` VALUES (15, '马哲', '1234', 3, 4);
INSERT INTO `dict` VALUES (17, '马经', '1114444', 1, 5);
INSERT INTO `dict` VALUES (19, '物理', '1234', NULL, NULL);
INSERT INTO `dict` VALUES (20, '体育', '333', NULL, NULL);
INSERT INTO `dict` VALUES (21, 'C语言', '111', NULL, NULL);
INSERT INTO `dict` VALUES (22, '数据库', '33', NULL, NULL);
INSERT INTO `dict` VALUES (23, '心理健康', '1234', 2, 4);
INSERT INTO `dict` VALUES (24, '1', '1', 1, 1);

-- ----------------------------
-- Table structure for major
-- ----------------------------
DROP TABLE IF EXISTS `major`;
CREATE TABLE `major`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `classes` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of major
-- ----------------------------
INSERT INTO `major` VALUES (1, NULL, '计算机学院', '测试1111', 'college', NULL);
INSERT INTO `major` VALUES (2, NULL, '文学院', '123123', 'college', NULL);
INSERT INTO `major` VALUES (3, NULL, '法学院', '12344444455', 'college', NULL);
INSERT INTO `major` VALUES (5, 2, '古文学', '1234444', 'major', '马哲,马经,体育');
INSERT INTO `major` VALUES (6, 1, '信息技术', '123', 'major', '高数,线代,马哲,C语言,数据库');
INSERT INTO `major` VALUES (8, NULL, '测试学院', '111', 'college', NULL);
INSERT INTO `major` VALUES (10, 2, '测试专业', '1234', 'major', '高数,马哲,体育,C语言,数据库');
INSERT INTO `major` VALUES (11, 1, '通讯工程', '123', 'major', '高数,体育,C语言,数据库,心理健康');
INSERT INTO `major` VALUES (12, NULL, '医学院', '12314123', 'college', NULL);
INSERT INTO `major` VALUES (13, 12, '心理健康', '1234123', 'major', '高数,马哲,物理,体育,C语言,数据库');

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `personcount` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of room
-- ----------------------------
INSERT INTO `room` VALUES (5, '11号教室', '3-201', NULL);
INSERT INTO `room` VALUES (6, '12号教室', '1234', NULL);
INSERT INTO `room` VALUES (7, '测试教室', '5号楼1111', NULL);
INSERT INTO `room` VALUES (8, '13号教室', '333', NULL);

-- ----------------------------
-- Table structure for temp
-- ----------------------------
DROP TABLE IF EXISTS `temp`;
CREATE TABLE `temp`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roomid` int(11) NULL DEFAULT NULL,
  `a1` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `a2` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `a3` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `a4` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `b1` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `b2` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `b3` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `b4` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `c1` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `c2` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `c3` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `c4` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `d1` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `d2` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `d3` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `d4` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `e1` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `e2` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `e3` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `e4` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 74 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of temp
-- ----------------------------
INSERT INTO `temp` VALUES (1, NULL, '周一_1', '周一_2', '周一_3', '周一_4', '周二_1', '周二_2', '周二_3', '周二_4', '周三_1', '周三_2', '周三_3', '周三_4', '周四_1', '周四_2', '周四_3', '周四_4', '周五_1', '周五_2', '周五_3', '周五_4');
INSERT INTO `temp` VALUES (70, 5, '11_体育_44', '10_体育_44', '11_C语言_45', '10_数据库_46', '11_数据库_46', '5_马哲_41', '5_马经_42', '10_高数_37', '13_马哲_41', '6_数据库_46', '10_马哲_41', '11_心理健康_48', '13_高数_37', '11_高数_37', '6_高数_37', '13_C语言_45', '13_数据库_46', '13_物理_43', '6_马哲_41', '5_体育_44');
INSERT INTO `temp` VALUES (71, 6, '6_C语言_45', '10_C语言_45', '13_体育_44', '6_线代_40', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '');
INSERT INTO `temp` VALUES (72, 7, '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '');
INSERT INTO `temp` VALUES (73, 8, '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '');

-- ----------------------------
-- Table structure for temp1
-- ----------------------------
DROP TABLE IF EXISTS `temp1`;
CREATE TABLE `temp1`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `week` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `classes` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of temp1
-- ----------------------------
INSERT INTO `temp1` VALUES (1, '周一', '1');
INSERT INTO `temp1` VALUES (2, '周一', '2');
INSERT INTO `temp1` VALUES (3, '周一', '3');
INSERT INTO `temp1` VALUES (4, '周一', '4');
INSERT INTO `temp1` VALUES (5, '周二', '1');
INSERT INTO `temp1` VALUES (6, '周二', '2');
INSERT INTO `temp1` VALUES (7, '周二', '3');
INSERT INTO `temp1` VALUES (8, '周二', '4');
INSERT INTO `temp1` VALUES (9, '周三', '1');
INSERT INTO `temp1` VALUES (10, '周三', '2');
INSERT INTO `temp1` VALUES (11, '周三', '3');
INSERT INTO `temp1` VALUES (12, '周三', '4');
INSERT INTO `temp1` VALUES (13, '周四', '1');
INSERT INTO `temp1` VALUES (14, '周四', '2');
INSERT INTO `temp1` VALUES (15, '周四', '3');
INSERT INTO `temp1` VALUES (16, '周四', '4');
INSERT INTO `temp1` VALUES (17, '周五', '1');
INSERT INTO `temp1` VALUES (18, '周五', '2');
INSERT INTO `temp1` VALUES (19, '周五', '3');
INSERT INTO `temp1` VALUES (20, '周五', '4');

-- ----------------------------
-- Table structure for timetable
-- ----------------------------
DROP TABLE IF EXISTS `timetable`;
CREATE TABLE `timetable`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `majorid` int(255) NULL DEFAULT NULL,
  `classes` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `teacherid` int(11) NULL DEFAULT NULL,
  `roomid` int(11) NULL DEFAULT NULL,
  `week` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `time` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 552 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of timetable
-- ----------------------------
INSERT INTO `timetable` VALUES (528, 11, '体育', 44, 5, '周一', '1');
INSERT INTO `timetable` VALUES (529, 10, '体育', 44, 5, '周一', '2');
INSERT INTO `timetable` VALUES (530, 11, 'C语言', 45, 5, '周一', '3');
INSERT INTO `timetable` VALUES (531, 10, '数据库', 46, 5, '周一', '4');
INSERT INTO `timetable` VALUES (532, 11, '数据库', 46, 5, '周二', '1');
INSERT INTO `timetable` VALUES (533, 5, '马哲', 41, 5, '周二', '2');
INSERT INTO `timetable` VALUES (534, 5, '马经', 42, 5, '周二', '3');
INSERT INTO `timetable` VALUES (535, 10, '高数', 37, 5, '周二', '4');
INSERT INTO `timetable` VALUES (536, 13, '马哲', 41, 5, '周三', '1');
INSERT INTO `timetable` VALUES (537, 6, '数据库', 46, 5, '周三', '2');
INSERT INTO `timetable` VALUES (538, 10, '马哲', 41, 5, '周三', '3');
INSERT INTO `timetable` VALUES (539, 11, '心理健康', 48, 5, '周三', '4');
INSERT INTO `timetable` VALUES (540, 13, '高数', 37, 5, '周四', '1');
INSERT INTO `timetable` VALUES (541, 11, '高数', 37, 5, '周四', '2');
INSERT INTO `timetable` VALUES (542, 6, '高数', 37, 5, '周四', '3');
INSERT INTO `timetable` VALUES (543, 13, 'C语言', 45, 5, '周四', '4');
INSERT INTO `timetable` VALUES (544, 13, '数据库', 46, 5, '周五', '1');
INSERT INTO `timetable` VALUES (545, 13, '物理', 43, 5, '周五', '2');
INSERT INTO `timetable` VALUES (546, 6, '马哲', 41, 5, '周五', '3');
INSERT INTO `timetable` VALUES (547, 5, '体育', 44, 5, '周五', '4');
INSERT INTO `timetable` VALUES (548, 6, 'C语言', 45, 6, '周一', '1');
INSERT INTO `timetable` VALUES (549, 10, 'C语言', 45, 6, '周一', '2');
INSERT INTO `timetable` VALUES (550, 13, '体育', 44, 6, '周一', '3');
INSERT INTO `timetable` VALUES (551, 6, '线代', 40, 6, '周一', '4');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `no` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `birthday` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tel` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `post` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `isadmin` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `college` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `major` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `classes` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 49 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, NULL, 'admin', '123', '超管', '444333', '123', NULL, NULL, '2222', '0', NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (35, '0001', 'stu', '123', '测试学生', '2022-01-08', '12311111111', '男', NULL, '123@23.COM', '2', NULL, '2', '5', NULL);
INSERT INTO `user` VALUES (36, '0002', 'stu1', '123', 'ceshi1', '2020-03-01', '11111', '女', NULL, '2222', '2', NULL, '1', '11', '14');
INSERT INTO `user` VALUES (37, NULL, 'tea', '123', '毛概老师', '2020-03-01', '12311111111', '男', '组长', '123@23.COM', '1', NULL, NULL, NULL, '高数');
INSERT INTO `user` VALUES (38, '0003', 'stu2', '123', '测试学生3', '2020-03-01', '12311111111', '男', NULL, '123@23.COM', '2', NULL, '2', '10', '17');
INSERT INTO `user` VALUES (39, '0004', 'stu3', '123', '测试女生', '2020-03-01', '13411111111', '女', NULL, '123@23.COM', '2', NULL, '1', '6', '13');
INSERT INTO `user` VALUES (40, NULL, 'tea1', '123', '高数老师1', '2020-03-01', '123', '男', '123', '123', '1', NULL, NULL, NULL, '线代');
INSERT INTO `user` VALUES (41, NULL, 'tea2', '123', '马哲老师', '2020-03-01', '12311111111', '男', '123', '123@23.COM', '1', NULL, NULL, NULL, '马哲');
INSERT INTO `user` VALUES (42, NULL, 'tea2', '123', '邓论老师', '2020-03-01', '123', '男', '123', '123', '1', NULL, NULL, NULL, '马经');
INSERT INTO `user` VALUES (43, NULL, 'tea4', '123', '计算机科学与技术老师', '2020-03-02', '123', '男', '123', '123', '1', NULL, NULL, NULL, '物理');
INSERT INTO `user` VALUES (44, NULL, 'tea5', '123', '语言老师', '2020-03-03', '12311111111', '男', '123', '123', '1', NULL, NULL, NULL, '体育');
INSERT INTO `user` VALUES (45, NULL, 'tea6', '123', '数据库老师', '2020-03-04', '123', '男', '123', '123', '1', NULL, NULL, NULL, 'C语言');
INSERT INTO `user` VALUES (46, NULL, 'tea7', '123', '心理学老师', '2020-03-02', '123', '男', '123', '123@23.COM', '1', NULL, NULL, NULL, '数据库');
INSERT INTO `user` VALUES (47, '0000001', 'stu99', '123', '测试99', '2020-03-08', '123', '男', NULL, '123344223@23.COM', '2', NULL, '2', '10', NULL);
INSERT INTO `user` VALUES (48, NULL, 'tea10', '123', '心理健康老师', '2020-04-26', '111', '男', '教研组长', '123@23.COM', '1', NULL, NULL, NULL, '心理健康');

SET FOREIGN_KEY_CHECKS = 1;
