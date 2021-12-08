
-- ----------------------------
-- 用户主表
-- ----------------------------
drop table if exists cms_user;
create table cms_user
(
    id           bigint(20)  not null auto_increment comment '用户ID',
    username     varchar(60) not null comment '用户账号',
    user_no      varchar(60) comment '用户编号',
    device_no    varchar(60) comment '设备编号',
    password     varchar(100) comment '密码',
    nickname     varchar(30) comment '用户昵称',
    type         varchar(2) default '00' comment '用户类型（00访客）',
    token        varchar(300) comment '认证',
    email        varchar(50) comment '用户邮箱',
    phone_number varchar(11) comment '手机号码',
    sex          char(1)    default '2' comment '用户性别（0男 1女 2未知）',
    avatar       varchar(100) comment '头像地址',
    default_group_id     bigint(20) comment '用户默认分组',
    deleted      char(1)    default '0' comment '逻辑删除(0:正常, 1:停用)',
    group_id     bigint(20) comment '数据分组',
    own_mode     char(1)    default 'w' comment '所有者权限(-:不可读写, r:可读, w:可读可写)',
    group_mode   char(1)    default 'r' comment '同分组权限(-:不可读写, r:可读, w:可读可写)',
    other_mode   char(1)    default '-' comment '其他分组权限(-:不可读写, r:可读, w:可读可写)',
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
    deleted     char(1)      default '0' comment '逻辑删除(0:正常, 1:停用)',
    group_id     bigint(20) comment '数据分组',
    own_mode     char(1)    default 'w' comment '所有者权限(-:不可读写, r:可读, w:可读可写)',
    group_mode   char(1)    default 'r' comment '同分组权限(-:不可读写, r:可读, w:可读可写)',
    other_mode   char(1)    default 'r' comment '其他分组权限(-:不可读写, r:可读, w:可读可写)',
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
    deleted     char(1)      default '0' comment '逻辑删除(0:正常, 1:停用)',
    group_id     bigint(20) comment '数据分组',
    own_mode     char(1)    default 'w' comment '所有者权限(-:不可读写, r:可读, w:可读可写)',
    group_mode   char(1)    default 'r' comment '同分组权限(-:不可读写, r:可读, w:可读可写)',
    other_mode   char(1)    default 'r' comment '其他分组权限(-:不可读写, r:可读, w:可读可写)',
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
    deleted     char(1)      default '0' comment '逻辑删除(0:正常, 1:停用)',
    group_id     bigint(20) comment '数据分组',
    own_mode     char(1)    default 'w' comment '所有者权限(-:不可读写, r:可读, w:可读可写)',
    group_mode   char(1)    default 'r' comment '同分组权限(-:不可读写, r:可读, w:可读可写)',
    other_mode   char(1)    default 'r' comment '其他分组权限(-:不可读写, r:可读, w:可读可写)',
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
    deleted     char(1)      default '0' comment '逻辑删除(0:正常, 1:停用)',
    group_id     bigint(20) comment '数据分组',
    own_mode     char(1)    default 'w' comment '所有者权限(-:不可读写, r:可读, w:可读可写)',
    group_mode   char(1)    default 'r' comment '同分组权限(-:不可读写, r:可读, w:可读可写)',
    other_mode   char(1)    default 'r' comment '其他分组权限(-:不可读写, r:可读, w:可读可写)',
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
    deleted     char(1)      default '0' comment '逻辑删除(0:正常, 1:停用)',
    group_id     bigint(20) comment '数据分组',
    own_mode     char(1)    default 'w' comment '所有者权限(-:不可读写, r:可读, w:可读可写)',
    group_mode   char(1)    default 'r' comment '同分组权限(-:不可读写, r:可读, w:可读可写)',
    other_mode   char(1)    default 'r' comment '其他分组权限(-:不可读写, r:可读, w:可读可写)',
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
-- 用户和分组关联表  用户N-1分组
-- ----------------------------
drop table if exists cms_user_group;
create table cms_user_group
(
    id          bigint(20) not null auto_increment comment '主键',
    user_id     bigint(20) not null comment '用户ID',
    group_id     bigint(20) not null comment '分组ID',
    create_by   varchar(60) comment '创建者',
    create_time datetime comment '创建时间',
    update_by   varchar(60) comment '更新者',
    update_time datetime comment '更新时间',
    primary key (id)
) engine = innodb
  auto_increment = 1 comment = '用户和分组关联表';

-- ----------------------------
-- 接口日志表字段
-- ----------------------------
drop table if exists sys_api_log;
create table sys_api_log
(
    id               bigint(20) not null auto_increment comment '主键',
    request_id       varchar(60) comment '请求流水号',
    user_no          varchar(60) comment '用户编号',
    device_no        varchar(60) comment '设备编号',
    request_code     varchar(20) comment '接口编码',
    request_path     varchar(200) comment '接口地址',
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
ALTER TABLE sys_api_log
    ADD INDEX `index_request_id` (`request_id`) USING BTREE;


-- ----------------------------
-- 操作记录表
-- ----------------------------
drop table if exists sys_option_log;
create table sys_option_log
(
    id           bigint(20)  not null auto_increment comment '主键ID',
    user_no          varchar(60) comment '用户编号',
    device_no        varchar(60) comment '设备编号',
    option_name        varchar(60) comment '操作名称',
    create_by    varchar(60) comment '创建者',
    create_time  datetime comment '创建时间',
    update_by    varchar(60) comment '更新者',
    update_time  datetime comment '更新时间',
    primary key (id)
) engine = innodb
  auto_increment = 1 comment = '操作记录表';
ALTER TABLE `sys_option_log`
    ADD INDEX `index_device_no` (`device_no`) USING BTREE;
ALTER TABLE `sys_option_log`
    ADD INDEX `index_user_no` (`user_no`) USING BTREE;

-- ----------------------------
-- 自增序列配置表
-- ----------------------------
drop table if exists sys_max_no;
create table sys_max_no
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
ALTER TABLE `sys_max_no`
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
    deleted       char(1)     default '0' comment '逻辑删除(0:正常, 1:停用)',
    group_id     bigint(20) comment '数据分组',
    own_mode     char(1)    default 'w' comment '所有者权限(-:不可读写, r:可读, w:可读可写)',
    group_mode   char(1)    default 'r' comment '同分组权限(-:不可读写, r:可读, w:可读可写)',
    other_mode   char(1)    default '-' comment '其他分组权限(-:不可读写, r:可读, w:可读可写)',
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

-- ----------------------------
-- Records of cms_auth
-- ----------------------------
INSERT INTO `cms_auth` VALUES (1, '所有权限', 'All', '0', 1, 'w', 'r', 'r', 'U20211018000106', '2021-10-15 20:59:40', 'U20211013000001', '2021-10-15 21:28:19', NULL);
INSERT INTO `cms_auth` VALUES (2, '所有只读权限', 'AllRead', '0', 1, 'w', 'r', 'r', 'U20211018000106', '2021-10-16 21:49:34', 'U20211013000001', '2021-10-17 16:05:31', NULL);
INSERT INTO `cms_auth` VALUES (3, '所有可写权限', 'AllWritable', '0', 1, 'w', 'r', 'r', 'U20211018000106', '2021-10-16 21:50:33', 'U20211013000001', '2021-10-17 16:05:43', NULL);

-- ----------------------------
-- Records of cms_auth_menu
-- ----------------------------
INSERT INTO `cms_auth_menu` VALUES (18, 1, 5, 'U20211013000001', '2021-10-15 21:28:19', 'U20211013000001', '2021-10-15 21:28:19');
INSERT INTO `cms_auth_menu` VALUES (19, 1, 6, 'U20211013000001', '2021-10-15 21:28:19', 'U20211013000001', '2021-10-15 21:28:19');
INSERT INTO `cms_auth_menu` VALUES (20, 1, 1, 'U20211013000001', '2021-10-15 21:28:19', 'U20211013000001', '2021-10-15 21:28:19');
INSERT INTO `cms_auth_menu` VALUES (21, 1, 7, 'U20211013000001', '2021-10-15 21:28:19', 'U20211013000001', '2021-10-15 21:28:19');
INSERT INTO `cms_auth_menu` VALUES (22, 1, 2, 'U20211013000001', '2021-10-15 21:28:19', 'U20211013000001', '2021-10-15 21:28:19');
INSERT INTO `cms_auth_menu` VALUES (23, 1, 8, 'U20211013000001', '2021-10-15 21:28:19', 'U20211013000001', '2021-10-15 21:28:19');
INSERT INTO `cms_auth_menu` VALUES (24, 1, 3, 'U20211013000001', '2021-10-15 21:28:19', 'U20211013000001', '2021-10-15 21:28:19');
INSERT INTO `cms_auth_menu` VALUES (25, 1, 9, 'U20211013000001', '2021-10-15 21:28:19', 'U20211013000001', '2021-10-15 21:28:19');
INSERT INTO `cms_auth_menu` VALUES (26, 1, 4, 'U20211013000001', '2021-10-15 21:28:19', 'U20211013000001', '2021-10-15 21:28:19');
INSERT INTO `cms_auth_menu` VALUES (31, 2, 1, 'U20211013000001', '2021-10-17 16:05:31', 'U20211013000001', '2021-10-17 16:05:31');
INSERT INTO `cms_auth_menu` VALUES (32, 2, 5, 'U20211013000001', '2021-10-17 16:05:31', 'U20211013000001', '2021-10-17 16:05:31');
INSERT INTO `cms_auth_menu` VALUES (33, 2, 7, 'U20211013000001', '2021-10-17 16:05:31', 'U20211013000001', '2021-10-17 16:05:31');
INSERT INTO `cms_auth_menu` VALUES (34, 2, 2, 'U20211013000001', '2021-10-17 16:05:31', 'U20211013000001', '2021-10-17 16:05:31');
INSERT INTO `cms_auth_menu` VALUES (35, 2, 8, 'U20211013000001', '2021-10-17 16:05:31', 'U20211013000001', '2021-10-17 16:05:31');
INSERT INTO `cms_auth_menu` VALUES (36, 2, 3, 'U20211013000001', '2021-10-17 16:05:31', 'U20211013000001', '2021-10-17 16:05:31');
INSERT INTO `cms_auth_menu` VALUES (37, 2, 9, 'U20211013000001', '2021-10-17 16:05:31', 'U20211013000001', '2021-10-17 16:05:31');
INSERT INTO `cms_auth_menu` VALUES (38, 2, 4, 'U20211013000001', '2021-10-17 16:05:31', 'U20211013000001', '2021-10-17 16:05:31');
INSERT INTO `cms_auth_menu` VALUES (39, 2, 6, 'U20211013000001', '2021-10-17 16:05:31', 'U20211013000001', '2021-10-17 16:05:31');
INSERT INTO `cms_auth_menu` VALUES (40, 3, 1, 'U20211013000001', '2021-10-17 16:05:43', 'U20211013000001', '2021-10-17 16:05:43');
INSERT INTO `cms_auth_menu` VALUES (41, 3, 5, 'U20211013000001', '2021-10-17 16:05:43', 'U20211013000001', '2021-10-17 16:05:43');
INSERT INTO `cms_auth_menu` VALUES (42, 3, 7, 'U20211013000001', '2021-10-17 16:05:43', 'U20211013000001', '2021-10-17 16:05:43');
INSERT INTO `cms_auth_menu` VALUES (43, 3, 2, 'U20211013000001', '2021-10-17 16:05:43', 'U20211013000001', '2021-10-17 16:05:43');
INSERT INTO `cms_auth_menu` VALUES (44, 3, 8, 'U20211013000001', '2021-10-17 16:05:43', 'U20211013000001', '2021-10-17 16:05:43');
INSERT INTO `cms_auth_menu` VALUES (45, 3, 3, 'U20211013000001', '2021-10-17 16:05:43', 'U20211013000001', '2021-10-17 16:05:43');
INSERT INTO `cms_auth_menu` VALUES (46, 3, 9, 'U20211013000001', '2021-10-17 16:05:43', 'U20211013000001', '2021-10-17 16:05:43');
INSERT INTO `cms_auth_menu` VALUES (47, 3, 4, 'U20211013000001', '2021-10-17 16:05:43', 'U20211013000001', '2021-10-17 16:05:43');
INSERT INTO `cms_auth_menu` VALUES (48, 3, 6, 'U20211013000001', '2021-10-17 16:05:43', 'U20211013000001', '2021-10-17 16:05:43');

-- ----------------------------
-- Records of cms_auth_option
-- ----------------------------
INSERT INTO `cms_auth_option` VALUES (9, 1, 1, 'U20211013000001', '2021-10-15 21:28:19', 'U20211013000001', '2021-10-15 21:28:19');
INSERT INTO `cms_auth_option` VALUES (10, 1, 2, 'U20211013000001', '2021-10-15 21:28:19', 'U20211013000001', '2021-10-15 21:28:19');
INSERT INTO `cms_auth_option` VALUES (11, 1, 3, 'U20211013000001', '2021-10-15 21:28:19', 'U20211013000001', '2021-10-15 21:28:19');
INSERT INTO `cms_auth_option` VALUES (12, 1, 4, 'U20211013000001', '2021-10-15 21:28:19', 'U20211013000001', '2021-10-15 21:28:19');
INSERT INTO `cms_auth_option` VALUES (17, 2, 1, 'U20211013000001', '2021-10-17 16:05:31', 'U20211013000001', '2021-10-17 16:05:31');
INSERT INTO `cms_auth_option` VALUES (18, 3, 1, 'U20211013000001', '2021-10-17 16:05:43', 'U20211013000001', '2021-10-17 16:05:43');
INSERT INTO `cms_auth_option` VALUES (19, 3, 2, 'U20211013000001', '2021-10-17 16:05:43', 'U20211013000001', '2021-10-17 16:05:43');
INSERT INTO `cms_auth_option` VALUES (20, 3, 3, 'U20211013000001', '2021-10-17 16:05:43', 'U20211013000001', '2021-10-17 16:05:43');

-- ----------------------------
-- Records of cms_group
-- ----------------------------
INSERT INTO `cms_group` VALUES (1, '系统分组', 'system', '0', 1, 'w', 'r', 'r', 'U20211018000106', '2021-10-15 21:29:31', 'U20211018000201', '2021-10-18 13:37:48', NULL);
INSERT INTO `cms_group` VALUES (2, '默认分组', 'default', '0', 1, 'w', 'r', 'r', 'U20211018000106', '2021-10-16 21:48:15', 'U20211018000201', '2021-10-18 13:38:02', NULL);
INSERT INTO `cms_group` VALUES (3, '开发分组', 'dev', '0', 1, 'w', 'r', 'r', 'U20211018000106', '2021-10-16 21:48:28', 'U20211018000201', '2021-10-18 13:38:16', NULL);
INSERT INTO `cms_group` VALUES (4, '运维分组', 'ops', '0', 1, 'w', 'r', 'r', 'U20211018000106', '2021-10-18 13:18:19', 'U20211018000201', '2021-10-18 13:38:31', NULL);

-- ----------------------------
-- Records of cms_menu
-- ----------------------------
INSERT INTO `cms_menu` VALUES (1, '系统管理', 'manage', 0, 0, NULL, 'F', '0', 'SettingOutlined', '0', 1, 'w', 'r', 'r', 'U20211018000106', '2021-10-12 17:38:52', 'admin', '2021-10-13 22:02:59', NULL);
INSERT INTO `cms_menu` VALUES (2, '菜单管理', 'menu', 1, 1, NULL, 'M', '0', '', '0', 1, 'w', 'r', 'r', 'U20211018000106', '2021-10-12 19:33:27', 'admin', '2021-10-12 20:56:20', NULL);
INSERT INTO `cms_menu` VALUES (3, '角色管理', 'role', 1, 3, NULL, 'M', '0', '', '0', 1, 'w', 'r', 'r', 'U20211018000106', '2021-10-12 20:29:45', 'U20211013000001', '2021-10-14 16:09:40', NULL);
INSERT INTO `cms_menu` VALUES (4, '用户管理', 'user', 1, 5, NULL, 'M', '0', '', '0', 1, 'w', 'r', 'r', 'U20211018000106', '2021-10-12 21:07:21', 'U20211013000001', '2021-10-15 21:20:39', NULL);
INSERT INTO `cms_menu` VALUES (5, '问卷管理', 'questionnaire', 0, 1, NULL, 'F', '0', 'AuditOutlined', '0', 1, 'w', 'r', 'r', 'U20211018000106', '2021-10-13 21:39:38', 'U20211013000001', '2021-10-13 22:51:23', NULL);
INSERT INTO `cms_menu` VALUES (6, '问卷导入', 'import', 5, 1, NULL, 'M', '0', '', '0', 1, 'w', 'r', 'r', 'U20211018000106', '2021-10-13 22:52:11', 'U20211013000001', '2021-10-14 10:28:37', NULL);
INSERT INTO `cms_menu` VALUES (7, '操作管理', 'option', 1, 0, NULL, 'M', '0', '', '0', 1, 'w', 'r', 'r', 'U20211018000106', '2021-10-14 16:02:10', 'U20211013000001', '2021-10-14 16:12:03', NULL);
INSERT INTO `cms_menu` VALUES (8, '权限配置', 'auth', 1, 2, NULL, 'M', '0', '', '0', 1, 'w', 'r', 'r', 'U20211018000106', '2021-10-14 16:09:12', 'U20211013000001', '2021-10-15 21:06:49', NULL);
INSERT INTO `cms_menu` VALUES (9, '分组管理', 'group', 1, 4, NULL, 'M', '0', '', '0', 1, 'w', 'r', 'r', 'U20211018000106', '2021-10-15 21:20:10', 'U20211013000001', '2021-10-15 21:20:46', NULL);

-- ----------------------------
-- Records of cms_option
-- ----------------------------
INSERT INTO `cms_option` VALUES (1, '查询', 'query', '0', 1, 'w', 'r', 'r', 'U20211018000106', '2021-10-14 16:05:53', 'U20211018000106', '2021-10-18 17:18:39', NULL);
INSERT INTO `cms_option` VALUES (2, '新增', 'add', '0', 1, 'w', 'r', 'r', 'U20211018000106', '2021-10-14 16:06:31', 'U20211017000100', '2021-10-18 10:43:30', NULL);
INSERT INTO `cms_option` VALUES (3, '修改', 'edit', '0', 1, 'w', 'r', 'r', 'U20211018000106', '2021-10-14 16:06:42', 'U20211013000001', '2021-10-14 16:06:42', NULL);
INSERT INTO `cms_option` VALUES (4, '删除', 'delete', '0', 1, 'w', 'r', 'r', 'U20211018000106', '2021-10-14 16:06:54', 'U20211013000001', '2021-10-14 16:06:54', NULL);

-- ----------------------------
-- Records of cms_role
-- ----------------------------
INSERT INTO `cms_role` VALUES (1, '系统管理员', 'Admin', '0', 1, 'w', 'r', 'r', 'U20211018000106', '2021-10-12 21:55:40', 'U20211013000001', '2021-10-16 21:51:26', NULL);
INSERT INTO `cms_role` VALUES (2, '只读角色', 'ReadRole', '0', 1, 'w', 'r', 'r', 'U20211018000106', '2021-10-12 21:56:22', 'U20211013000001', '2021-10-16 21:51:40', NULL);
INSERT INTO `cms_role` VALUES (3, '可写用户', 'WriteRole', '0', 1, 'w', 'r', 'r', 'U20211018000106', '2021-10-12 21:56:50', 'U20211013000001', '2021-10-16 21:51:53', NULL);

-- ----------------------------
-- Records of cms_role_auth
-- ----------------------------
INSERT INTO `cms_role_auth` VALUES (4, 1, 1, 'U20211013000001', '2021-10-16 21:51:26', 'U20211013000001', '2021-10-16 21:51:26');
INSERT INTO `cms_role_auth` VALUES (5, 2, 2, 'U20211013000001', '2021-10-16 21:51:40', 'U20211013000001', '2021-10-16 21:51:40');
INSERT INTO `cms_role_auth` VALUES (6, 3, 3, 'U20211013000001', '2021-10-16 21:51:53', 'U20211013000001', '2021-10-16 21:51:53');

-- ----------------------------
-- Records of cms_user
-- ----------------------------
INSERT INTO `cloud`.`cms_user`(`id`, `username`, `user_no`, `password`, `nickname`, `type`, `token`, `email`, `phone_number`, `sex`, `avatar`, `default_group_id`, `deleted`, `group_id`, `own_mode`, `group_mode`, `other_mode`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1, 'admin', 'U20211018000106', '9ZPsmFqGpMkaSNg8Fxv8XR7wMsnpdV4eb4rUNntp184V', '系统管理员', '01', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjoiVTIwMjExMDE4MDAwMTA2IiwiaWF0IjoxNjM4MTAzOTI5fQ.gVwF6I27L6wwrELyibwGnFV1yMndxKV8MAyeP750618', NULL, NULL, '2', NULL, 1, '0', 1, 'w', 'r', 'r', 'U20211018000106', '2021-10-13 20:35:41', 'U20211018000201', '2021-10-18 13:39:44');
INSERT INTO `cloud`.`cms_user`(`id`, `username`, `user_no`, `password`, `nickname`, `type`, `token`, `email`, `phone_number`, `sex`, `avatar`, `default_group_id`, `deleted`, `group_id`, `own_mode`, `group_mode`, `other_mode`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (2, 'dev', 'U20211018000202', '8iLQLpiZZntVnMhZ8VX9YnhTvNREHK7kUrpCYXkgH5vC', '开发人员', '00', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjoiVTIwMjExMDE4MDAwMjAyIiwiaWF0IjoxNjM1MTI2MjM1fQ.hmeH5GJtMYAic6FRp7Ji1UKum7s5xT8YCRMzGxWeLo8', NULL, NULL, '2', NULL, 3, '0', 1, 'w', 'r', 'r', 'U20211018000106', '2021-10-16 21:59:42', 'U20211018000201', '2021-10-18 13:39:55');
INSERT INTO `cloud`.`cms_user`(`id`, `username`, `user_no`, `password`, `nickname`, `type`, `token`, `email`, `phone_number`, `sex`, `avatar`, `default_group_id`, `deleted`, `group_id`, `own_mode`, `group_mode`, `other_mode`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (3, 'ops', 'U20211018000201', '7pvk763joCo25grKGoJfDVi9hHMkZoxJu8FLFoDDvyNU', '运维人员', '00', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjoiVTIwMjExMDE4MDAwMjAxIiwiaWF0IjoxNjM0NTM1NDMxfQ.lQtFBPyqirUzEr3KAai03GaCgHeYDjrr3kJT_D2XUYA', NULL, NULL, '2', NULL, 4, '0', 1, 'w', 'r', 'r', 'U20211018000106', '2021-10-16 22:00:00', 'U20211018000201', '2021-10-18 13:40:04');

-- ----------------------------
-- Records of cms_user_group
-- ----------------------------
INSERT INTO `cms_user_group` VALUES (28, 1, 1, 'U20211018000201', '2021-10-18 13:39:44', 'U20211018000201', '2021-10-18 13:39:44');
INSERT INTO `cms_user_group` VALUES (29, 2, 3, 'U20211018000201', '2021-10-18 13:39:55', 'U20211018000201', '2021-10-18 13:39:55');
INSERT INTO `cms_user_group` VALUES (30, 3, 4, 'U20211018000201', '2021-10-18 13:40:04', 'U20211018000201', '2021-10-18 13:40:04');

-- ----------------------------
-- Records of cms_user_role
-- ----------------------------
INSERT INTO `cms_user_role` VALUES (49, 1, 1, 'U20211018000201', '2021-10-18 13:39:44', 'U20211018000201', '2021-10-18 13:39:44');
INSERT INTO `cms_user_role` VALUES (50, 2, 3, 'U20211018000201', '2021-10-18 13:39:55', 'U20211018000201', '2021-10-18 13:39:55');
INSERT INTO `cms_user_role` VALUES (51, 3, 3, 'U20211018000201', '2021-10-18 13:40:04', 'U20211018000201', '2021-10-18 13:40:04');

