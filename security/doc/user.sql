DROP TABLE IF EXISTS `security_user`;
CREATE TABLE `security_user`
(
    `id`       int(11)                                              NOT NULL AUTO_INCREMENT COMMENT '系统物理主键',
    `username` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
    `password` varchar(255)                                            DEFAULT NULL COMMENT '用户密码',
    PRIMARY KEY (`id`)
    ) ENGINE = InnoDB
    AUTO_INCREMENT = 110
    DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `security_user`
VALUES ('1', 'vilce', 'vilce');
INSERT INTO `admin_user`
VALUES ('2', 'test', '123');
