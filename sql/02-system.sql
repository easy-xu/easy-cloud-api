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
    deleted      char(1)    default '0' comment '删除标志（0正常 1停用）',
    group_id     bigint(20) comment '数据分组',
    own_mode     char(1)    default 'w' comment '所有者权限（-不可读不可写 r可读 w可读可写）',
    group_mode   char(1)    default 'r' comment '同分组权限（-不可读不可写 r可读 w可读可写）',
    other_mode   char(1)    default '-' comment '其他分组权限（-不可读不可写 r可读 w可读可写）',
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
-- 分组表
-- ----------------------------
drop table if exists cms_group;
create table cms_group
(
    id          bigint(20)   not null auto_increment comment '分组ID',
    name        varchar(30)  not null comment '分组名称',
    code        varchar(100) not null comment '分组字符串',
    deleted     char(1)      default '0' comment '删除标志（0正常 1停用）',
    group_id     bigint(20) comment '数据分组',
    own_mode     char(1)    default 'w' comment '所有者权限（-不可读不可写 r可读 w可读可写）',
    group_mode   char(1)    default 'r' comment '同分组权限（-不可读不可写 r可读 w可读可写）',
    other_mode   char(1)    default '-' comment '其他分组权限（-不可读不可写 r可读 w可读可写）',
    create_by   varchar(60) comment '创建者',
    create_time datetime comment '创建时间',
    update_by   varchar(60) comment '更新者',
    update_time datetime comment '更新时间',
    remark      varchar(500) default null comment '备注',
    primary key (id)
) engine = innodb
  auto_increment = 1 comment = '分组表';


-- ----------------------------
-- 角色主表
-- ----------------------------
drop table if exists cms_role;
create table cms_role
(
    id          bigint(20)   not null auto_increment comment '角色ID',
    name        varchar(30)  not null comment '角色名称',
    code        varchar(100) not null comment '角色权限字符串',
    deleted     char(1)      default '0' comment '删除标志（0正常 1停用）',
    group_id     bigint(20) comment '数据分组',
    own_mode     char(1)    default 'w' comment '所有者权限（-不可读不可写 r可读 w可读可写）',
    group_mode   char(1)    default 'r' comment '同分组权限（-不可读不可写 r可读 w可读可写）',
    other_mode   char(1)    default '-' comment '其他分组权限（-不可读不可写 r可读 w可读可写）',
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
    code        varchar(200) default '' comment '路径字符',
    parent_id   bigint(20)   default 0 comment '父菜单ID',
    order_num   int(4)       default 0 comment '显示顺序',
    component   varchar(255) default null comment '组件路径',
    type        char(1)      default '' comment '菜单类型（F目录 M菜单）',
    visible     char(1)      default 0 comment '菜单状态（0显示 1隐藏）',
    icon        varchar(100) default '' comment '菜单图标',
    deleted     char(1)      default '0' comment '删除标志（0正常 1停用）',
    group_id     bigint(20) comment '数据分组',
    own_mode     char(1)    default 'w' comment '所有者权限（-不可读不可写 r可读 w可读可写）',
    group_mode   char(1)    default 'r' comment '同分组权限（-不可读不可写 r可读 w可读可写）',
    other_mode   char(1)    default '-' comment '其他分组权限（-不可读不可写 r可读 w可读可写）',
    create_by   varchar(60) comment '创建者',
    create_time datetime comment '创建时间',
    update_by   varchar(60) comment '更新者',
    update_time datetime comment '更新时间',
    remark      varchar(500) default null comment '备注',
    primary key (id)
) engine = innodb
  auto_increment = 1 comment = '菜单权限表';

