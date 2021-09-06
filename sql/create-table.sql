
-- ----------------------------
-- 用户主表
-- ----------------------------
drop table if exists user_master;
create table user_master
(
    id     bigint(20)  not null auto_increment comment '用户ID',
    username   varchar(30) not null comment '用户账号',
    password    varchar(100) default '' comment '密码',
    nickname   varchar(30) not null comment '用户昵称',
    type   varchar(2)   default '00' comment '用户类型（00访客）',
    email       varchar(50)  default '' comment '用户邮箱',
    phone_number varchar(11)  default '' comment '手机号码',
    sex         char(1)      default '0' comment '用户性别（0男 1女 2未知）',
    avatar      varchar(100) default '' comment '头像地址',
    status      char(1)      default '0' comment '帐号状态（0正常 1停用）',
    del_flag    char(1)      default '0' comment '删除标志（0代表存在 1代表删除）',
    create_by   varchar(64)  default '' comment '创建者',
    create_time datetime comment '创建时间',
    update_by   varchar(64)  default '' comment '更新者',
    update_time datetime comment '更新时间',
    primary key (id)
) engine = innodb
  auto_increment = 100 comment = '用户信息主表';
