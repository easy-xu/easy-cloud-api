-- ----------------------------
-- 用户主表
-- ----------------------------
drop table if exists cms_user;
create table cms_user
(
    id           bigint(20)  not null auto_increment comment '用户ID',
    username     varchar(60) not null comment '用户账号',
    user_no      varchar(60) comment '用户编号',
    password     varchar(100) comment '密码',
    nickname     varchar(30) comment '用户昵称',
    type         varchar(2) default '00' comment '用户类型（00访客）',
    token        varchar(300) comment '认证',
    email        varchar(50) comment '用户邮箱',
    phone_number varchar(11) comment '手机号码',
    sex          char(1)    default '2' comment '用户性别（0男 1女 2未知）',
    avatar       varchar(100) comment '头像地址',
    status       char(1)    default '0' comment '状态（0正常 1停用）',
    deleted      char(1)    default '0' comment '删除标志（0正常 1停用）',
    create_by    varchar(60) comment '创建者',
    create_time  datetime comment '创建时间',
    update_by    varchar(60) comment '更新者',
    update_time  datetime comment '更新时间',
    primary key (id)
) engine = innodb
  auto_increment = 1 comment = '用户信息主表';
ALTER TABLE `cms_user`
    ADD INDEX `index_username` (`username`) USING BTREE;
ALTER TABLE `cms_user`
    ADD INDEX `index_user_no` (`user_no`) USING BTREE;

-- ----------------------------
-- 角色主表
-- ----------------------------
drop table if exists cms_role;
create table cms_role
(
    id          bigint(20)   not null auto_increment comment '角色ID',
    name        varchar(30)  not null comment '角色名称',
    code        varchar(100) not null comment '角色权限字符串',
    status      char(1)      default '0' comment '状态（0正常 1停用）',
    deleted     char(1)      default '0' comment '删除标志（0正常 1停用）',
    create_by   varchar(60) comment '创建者',
    create_time datetime comment '创建时间',
    update_by   varchar(60) comment '更新者',
    update_time datetime comment '更新时间',
    remark      varchar(500) default null comment '备注',
    primary key (id)
) engine = innodb
  auto_increment = 1 comment = '角色信息表';

-- ----------------------------
-- 菜单权限表
-- ----------------------------
drop table if exists cms_menu;
create table cms_menu
(
    id          bigint(20)  not null auto_increment comment '菜单ID',
    name        varchar(50) not null comment '菜单名称',
    parent_id   bigint(20)   default 0 comment '父菜单ID',
    order_num   int(4)       default 0 comment '显示顺序',
    path        varchar(200) default '' comment '路由地址',
    component   varchar(255) default null comment '组件路径',
    type        char(1)      default '' comment '菜单类型（F目录 M菜单）',
    visible     char(1)      default 0 comment '菜单状态（0显示 1隐藏）',
    icon        varchar(100) default '' comment '菜单图标',
    status      char(1)      default '0' comment '状态（0正常 1停用）',
    deleted     char(1)      default '0' comment '删除标志（0正常 1停用）',
    create_by   varchar(60) comment '创建者',
    create_time datetime comment '创建时间',
    update_by   varchar(60) comment '更新者',
    update_time datetime comment '更新时间',
    remark      varchar(500) default null comment '备注',
    primary key (id)
) engine = innodb
  auto_increment = 1 comment = '菜单权限表';

-- ----------------------------
-- 用户和角色关联表  用户N-1角色
-- ----------------------------
drop table if exists cms_user_role;
create table cms_user_role
(
    id          bigint(20) not null auto_increment comment '主键',
    user_id     bigint(20) not null comment '用户ID',
    role_id     bigint(20) not null comment '角色ID',
    create_by   varchar(60) comment '创建者',
    create_time datetime comment '创建时间',
    update_by   varchar(60) comment '更新者',
    update_time datetime comment '更新时间',
    primary key (id)
) engine = innodb
  auto_increment = 1 comment = '用户和角色关联表';

-- ----------------------------
-- 角色和菜单关联表  角色1-N菜单
-- ----------------------------
drop table if exists cms_role_menu;
create table cms_role_menu
(
    id          bigint(20) not null auto_increment comment '主键',
    role_id     bigint(20) not null comment '角色ID',
    menu_id     bigint(20) not null comment '菜单ID',
    create_by   varchar(60) comment '创建者',
    create_time datetime comment '创建时间',
    update_by   varchar(60) comment '更新者',
    update_time datetime comment '更新时间',
    primary key (id)
) engine = innodb
  auto_increment = 1 comment = '角色和菜单关联表';

-- ----------------------------
-- 接口日志表字段
-- ----------------------------
drop table if exists api_log;
create table api_log
(
    id               bigint(20) not null auto_increment comment '主键',
    request_id       varchar(60) comment '请求流水号',
    request_code     varchar(20) comment '接口编码',
    request_path     varchar(20) comment '接口地址',
    business_no      varchar(20) comment '业务编号',
    sys_code         varchar(20) comment '系统编号',
    used_time        bigint(20) comment '接口耗时',
    response_code    bigint(20) comment '结果编码',
    response_message text comment '结果描述',
    deleted          char(1) default '0' comment '删除标志（0正常 1停用）',
    create_by        varchar(60) comment '创建者',
    create_time      datetime comment '创建时间',
    update_time      datetime comment '更新时间',
    update_by        varchar(60) comment '更新者',
    primary key (id)
) engine = innodb
  auto_increment = 1 comment = '接口日志表';
ALTER TABLE api_log
    ADD INDEX `index_request_id` (`request_id`) USING BTREE;