-- ----------------------------
-- 操作类型表
-- ----------------------------
drop table if exists cms_option;
create table cms_option
(
    id          bigint(20)   not null auto_increment comment '操作ID',
    name        varchar(50)  not null comment '操作名称',
    code        varchar(100) not null comment '操作字符串',
    deleted     char(1)      default '0' comment '删除标志（0正常 1停用）',
    group_id     bigint(20) comment '数据分组',
    own_mode     char(1)    default 'w' comment '所有者权限（-不可读不可写 r可读 w可读可写）',
    group_mode   char(1)    default 'r' comment '同分组权限（-不可读不可写 r可读 w可读可写）',
    other_mode   char(1)    default '-' comment '其他分组权限（-不可读不可写 r可读 w可读可写）',
    create_by   varchar(60) comment '创建者',
    create_time datetime comment '创建时间',
    update_by   varchar(60) comment '更新者',
    update_time datetime comment '更新时间',
    remark      varchar(500) default null comment '备注',
    primary key (id)
) engine = innodb
  auto_increment = 1 comment = '操作类型表';

-- ----------------------------
-- 权限表
-- ----------------------------
drop table if exists cms_auth;
create table cms_auth
(
    id          bigint(20)   not null auto_increment comment '权限ID',
    name        varchar(50)  not null comment '权限名称',
    code        varchar(100) not null comment '权限字符串',
    deleted     char(1)      default '0' comment '删除标志（0正常 1停用）',
    group_id     bigint(20) comment '数据分组',
    own_mode     char(1)    default 'w' comment '所有者权限（-不可读不可写 r可读 w可读可写）',
    group_mode   char(1)    default 'r' comment '同分组权限（-不可读不可写 r可读 w可读可写）',
    other_mode   char(1)    default '-' comment '其他分组权限（-不可读不可写 r可读 w可读可写）',
    create_by   varchar(60) comment '创建者',
    create_time datetime comment '创建时间',
    update_by   varchar(60) comment '更新者',
    update_time datetime comment '更新时间',
    remark      varchar(500) default null comment '备注',
    primary key (id)
) engine = innodb
  auto_increment = 1 comment = '权限表';

-- ----------------------------
-- 权限和操作关联表  权限1-N操作
-- ----------------------------
drop table if exists cms_auth_option;
create table cms_auth_option
(
    id          bigint(20) not null auto_increment comment '主键',
    auth_id     bigint(20) not null comment '权限ID',
    option_id   bigint(20) not null comment '操作ID',
    create_by   varchar(60) comment '创建者',
    create_time datetime comment '创建时间',
    update_by   varchar(60) comment '更新者',
    update_time datetime comment '更新时间',
    primary key (id)
) engine = innodb
  auto_increment = 1 comment = '操作和权限关联表';

-- ----------------------------
-- 权限和菜单关联表  权限1-N菜单
-- ----------------------------
drop table if exists cms_auth_menu;
create table cms_auth_menu
(
    id          bigint(20) not null auto_increment comment '主键',
    auth_id     bigint(20) not null comment '权限ID',
    menu_id     bigint(20) not null comment '菜单ID',
    create_by   varchar(60) comment '创建者',
    create_time datetime comment '创建时间',
    update_by   varchar(60) comment '更新者',
    update_time datetime comment '更新时间',
    primary key (id)
) engine = innodb
  auto_increment = 1 comment = '用户和角色关联表';

-- ----------------------------
-- 角色和权限关联表  角色1-N权限
-- ----------------------------
drop table if exists cms_role_auth;
create table cms_role_auth
(
    id          bigint(20) not null auto_increment comment '主键',
    role_id     bigint(20) not null comment '角色ID',
    auth_id     bigint(20) not null comment '权限ID',
    create_by   varchar(60) comment '创建者',
    create_time datetime comment '创建时间',
    update_by   varchar(60) comment '更新者',
    update_time datetime comment '更新时间',
    primary key (id)
) engine = innodb
  auto_increment = 1 comment = '角色和权限关联表';


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
    group_id     bigint(20) comment '数据分组',
    own_mode     char(1)    default 'w' comment '所有者权限（-不可读不可写 r可读 w可读可写）',
    group_mode   char(1)    default 'r' comment '同分组权限（-不可读不可写 r可读 w可读可写）',
    other_mode   char(1)    default '-' comment '其他分组权限（-不可读不可写 r可读 w可读可写）',
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
    create_by   varchar(60) default '' comment '创建者',
    create_time datetime comment '创建时间',
    update_by   varchar(60) default '' comment '更新者',
    update_time datetime comment '更新时间',
    primary key (id)
) engine = innodb
  auto_increment = 1 comment = '文件内容表';


