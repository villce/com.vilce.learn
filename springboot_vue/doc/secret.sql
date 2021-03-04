-- ----------------------------
-- Table structure for secret_img_modules
-- ----------------------------
DROP TABLE IF EXISTS `secret_img_modules`;
CREATE TABLE `secret_img_modules`
(
    `id`        int(11) NOT NULL AUTO_INCREMENT COMMENT '系统物理主键',
    `modules_date`      varchar(64)   DEFAULT NULL COMMENT '模块日期',
    `modules_title`      varchar(64)  DEFAULT NULL COMMENT '模块主题',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 17
  DEFAULT CHARSET = utf8
  COLLATE = utf8_general_ci;

-- ----------------------------
-- Records of admin_menu
-- ----------------------------
INSERT INTO `secret_img_modules`
VALUES (1, '2021-03-02', '测试');

DROP TABLE IF EXISTS `secret_img`;
CREATE TABLE `secret_img`
(
    `id`        int(11) NOT NULL AUTO_INCREMENT COMMENT '系统物理主键',
    `modules_id`      int(11)   NOT NULL COMMENT '模块ID',
    `img_url`      varchar(64)  DEFAULT NULL COMMENT '图片地址',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 17
  DEFAULT CHARSET = utf8
  COLLATE = utf8_general_ci;

-- ----------------------------
-- Records of admin_menu
-- ----------------------------
INSERT INTO `secret_img`
VALUES (1, 1, 'http://127.0.0.1:8006/img/2021-03-02/202103020001.png');