-- ----------------------------
-- 自增序列配置表
-- ----------------------------
drop table if exists seq_max_no;
create table seq_max_no
(
    id          bigint(20)  not null auto_increment comment '主键',
    no_type     varchar(60) not null comment '序列号类型',
    no_limit    varchar(60) not null comment '序列号小类型',
    max_no      bigint(20) comment '已申请序列号',
    no_step     bigint(20) comment '序列号步长',
    deleted     char(1)     default '0' comment '删除标志（0正常 1停用）',
    create_by   varchar(60) default '' comment '创建者',
    create_time datetime comment '创建时间',
    update_by   varchar(60) default '' comment '更新者',
    update_time datetime comment '更新时间',
    primary key (id)
) engine = innodb
  auto_increment = 1 comment = '自增序列配置表';
ALTER TABLE `seq_max_no`
    ADD INDEX `index_no_query` (`no_type`, no_limit) USING BTREE;


-- ----------------------------
-- 系统文件主表
-- ----------------------------
drop table if exists file_master;
create table file_master
(
    id            bigint(20)  not null auto_increment comment '主键',
    original_name varchar(200) comment '原文件名',
    server_name   varchar(200) comment '服务器文件名',
    sha256        varchar(60) not null comment '文件摘要',
    content_id    bigint(20) comment '文件内容Id',
    status        bigint(20) comment '文件状态（0临时文件 1正常文件）',
    deleted       char(1)     default '0' comment '删除标志（0正常 1停用）',
    create_by     varchar(60) default '' comment '创建者',
    create_time   datetime comment '创建时间',
    update_by     varchar(60) default '' comment '更新者',
    update_time   datetime comment '更新时间',
    primary key (id)
) engine = innodb
  auto_increment = 1 comment = '文件主表';
ALTER TABLE `file_master`
    ADD INDEX `index_sha256` (`sha256`) USING BTREE;

-- ----------------------------
-- 系统文件内容表
-- ----------------------------
drop table if exists file_content;
create table file_content
(
    id          bigint(20) not null auto_increment comment '主键',
    content     MEDIUMTEXT comment '文件内容',
    deleted     char(1)     default '0' comment '删除标志（0正常 1停用）',
    create_by   varchar(60) default '' comment '创建者',
    create_time datetime comment '创建时间',
    update_by   varchar(60) default '' comment '更新者',
    update_time datetime comment '更新时间',
    primary key (id)
) engine = innodb
  auto_increment = 1 comment = '文件内容表';


-- 数据备份

INSERT INTO `simple-cloud`.`cms_menu`(`id`, `name`, `parent_id`, `order_num`, `path`, `component`, `type`, `visible`,
                                      `icon`, `status`, `deleted`, `create_by`, `create_time`, `update_by`,
                                      `update_time`, `remark`)
VALUES (1, '系统管理', 0, 0, 'cms', NULL, 'F', '0', 'SettingFilled', '0', '0', 'admin', '2021-10-12 17:38:52', 'admin',
        '2021-10-12 21:27:49', NULL);
INSERT INTO `simple-cloud`.`cms_menu`(`id`, `name`, `parent_id`, `order_num`, `path`, `component`, `type`, `visible`,
                                      `icon`, `status`, `deleted`, `create_by`, `create_time`, `update_by`,
                                      `update_time`, `remark`)
VALUES (2, '菜单管理', 1, 1, 'menu', NULL, 'M', '0', '', '0', '0', 'admin', '2021-10-12 19:33:27', 'admin',
        '2021-10-12 20:56:20', NULL);
INSERT INTO `simple-cloud`.`cms_menu`(`id`, `name`, `parent_id`, `order_num`, `path`, `component`, `type`, `visible`,
                                      `icon`, `status`, `deleted`, `create_by`, `create_time`, `update_by`,
                                      `update_time`, `remark`)
VALUES (3, '角色管理', 1, 2, 'role', NULL, 'M', '0', '', '0', '0', 'admin', '2021-10-12 20:29:45', 'admin',
        '2021-10-12 20:56:33', NULL);
INSERT INTO `simple-cloud`.`cms_menu`(`id`, `name`, `parent_id`, `order_num`, `path`, `component`, `type`, `visible`,
                                      `icon`, `status`, `deleted`, `create_by`, `create_time`, `update_by`,
                                      `update_time`, `remark`)
VALUES (4, '用户管理', 1, 3, 'user', NULL, 'M', '0', '', '0', '0', 'admin', '2021-10-12 21:07:21', 'admin',
        '2021-10-12 21:07:21', NULL);


INSERT INTO `simple-cloud`.`cms_role`(`id`, `name`, `code`, `status`, `deleted`, `create_by`, `create_time`,
                                      `update_by`, `update_time`, `remark`)
VALUES (1, '系统管理员', 'admin', '0', '0', 'admin', '2021-10-12 21:55:40', 'admin', '2021-10-12 21:55:40', NULL);
INSERT INTO `simple-cloud`.`cms_role`(`id`, `name`, `code`, `status`, `deleted`, `create_by`, `create_time`,
                                      `update_by`, `update_time`, `remark`)
VALUES (2, '只读用户', 'readable', '0', '0', 'admin', '2021-10-12 21:56:22', 'admin', '2021-10-12 21:57:33', NULL);
INSERT INTO `simple-cloud`.`cms_role`(`id`, `name`, `code`, `status`, `deleted`, `create_by`, `create_time`,
                                      `update_by`, `update_time`, `remark`)
VALUES (3, '可写用户', 'writable', '0', '0', 'admin', '2021-10-12 21:56:50', 'admin', '2021-10-12 21:57:15', NULL);