-- 数据备份
INSERT INTO `simple-cloud`.`cms_option`(`id`, `name`, `code`, `deleted`, `create_by`, `create_time`, `update_by`,
                                        `update_time`, `remark`)
VALUES (1, '查询', 'query', '0', 'U20211013000001', '2021-10-14 16:05:53', 'U20211013000001', '2021-10-14 16:05:53',
        NULL);
INSERT INTO `simple-cloud`.`cms_option`(`id`, `name`, `code`, `deleted`, `create_by`, `create_time`, `update_by`,
                                        `update_time`, `remark`)
VALUES (2, '查看', 'view', '0', 'U20211013000001', '2021-10-14 16:06:18', 'U20211013000001', '2021-10-14 16:06:18', NULL);
INSERT INTO `simple-cloud`.`cms_option`(`id`, `name`, `code`, `deleted`, `create_by`, `create_time`, `update_by`,
                                        `update_time`, `remark`)
VALUES (3, '新增', 'add', '0', 'U20211013000001', '2021-10-14 16:06:31', 'U20211013000001', '2021-10-14 16:06:31', NULL);
INSERT INTO `simple-cloud`.`cms_option`(`id`, `name`, `code`, `deleted`, `create_by`, `create_time`, `update_by`,
                                        `update_time`, `remark`)
VALUES (4, '修改', 'edit', '0', 'U20211013000001', '2021-10-14 16:06:42', 'U20211013000001', '2021-10-14 16:06:42', NULL);
INSERT INTO `simple-cloud`.`cms_option`(`id`, `name`, `code`, `deleted`, `create_by`, `create_time`, `update_by`,
                                        `update_time`, `remark`)
VALUES (5, '删除', 'delete', '0', 'U20211013000001', '2021-10-14 16:06:54', 'U20211013000001', '2021-10-14 16:06:54',
        NULL);

INSERT INTO `simple-cloud`.`cms_menu`(`id`, `name`, `parent_id`, `order_num`, `code`, `component`, `type`, `visible`,
                                      `icon`, `deleted`, `create_by`, `create_time`, `update_by`, `update_time`,
                                      `remark`)
VALUES (1, '系统管理', 0, 0, 'manage', NULL, 'F', '0', 'SettingOutlined', '0', 'admin', '2021-10-12 17:38:52', 'admin',
        '2021-10-13 22:02:59', NULL);
INSERT INTO `simple-cloud`.`cms_menu`(`id`, `name`, `parent_id`, `order_num`, `code`, `component`, `type`, `visible`,
                                      `icon`, `deleted`, `create_by`, `create_time`, `update_by`, `update_time`,
                                      `remark`)
VALUES (2, '菜单管理', 1, 1, 'menu', NULL, 'M', '0', '', '0', 'admin', '2021-10-12 19:33:27', 'admin',
        '2021-10-12 20:56:20', NULL);
INSERT INTO `simple-cloud`.`cms_menu`(`id`, `name`, `parent_id`, `order_num`, `code`, `component`, `type`, `visible`,
                                      `icon`, `deleted`, `create_by`, `create_time`, `update_by`, `update_time`,
                                      `remark`)
VALUES (3, '角色管理', 1, 3, 'role', NULL, 'M', '0', '', '0', 'admin', '2021-10-12 20:29:45', 'U20211013000001',
        '2021-10-14 16:09:40', NULL);
INSERT INTO `simple-cloud`.`cms_menu`(`id`, `name`, `parent_id`, `order_num`, `code`, `component`, `type`, `visible`,
                                      `icon`, `deleted`, `create_by`, `create_time`, `update_by`, `update_time`,
                                      `remark`)
VALUES (4, '用户管理', 1, 4, 'user', NULL, 'M', '0', '', '0', 'admin', '2021-10-12 21:07:21', 'U20211013000001',
        '2021-10-14 16:09:47', NULL);
INSERT INTO `simple-cloud`.`cms_menu`(`id`, `name`, `parent_id`, `order_num`, `code`, `component`, `type`, `visible`,
                                      `icon`, `deleted`, `create_by`, `create_time`, `update_by`, `update_time`,
                                      `remark`)
