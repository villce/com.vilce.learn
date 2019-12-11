CREATE TABLE vilce.security_user
(
    id       number(10) NOT NULL,
    username varchar2(255) DEFAULT NULL,
    password varchar2(255) DEFAULT NULL,
    nickname varchar(255)  DEFAULT ''
);
--注释脚本
comment on table vilce.security_user is 'Security用户表';
comment on column vilce.security_user.id is '系统物理主键';
comment on column vilce.security_user.username is '账号';
comment on column vilce.security_user.password is '密码';
comment on column vilce.security_user.nickname is '昵称';