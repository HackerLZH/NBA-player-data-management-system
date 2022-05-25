/*
 Navicat Premium Data Transfer

 Source Server         : win_mysql
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : 127.0.0.1:3306
 Source Schema         : graduationdesign

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 25/05/2022 15:22:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '留言id',
  `user_id` int NOT NULL COMMENT '用户id',
  `comment_time` datetime NOT NULL COMMENT '留言时间',
  `text` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '留言内容',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (3, 5, '2022-04-18 00:13:29', '我们会认真浏览用户留言！');
INSERT INTO `comment` VALUES (4, 2, '2022-04-18 00:23:00', '加油！');
INSERT INTO `comment` VALUES (5, 9, '2022-04-18 10:24:03', '笑望沧溟千军破，\n\n策定乾坤算因果 。\n\n无觉无惧轻生死，\n\n非鬼非神似疯魔。');
INSERT INTO `comment` VALUES (6, 8, '2022-04-18 10:33:59', '宁的网站安全系数不高啊，容易被入侵。有问题可以联系我，本人可以提供技术支持，价格友好。');
INSERT INTO `comment` VALUES (7, 2, '2022-04-18 10:35:58', '一觉睡到天亮，又是养身的一天^^');
INSERT INTO `comment` VALUES (9, 5, '2022-04-18 21:44:06', '我们将给我们的网站加入spring cache');
INSERT INTO `comment` VALUES (10, 5, '2022-04-21 20:24:41', '本系统暂时停更');

-- ----------------------------
-- Table structure for player
-- ----------------------------
DROP TABLE IF EXISTS `player`;
CREATE TABLE `player`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '球员id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '姓名',
  `age` int NOT NULL DEFAULT -1 COMMENT '年龄',
  `birthday` date NOT NULL COMMENT '生日',
  `birth_place` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '出生地',
  `team` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '效力球队',
  `enter_time` int NOT NULL COMMENT '选秀时间',
  `played_time` int NOT NULL DEFAULT 0 COMMENT '已打NBA时间',
  `retired` enum('是','否') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '是否退役',
  `score` int NOT NULL DEFAULT 0 COMMENT '得分',
  `rebound` int NOT NULL DEFAULT 0 COMMENT '篮板',
  `assist` int NOT NULL DEFAULT 0 COMMENT '助攻',
  `steal` int NOT NULL DEFAULT 0 COMMENT '抢断',
  `block` int NOT NULL DEFAULT 0 COMMENT '盖帽',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of player
-- ----------------------------
INSERT INTO `player` VALUES (1, '迈克尔-乔丹', 59, '1963-02-17', '美国纽约州布鲁克林', '公牛', 1984, 15, '是', 32292, 6672, 5633, 2514, 893);
INSERT INTO `player` VALUES (2, '勒布朗-詹姆斯', 37, '1984-12-30', '美国俄亥俄州阿克伦', '湖人', 2003, 19, '否', 37062, 10210, 10045, 2136, 1041);
INSERT INTO `player` VALUES (3, '卡里姆-阿卜杜尔-贾巴尔', 74, '1947-04-16', '美国纽约', '湖人', 1969, 20, '否', 38387, 17440, 5660, 1160, 3189);
INSERT INTO `player` VALUES (4, '魔术师约翰逊', 62, '1959-08-14', '美国密歇根州兰辛', '湖人', 1979, 13, '是', 17707, 6559, 10141, 1724, 374);
INSERT INTO `player` VALUES (5, '蒂姆-邓肯', 45, '1976-04-25', '美属维尔京群岛圣克罗伊岛', '马刺', 1997, 19, '是', 26496, 15091, 4225, 1025, 3020);
INSERT INTO `player` VALUES (6, '比尔-拉塞尔', 88, '1934-02-12', '美国路易斯安那州门罗市', '凯尔特人', 1956, 13, '是', 14522, 21620, 4100, 0, 0);
INSERT INTO `player` VALUES (7, '克里斯-保罗', 36, '1985-05-06', '美国北卡罗来纳州温斯顿-塞勒姆', '太阳', 2005, 17, '否', 20936, 5206, 10977, 2453, 172);
INSERT INTO `player` VALUES (8, '斯蒂芬-库里', 34, '1988-03-14', '美国俄亥俄州阿克伦', '勇士', 2009, 13, '否', 20064, 3838, 5388, 1367, 187);
INSERT INTO `player` VALUES (9, '科比-布莱恩特', 42, '1978-08-23', '美国宾夕法尼亚州费城', '湖人', 1996, 20, '是', 33643, 7047, 6306, 1944, 640);
INSERT INTO `player` VALUES (10, '凯文-杜兰特', 33, '1988-09-29', '美国华盛顿哥伦比亚特区', '篮网', 2007, 15, '否', 25506, 6636, 4016, 1020, 1038);
INSERT INTO `player` VALUES (11, '沙奎尔-奥尼尔', 50, '1972-03-26', '美国新泽西州纽瓦克', '湖人', 1992, 19, '是', 28596, 13099, 3026, 739, 2732);
INSERT INTO `player` VALUES (12, '卡梅罗-安东尼', 37, '1984-05-29', '美国纽约州布鲁克林', '湖人', 2003, 19, '否', 28289, 7808, 3422, 1223, 644);
INSERT INTO `player` VALUES (13, '德怀特-霍华德', 36, '1985-12-08', '美国佐治亚州亚特兰大', '湖人', 2004, 18, '否', 19485, 14627, 1676, 1081, 2228);
INSERT INTO `player` VALUES (14, '卡尔-马龙', 58, '1963-07-24', '美国路易斯安那州萨穆菲尔德', '爵士', 1985, 19, '是', 36928, 14968, 5248, 2085, 1145);
INSERT INTO `player` VALUES (15, '安东尼-戴维斯', 29, '1993-03-11', '美国伊利诺伊州芝加哥', '湖人', 2012, 10, '否', 14390, 6162, 1414, 824, 1413);
INSERT INTO `player` VALUES (16, '德怀恩-韦德', 40, '1982-01-17', '美国伊利诺伊州芝加哥', '热火', 2003, 16, '是', 23165, 4933, 5701, 1620, 885);
INSERT INTO `player` VALUES (17, '德克-诺维茨基', 44, '1978-06-19', '德国维尔茨堡', '独行侠', 1998, 21, '是', 31560, 11489, 3651, 1210, 1281);
INSERT INTO `player` VALUES (18, '威尔特-张伯伦', 86, '1936-08-21', '美国宾夕法尼亚州费城', '湖人', 1959, 14, '是', 31419, 23924, 4643, 0, 0);
INSERT INTO `player` VALUES (19, '摩西-马龙 ', 67, '1955-03-23', '美国弗吉尼亚州彼得斯堡', '76人', 1978, 17, '是', 27409, 16212, 1796, 1089, 1733);
INSERT INTO `player` VALUES (20, '文斯-卡特', 45, '1977-01-26', '美国佛罗里达州代顿海滩市', '猛龙', 1998, 22, '是', 25728, 6606, 4714, 1530, 888);
INSERT INTO `player` VALUES (21, '凯文-加内特', 46, '1976-05-19', '美国南卡罗来纳州', '森林狼', 1995, 21, '是', 26071, 14662, 5445, 1859, 2037);
INSERT INTO `player` VALUES (22, '卢卡-东契奇', 23, '1999-02-28', '斯洛文尼亚卢布尔雅那', '独行侠', 2018, 4, '否', 6962, 2256, 2102, 278, 111);
INSERT INTO `player` VALUES (23, '扬尼斯-阿德托昆博', 28, '1994-12-06', '希腊雅典', '雄鹿', 2013, 9, '否', 14321, 6149, 3020, 764, 856);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `age` int NULL DEFAULT -1 COMMENT '年龄',
  `gender` enum('保密','男','女') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '邮箱',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '电话号码',
  `register_time` date NOT NULL COMMENT '注册时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'test1', 'test1test1', NULL, -1, NULL, 'test1@qq.com', '', '2022-04-08');
INSERT INTO `user` VALUES (2, 'lzh', 'lzhlzh', '/assets/images/user/lzh/1650247987900程序员头像.jpg', 22, '保密', '1064433607@qq.com', '13659896458', '2022-04-08');
INSERT INTO `user` VALUES (5, 'admin', 'adminadmin', '/assets/images/user/admin/1650247916462admin.png', 0, '保密', 'admin@admin.com', '', '2022-04-10');
INSERT INTO `user` VALUES (8, 'hacker', 'hackerhacker', '/assets/images/user/hacker/1650249051128hacker.png', -1, '保密', 'hacker@163.com', '', '2022-04-15');
INSERT INTO `user` VALUES (9, '疯不觉', 'fbjfbj', '/assets/images/default-icon.png', -1, NULL, 'fbj@163.com', '', '2022-04-18');
INSERT INTO `user` VALUES (10, '枉叹之', 'wtzwtz', '/assets/images/default-icon.png', -1, NULL, 'lzh1064433607@163.com', '', '2022-04-22');

SET FOREIGN_KEY_CHECKS = 1;