VALUES (5, '问卷管理', 0, 1, 'questionnaire', NULL, 'F', '0', 'AuditOutlined', '0', 'admin', '2021-10-13 21:39:38',
        'U20211013000001', '2021-10-13 22:51:23', NULL);
INSERT INTO `simple-cloud`.`cms_menu`(`id`, `name`, `parent_id`, `order_num`, `code`, `component`, `type`, `visible`,
                                      `icon`, `deleted`, `create_by`, `create_time`, `update_by`, `update_time`,
                                      `remark`)
VALUES (6, '问卷导入', 5, 1, 'import', NULL, 'M', '0', '', '0', 'U20211013000001', '2021-10-13 22:52:11', 'U20211013000001',
        '2021-10-14 10:28:37', NULL);
INSERT INTO `simple-cloud`.`cms_menu`(`id`, `name`, `parent_id`, `order_num`, `code`, `component`, `type`, `visible`,
                                      `icon`, `deleted`, `create_by`, `create_time`, `update_by`, `update_time`,
                                      `remark`)
VALUES (7, '操作管理', 1, 0, 'option', NULL, 'M', '0', '', '0', 'U20211013000001', '2021-10-14 16:02:10', 'U20211013000001',
        '2021-10-14 16:12:03', NULL);
INSERT INTO `simple-cloud`.`cms_menu`(`id`, `name`, `parent_id`, `order_num`, `code`, `component`, `type`, `visible`,
                                      `icon`, `deleted`, `create_by`, `create_time`, `update_by`, `update_time`,
                                      `remark`)
VALUES (8, '权限配置', 1, 2, 'auth', NULL, 'M', '0', '', '0', 'U20211013000001', '2021-10-14 16:09:12', 'U20211013000001',
        '2021-10-14 16:09:12', NULL);

INSERT INTO `simple-cloud`.`cms_role`(`id`, `name`, `code`, `deleted`, `create_by`, `create_time`, `update_by`,
                                      `update_time`, `remark`)
VALUES (1, '系统管理员', 'admin', '0', 'admin', '2021-10-12 21:55:40', 'admin', '2021-10-13 17:53:43', NULL);
INSERT INTO `simple-cloud`.`cms_role`(`id`, `name`, `code`, `deleted`, `create_by`, `create_time`, `update_by`,
                                      `update_time`, `remark`)
VALUES (2, '只读用户', 'readable', '0', 'admin', '2021-10-12 21:56:22', 'U20211013000001', '2021-10-14 10:28:43', NULL);
INSERT INTO `simple-cloud`.`cms_role`(`id`, `name`, `code`, `deleted`, `create_by`, `create_time`, `update_by`,
                                      `update_time`, `remark`)
VALUES (3, '可写用户', 'writable', '0', 'admin', '2021-10-12 21:56:50', 'admin', '2021-10-12 21:57:15', NULL);

INSERT INTO `simple-cloud`.`cms_user_role`(`id`, `user_id`, `role_id`, `create_by`, `create_time`, `update_by`,
                                           `update_time`)
VALUES (16, 1, 1, 'U20211013000001', '2021-10-14 10:27:24', 'U20211013000001', '2021-10-14 10:27:24');

INSERT INTO `simple-cloud`.`cms_user`(`id`, `username`, `user_no`, `password`, `nickname`, `type`, `token`, `email`,
                                      `phone_number`, `sex`, `avatar`, `deleted`, `create_by`, `create_time`,
                                      `update_by`, `update_time`)
VALUES (1, 'admin', 'U20211013000001', 'B7exzphzxZANPZkW2rArP651aTbKemYuaVZ8yhj9jsAG', NULL, '01',
        'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjoiVTIwMjExMDEzMDAwMDAxIiwiaWF0IjoxNjM0MTc1OTEzfQ.aBv19JPSIcZT_hyMXInU8qKVfeKIJZ2dBqQhSq_ZhgA',
        NULL, NULL, '2', NULL, '0', NULL, '2021-10-13 20:35:41', 'U20211013000001', '2021-10-14 10:27:24');
