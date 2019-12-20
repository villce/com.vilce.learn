CREATE TABLE vilce.users
(
  eid number(18,0) NOT NULL ,
  user_code varchar2(255) NOT NULL ,
  user_name varchar2(255) DEFAULT NULL ,
  password varchar2(255) NOT NULL
);
--注释脚本
comment on table vilce.users is '用户表';
comment on column vilce.users.eid is '系统物理主键';
comment on column vilce.users.user_code is '用户账号';
comment on column vilce.users.user_name is '用户名';
comment on column vilce.users.password is '密码';