-- ----------------------------
-- 菜单表
-- ----------------------------
drop table if exists cms_menu;
create table cms_menu
(
    id          bigint(20)  not null auto_increment comment '菜单ID',
    name        varchar(50) not null comment '菜单名称',
    code        varchar(200) not null comment '路径字符',
    parent_id   bigint(20)   not null comment '父菜单ID',
    order_num   int(4)       default 0 comment '显示顺序',
    component   varchar(255) comment '组件路径',
    type        char(1)      not null default 'M' comment '菜单类型(F:目录 M:菜单)',
    visible     char(1)      default '0' comment '菜单状态(0:显示 1:隐藏)',
    icon        varchar(100)  comment '菜单图标',
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
  auto_increment = 1 comment = '菜单表';

ALTER TABLE `cms_menu`
  ADD UNIQUE INDEX `unique_code`(`code`) USING BTREE;
ALTER TABLE `cms_menu`
  ADD INDEX `index_parent_id`(`parent_id`) USING BTREE;

-- ----------------------------
-- Records of cms_menu
-- ----------------------------
INSERT INTO `cloud`.`cms_menu`(`id`, `name`, `code`, `parent_id`, `order_num`, `component`, `type`, `visible`, `icon`, `deleted`, `group_id`, `own_mode`, `group_mode`, `other_mode`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1, '系统管理', 'manage', 0, 0, NULL, 'F', '0', 'SettingOutlined', '0', 1, 'w', 'r', 'r', 'U20211018000106', '2021-10-12 17:38:52', 'admin', '2021-10-13 22:02:59', NULL);
INSERT INTO `cloud`.`cms_menu`(`id`, `name`, `code`, `parent_id`, `order_num`, `component`, `type`, `visible`, `icon`, `deleted`, `group_id`, `own_mode`, `group_mode`, `other_mode`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2, '菜单管理', 'menu', 1, 1, NULL, 'M', '0', '', '0', 1, 'w', 'r', 'r', 'U20211018000106', '2021-10-12 19:33:27', 'admin', '2021-10-12 20:56:20', NULL);
INSERT INTO `cloud`.`cms_menu`(`id`, `name`, `code`, `parent_id`, `order_num`, `component`, `type`, `visible`, `icon`, `deleted`, `group_id`, `own_mode`, `group_mode`, `other_mode`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (3, '角色管理', 'role', 1, 3, NULL, 'M', '0', '', '0', 1, 'w', 'r', 'r', 'U20211018000106', '2021-10-12 20:29:45', 'U20211013000001', '2021-10-14 16:09:40', NULL);
INSERT INTO `cloud`.`cms_menu`(`id`, `name`, `code`, `parent_id`, `order_num`, `component`, `type`, `visible`, `icon`, `deleted`, `group_id`, `own_mode`, `group_mode`, `other_mode`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (4, '用户管理', 'user', 1, 5, NULL, 'M', '0', '', '0', 1, 'w', 'r', 'r', 'U20211018000106', '2021-10-12 21:07:21', 'U20211013000001', '2021-10-15 21:20:39', NULL);
INSERT INTO `cloud`.`cms_menu`(`id`, `name`, `code`, `parent_id`, `order_num`, `component`, `type`, `visible`, `icon`, `deleted`, `group_id`, `own_mode`, `group_mode`, `other_mode`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (5, '问卷管理', 'questionnaire', 0, 11, NULL, 'F', '0', 'AuditOutlined', '0', 1, 'w', 'r', 'r', 'U20211018000106', '2021-10-13 21:39:38', 'U20211018000106', '2021-12-04 10:22:53', NULL);
INSERT INTO `cloud`.`cms_menu`(`id`, `name`, `code`, `parent_id`, `order_num`, `component`, `type`, `visible`, `icon`, `deleted`, `group_id`, `own_mode`, `group_mode`, `other_mode`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (6, '问卷导入', 'import', 5, 1, NULL, 'M', '0', '', '0', 1, 'w', 'r', 'r', 'U20211018000106', '2021-10-13 22:52:11', 'U20211013000001', '2021-10-14 10:28:37', NULL);
INSERT INTO `cloud`.`cms_menu`(`id`, `name`, `code`, `parent_id`, `order_num`, `component`, `type`, `visible`, `icon`, `deleted`, `group_id`, `own_mode`, `group_mode`, `other_mode`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (7, '操作管理', 'option', 1, 0, NULL, 'M', '0', '', '0', 1, 'w', 'r', 'r', 'U20211018000106', '2021-10-14 16:02:10', 'U20211013000001', '2021-10-14 16:12:03', NULL);
INSERT INTO `cloud`.`cms_menu`(`id`, `name`, `code`, `parent_id`, `order_num`, `component`, `type`, `visible`, `icon`, `deleted`, `group_id`, `own_mode`, `group_mode`, `other_mode`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (8, '权限配置', 'auth', 1, 2, NULL, 'M', '0', '', '0', 1, 'w', 'r', 'r', 'U20211018000106', '2021-10-14 16:09:12', 'U20211013000001', '2021-10-15 21:06:49', NULL);
INSERT INTO `cloud`.`cms_menu`(`id`, `name`, `code`, `parent_id`, `order_num`, `component`, `type`, `visible`, `icon`, `deleted`, `group_id`, `own_mode`, `group_mode`, `other_mode`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (9, '分组管理', 'group', 1, 4, NULL, 'M', '0', '', '0', 1, 'w', 'r', 'r', 'U20211018000106', '2021-10-15 21:20:10', 'U20211013000001', '2021-10-15 21:20:46', NULL);
INSERT INTO `cloud`.`cms_menu`(`id`, `name`, `code`, `parent_id`, `order_num`, `component`, `type`, `visible`, `icon`, `deleted`, `group_id`, `own_mode`, `group_mode`, `other_mode`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (10, '知识图谱', 'knowledge', 0, 10, NULL, 'F', '0', 'DashboardOutlined', '0', 1, 'w', 'r', 'r', 'U20211018000106', '2021-10-24 16:35:40', 'U20211018000106', '2021-12-04 10:22:16', NULL);
INSERT INTO `cloud`.`cms_menu`(`id`, `name`, `code`, `parent_id`, `order_num`, `component`, `type`, `visible`, `icon`, `deleted`, `group_id`, `own_mode`, `group_mode`, `other_mode`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (11, '节点管理', 'node', 10, 1, NULL, 'M', '0', '', '0', 1, 'w', 'r', 'r', 'U20211018000106', '2021-10-24 16:36:20', 'U20211018000106', '2021-10-25 09:43:51', NULL);
INSERT INTO `cloud`.`cms_menu`(`id`, `name`, `code`, `parent_id`, `order_num`, `component`, `type`, `visible`, `icon`, `deleted`, `group_id`, `own_mode`, `group_mode`, `other_mode`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (13, '系统查询', 'system', 0, 3, NULL, 'F', '0', 'DatabaseOutlined', '0', 1, 'w', 'r', 'r', 'U20211018000106', '2021-11-30 17:09:26', 'U20211018000106', '2021-12-04 10:22:45', NULL);
INSERT INTO `cloud`.`cms_menu`(`id`, `name`, `code`, `parent_id`, `order_num`, `component`, `type`, `visible`, `icon`, `deleted`, `group_id`, `own_mode`, `group_mode`, `other_mode`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (14, '接口查询', 'apilog', 13, 0, NULL, 'M', '0', '', '0', 1, 'w', 'r', 'r', 'U20211018000106', '2021-11-30 17:10:22', 'U20211018000106', '2021-11-30 17:27:07', NULL);
INSERT INTO `cloud`.`cms_menu`(`id`, `name`, `code`, `parent_id`, `order_num`, `component`, `type`, `visible`, `icon`, `deleted`, `group_id`, `own_mode`, `group_mode`, `other_mode`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (15, '操作查询', 'optionlog', 13, 1, NULL, 'M', '0', '', '0', 1, 'w', 'r', 'r', 'U20211018000106', '2021-11-30 17:10:52', 'U20211018000106', '2021-11-30 17:10:52', NULL);
INSERT INTO `cloud`.`cms_menu`(`id`, `name`, `code`, `parent_id`, `order_num`, `component`, `type`, `visible`, `icon`, `deleted`, `group_id`, `own_mode`, `group_mode`, `other_mode`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (16, '批处理管理', 'job', 0, 2, NULL, 'F', '0', 'CalendarOutlined', '0', NULL, 'w', 'r', '-', 'U20211018000106', '2021-12-04 10:23:50', 'U20211018000106', '2021-12-04 10:27:21', NULL);
INSERT INTO `cloud`.`cms_menu`(`id`, `name`, `code`, `parent_id`, `order_num`, `component`, `type`, `visible`, `icon`, `deleted`, `group_id`, `own_mode`, `group_mode`, `other_mode`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (17, '任务配置', 'config', 16, 1, NULL, 'M', '0', '', '0', NULL, 'w', 'r', '-', 'U20211018000106', '2021-12-04 10:24:48', 'U20211018000106', '2021-12-04 10:24:48', NULL);
INSERT INTO `cloud`.`cms_menu`(`id`, `name`, `code`, `parent_id`, `order_num`, `component`, `type`, `visible`, `icon`, `deleted`, `group_id`, `own_mode`, `group_mode`, `other_mode`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (18, '日志查询', 'log', 16, 2, NULL, 'M', '0', '', '0', NULL, 'w', 'r', '-', 'U20211018000106', '2021-12-04 10:25:15', 'U20211018000106', '2021-12-04 10:25:15', NULL);


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
ALTER TABLE `cms_option`
  ADD UNIQUE INDEX `unique_code`(`code`) USING BTREE;

-- ----------------------------
-- Records of cms_option
-- ----------------------------
INSERT INTO `cloud`.`cms_option`(`id`, `name`, `code`, `deleted`, `group_id`, `own_mode`, `group_mode`, `other_mode`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1, '查询', 'query', '0', 1, 'w', 'r', 'r', 'U20211018000106', '2021-10-14 16:05:53', 'U20211018000106', '2021-10-18 17:18:39', NULL);
INSERT INTO `cloud`.`cms_option`(`id`, `name`, `code`, `deleted`, `group_id`, `own_mode`, `group_mode`, `other_mode`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2, '新增', 'add', '0', 1, 'w', 'r', 'r', 'U20211018000106', '2021-10-14 16:06:31', 'U20211017000100', '2021-10-18 10:43:30', NULL);
INSERT INTO `cloud`.`cms_option`(`id`, `name`, `code`, `deleted`, `group_id`, `own_mode`, `group_mode`, `other_mode`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (3, '修改', 'edit', '0', 1, 'w', 'r', 'r', 'U20211018000106', '2021-10-14 16:06:42', 'U20211013000001', '2021-10-14 16:06:42', NULL);
INSERT INTO `cloud`.`cms_option`(`id`, `name`, `code`, `deleted`, `group_id`, `own_mode`, `group_mode`, `other_mode`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (4, '删除', 'delete', '0', 1, 'w', 'r', 'r', 'U20211018000106', '2021-10-14 16:06:54', 'U20211013000001', '2021-10-14 16:06:54', NULL);

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
ALTER TABLE `cms_auth`
  ADD UNIQUE INDEX `unique_code`(`code`) USING BTREE;

-- ----------------------------
-- Records of cms_auth
-- ----------------------------
INSERT INTO `cloud`.`cms_auth`(`id`, `name`, `code`, `deleted`, `group_id`, `own_mode`, `group_mode`, `other_mode`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1, '所有权限', 'All', '0', 1, 'w', 'r', 'r', 'U20211018000106', '2021-10-15 20:59:40', 'U20211018000106', '2021-12-04 10:25:40', NULL);
INSERT INTO `cloud`.`cms_auth`(`id`, `name`, `code`, `deleted`, `group_id`, `own_mode`, `group_mode`, `other_mode`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2, '所有只读权限', 'AllRead', '0', 1, 'w', 'r', 'r', 'U20211018000106', '2021-10-16 21:49:34', 'U20211018000106', '2021-10-25 09:42:07', NULL);
INSERT INTO `cloud`.`cms_auth`(`id`, `name`, `code`, `deleted`, `group_id`, `own_mode`, `group_mode`, `other_mode`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (3, '所有可写权限', 'AllWritable', '0', 1, 'w', 'r', 'r', 'U20211018000106', '2021-10-16 21:50:33', 'U20211018000106', '2021-10-25 09:42:14', NULL);


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
  auto_increment = 1 comment = '权限操作关联表';

-- ----------------------------
-- Records of cms_auth_option
-- ----------------------------
INSERT INTO `cloud`.`cms_auth_option`(`id`, `auth_id`, `option_id`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (25, 2, 1, 'U20211018000106', '2021-10-25 09:42:07', 'U20211018000106', '2021-10-25 09:42:07');
INSERT INTO `cloud`.`cms_auth_option`(`id`, `auth_id`, `option_id`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (26, 3, 1, 'U20211018000106', '2021-10-25 09:42:14', 'U20211018000106', '2021-10-25 09:42:14');
INSERT INTO `cloud`.`cms_auth_option`(`id`, `auth_id`, `option_id`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (27, 3, 2, 'U20211018000106', '2021-10-25 09:42:14', 'U20211018000106', '2021-10-25 09:42:14');
INSERT INTO `cloud`.`cms_auth_option`(`id`, `auth_id`, `option_id`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (28, 3, 3, 'U20211018000106', '2021-10-25 09:42:14', 'U20211018000106', '2021-10-25 09:42:14');
INSERT INTO `cloud`.`cms_auth_option`(`id`, `auth_id`, `option_id`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (37, 1, 1, 'U20211018000106', '2021-12-04 10:25:40', 'U20211018000106', '2021-12-04 10:25:40');
INSERT INTO `cloud`.`cms_auth_option`(`id`, `auth_id`, `option_id`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (38, 1, 2, 'U20211018000106', '2021-12-04 10:25:40', 'U20211018000106', '2021-12-04 10:25:40');
INSERT INTO `cloud`.`cms_auth_option`(`id`, `auth_id`, `option_id`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (39, 1, 3, 'U20211018000106', '2021-12-04 10:25:40', 'U20211018000106', '2021-12-04 10:25:40');
INSERT INTO `cloud`.`cms_auth_option`(`id`, `auth_id`, `option_id`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (40, 1, 4, 'U20211018000106', '2021-12-04 10:25:40', 'U20211018000106', '2021-12-04 10:25:40');

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
  auto_increment = 1 comment = '权限菜单关联表';

-- ----------------------------
-- Records of cms_auth_menu
-- ----------------------------
INSERT INTO `cloud`.`cms_auth_menu`(`id`, `auth_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (60, 2, 1, 'U20211018000106', '2021-10-25 09:42:07', 'U20211018000106', '2021-10-25 09:42:07');
INSERT INTO `cloud`.`cms_auth_menu`(`id`, `auth_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (61, 2, 5, 'U20211018000106', '2021-10-25 09:42:07', 'U20211018000106', '2021-10-25 09:42:07');
INSERT INTO `cloud`.`cms_auth_menu`(`id`, `auth_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (62, 2, 7, 'U20211018000106', '2021-10-25 09:42:07', 'U20211018000106', '2021-10-25 09:42:07');
INSERT INTO `cloud`.`cms_auth_menu`(`id`, `auth_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (63, 2, 2, 'U20211018000106', '2021-10-25 09:42:07', 'U20211018000106', '2021-10-25 09:42:07');
INSERT INTO `cloud`.`cms_auth_menu`(`id`, `auth_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (64, 2, 8, 'U20211018000106', '2021-10-25 09:42:07', 'U20211018000106', '2021-10-25 09:42:07');
INSERT INTO `cloud`.`cms_auth_menu`(`id`, `auth_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (65, 2, 3, 'U20211018000106', '2021-10-25 09:42:07', 'U20211018000106', '2021-10-25 09:42:07');
INSERT INTO `cloud`.`cms_auth_menu`(`id`, `auth_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (66, 2, 9, 'U20211018000106', '2021-10-25 09:42:07', 'U20211018000106', '2021-10-25 09:42:07');
INSERT INTO `cloud`.`cms_auth_menu`(`id`, `auth_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (67, 2, 4, 'U20211018000106', '2021-10-25 09:42:07', 'U20211018000106', '2021-10-25 09:42:07');
INSERT INTO `cloud`.`cms_auth_menu`(`id`, `auth_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (68, 2, 6, 'U20211018000106', '2021-10-25 09:42:07', 'U20211018000106', '2021-10-25 09:42:07');
INSERT INTO `cloud`.`cms_auth_menu`(`id`, `auth_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (69, 2, 10, 'U20211018000106', '2021-10-25 09:42:07', 'U20211018000106', '2021-10-25 09:42:07');
INSERT INTO `cloud`.`cms_auth_menu`(`id`, `auth_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (70, 2, 11, 'U20211018000106', '2021-10-25 09:42:07', 'U20211018000106', '2021-10-25 09:42:07');
INSERT INTO `cloud`.`cms_auth_menu`(`id`, `auth_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (71, 3, 1, 'U20211018000106', '2021-10-25 09:42:14', 'U20211018000106', '2021-10-25 09:42:14');
INSERT INTO `cloud`.`cms_auth_menu`(`id`, `auth_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (72, 3, 5, 'U20211018000106', '2021-10-25 09:42:14', 'U20211018000106', '2021-10-25 09:42:14');
INSERT INTO `cloud`.`cms_auth_menu`(`id`, `auth_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (73, 3, 7, 'U20211018000106', '2021-10-25 09:42:14', 'U20211018000106', '2021-10-25 09:42:14');
INSERT INTO `cloud`.`cms_auth_menu`(`id`, `auth_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (74, 3, 2, 'U20211018000106', '2021-10-25 09:42:14', 'U20211018000106', '2021-10-25 09:42:14');
INSERT INTO `cloud`.`cms_auth_menu`(`id`, `auth_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (75, 3, 8, 'U20211018000106', '2021-10-25 09:42:14', 'U20211018000106', '2021-10-25 09:42:14');
INSERT INTO `cloud`.`cms_auth_menu`(`id`, `auth_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (76, 3, 3, 'U20211018000106', '2021-10-25 09:42:14', 'U20211018000106', '2021-10-25 09:42:14');
INSERT INTO `cloud`.`cms_auth_menu`(`id`, `auth_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (77, 3, 9, 'U20211018000106', '2021-10-25 09:42:14', 'U20211018000106', '2021-10-25 09:42:14');
INSERT INTO `cloud`.`cms_auth_menu`(`id`, `auth_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (78, 3, 4, 'U20211018000106', '2021-10-25 09:42:14', 'U20211018000106', '2021-10-25 09:42:14');
INSERT INTO `cloud`.`cms_auth_menu`(`id`, `auth_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (79, 3, 6, 'U20211018000106', '2021-10-25 09:42:14', 'U20211018000106', '2021-10-25 09:42:14');
INSERT INTO `cloud`.`cms_auth_menu`(`id`, `auth_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (80, 3, 10, 'U20211018000106', '2021-10-25 09:42:14', 'U20211018000106', '2021-10-25 09:42:14');
INSERT INTO `cloud`.`cms_auth_menu`(`id`, `auth_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (81, 3, 11, 'U20211018000106', '2021-10-25 09:42:14', 'U20211018000106', '2021-10-25 09:42:14');
INSERT INTO `cloud`.`cms_auth_menu`(`id`, `auth_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (110, 1, 5, 'U20211018000106', '2021-12-04 10:25:40', 'U20211018000106', '2021-12-04 10:25:40');
INSERT INTO `cloud`.`cms_auth_menu`(`id`, `auth_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (111, 1, 6, 'U20211018000106', '2021-12-04 10:25:40', 'U20211018000106', '2021-12-04 10:25:40');
INSERT INTO `cloud`.`cms_auth_menu`(`id`, `auth_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (112, 1, 1, 'U20211018000106', '2021-12-04 10:25:40', 'U20211018000106', '2021-12-04 10:25:40');
INSERT INTO `cloud`.`cms_auth_menu`(`id`, `auth_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (113, 1, 7, 'U20211018000106', '2021-12-04 10:25:40', 'U20211018000106', '2021-12-04 10:25:40');
INSERT INTO `cloud`.`cms_auth_menu`(`id`, `auth_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (114, 1, 2, 'U20211018000106', '2021-12-04 10:25:40', 'U20211018000106', '2021-12-04 10:25:40');
INSERT INTO `cloud`.`cms_auth_menu`(`id`, `auth_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (115, 1, 8, 'U20211018000106', '2021-12-04 10:25:40', 'U20211018000106', '2021-12-04 10:25:40');
INSERT INTO `cloud`.`cms_auth_menu`(`id`, `auth_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (116, 1, 3, 'U20211018000106', '2021-12-04 10:25:40', 'U20211018000106', '2021-12-04 10:25:40');
INSERT INTO `cloud`.`cms_auth_menu`(`id`, `auth_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (117, 1, 9, 'U20211018000106', '2021-12-04 10:25:40', 'U20211018000106', '2021-12-04 10:25:40');
INSERT INTO `cloud`.`cms_auth_menu`(`id`, `auth_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (118, 1, 4, 'U20211018000106', '2021-12-04 10:25:40', 'U20211018000106', '2021-12-04 10:25:40');
INSERT INTO `cloud`.`cms_auth_menu`(`id`, `auth_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (119, 1, 10, 'U20211018000106', '2021-12-04 10:25:40', 'U20211018000106', '2021-12-04 10:25:40');
INSERT INTO `cloud`.`cms_auth_menu`(`id`, `auth_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (120, 1, 11, 'U20211018000106', '2021-12-04 10:25:40', 'U20211018000106', '2021-12-04 10:25:40');
INSERT INTO `cloud`.`cms_auth_menu`(`id`, `auth_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (121, 1, 13, 'U20211018000106', '2021-12-04 10:25:40', 'U20211018000106', '2021-12-04 10:25:40');
INSERT INTO `cloud`.`cms_auth_menu`(`id`, `auth_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (122, 1, 14, 'U20211018000106', '2021-12-04 10:25:40', 'U20211018000106', '2021-12-04 10:25:40');
INSERT INTO `cloud`.`cms_auth_menu`(`id`, `auth_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (123, 1, 15, 'U20211018000106', '2021-12-04 10:25:40', 'U20211018000106', '2021-12-04 10:25:40');
INSERT INTO `cloud`.`cms_auth_menu`(`id`, `auth_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (124, 1, 16, 'U20211018000106', '2021-12-04 10:25:40', 'U20211018000106', '2021-12-04 10:25:40');
INSERT INTO `cloud`.`cms_auth_menu`(`id`, `auth_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (125, 1, 17, 'U20211018000106', '2021-12-04 10:25:40', 'U20211018000106', '2021-12-04 10:25:40');
INSERT INTO `cloud`.`cms_auth_menu`(`id`, `auth_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (126, 1, 18, 'U20211018000106', '2021-12-04 10:25:40', 'U20211018000106', '2021-12-04 10:25:40');

-- ----------------------------
-- 角色表
-- ----------------------------
drop table if exists cms_role;
create table cms_role
(
    id          bigint(20)   not null auto_increment comment '角色ID',
    name        varchar(30)  not null comment '角色名称',
    code        varchar(100) not null comment '角色字符串',
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
  auto_increment = 1 comment = '角色表';
ALTER TABLE `cms_role`
  ADD UNIQUE INDEX `unique_code`(`code`) USING BTREE;

-- ----------------------------
-- Records of cms_role
-- ----------------------------
INSERT INTO `cloud`.`cms_role`(`id`, `name`, `code`, `deleted`, `group_id`, `own_mode`, `group_mode`, `other_mode`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1, '系统管理员', 'Admin', '0', 1, 'w', 'r', 'r', 'U20211018000106', '2021-10-12 21:55:40', 'U20211013000001', '2021-10-16 21:51:26', NULL);
INSERT INTO `cloud`.`cms_role`(`id`, `name`, `code`, `deleted`, `group_id`, `own_mode`, `group_mode`, `other_mode`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2, '只读角色', 'ReadRole', '0', 1, 'w', 'r', 'r', 'U20211018000106', '2021-10-12 21:56:22', 'U20211013000001', '2021-10-16 21:51:40', NULL);
INSERT INTO `cloud`.`cms_role`(`id`, `name`, `code`, `deleted`, `group_id`, `own_mode`, `group_mode`, `other_mode`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (3, '可写用户', 'WriteRole', '0', 1, 'w', 'r', 'r', 'U20211018000106', '2021-10-12 21:56:50', 'U20211013000001', '2021-10-16 21:51:53', NULL);

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
-- Records of cms_role_auth
-- ----------------------------
INSERT INTO `cloud`.`cms_role_auth`(`id`, `role_id`, `auth_id`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (4, 1, 1, 'U20211013000001', '2021-10-16 21:51:26', 'U20211013000001', '2021-10-16 21:51:26');
INSERT INTO `cloud`.`cms_role_auth`(`id`, `role_id`, `auth_id`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (5, 2, 2, 'U20211013000001', '2021-10-16 21:51:40', 'U20211013000001', '2021-10-16 21:51:40');
INSERT INTO `cloud`.`cms_role_auth`(`id`, `role_id`, `auth_id`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (6, 3, 3, 'U20211013000001', '2021-10-16 21:51:53', 'U20211013000001', '2021-10-16 21:51:53');


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
ALTER TABLE `cms_group`
  ADD UNIQUE INDEX `unique_code`(`code`) USING BTREE;

-- ----------------------------
-- Records of cms_group
-- ----------------------------
INSERT INTO `cloud`.`cms_group`(`id`, `name`, `code`, `deleted`, `group_id`, `own_mode`, `group_mode`, `other_mode`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (1, '系统分组', 'system', '0', 1, 'w', 'r', 'r', 'U20211018000106', '2021-10-15 21:29:31', 'U20211018000201', '2021-10-18 13:37:48', NULL);
INSERT INTO `cloud`.`cms_group`(`id`, `name`, `code`, `deleted`, `group_id`, `own_mode`, `group_mode`, `other_mode`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2, '默认分组', 'default', '0', 1, 'w', 'r', 'r', 'U20211018000106', '2021-10-16 21:48:15', 'U20211018000201', '2021-10-18 13:38:02', NULL);
INSERT INTO `cloud`.`cms_group`(`id`, `name`, `code`, `deleted`, `group_id`, `own_mode`, `group_mode`, `other_mode`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (3, '开发分组', 'dev', '0', 1, 'w', 'r', 'r', 'U20211018000106', '2021-10-16 21:48:28', 'U20211018000201', '2021-10-18 13:38:16', NULL);
INSERT INTO `cloud`.`cms_group`(`id`, `name`, `code`, `deleted`, `group_id`, `own_mode`, `group_mode`, `other_mode`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (4, '运维分组', 'ops', '0', 1, 'w', 'r', 'r', 'U20211018000106', '2021-10-18 13:18:19', 'U20211018000201', '2021-10-18 13:38:31', NULL);
INSERT INTO `cloud`.`cms_group`(`id`, `name`, `code`, `deleted`, `group_id`, `own_mode`, `group_mode`, `other_mode`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (5, '演示分组', 'demo', '0', 1, 'w', 'r', 'r', 'U20211018000106', '2021-11-29 17:06:04', 'U20211018000202', '2021-11-29 17:06:04', NULL);


-- ----------------------------
-- 用户表
-- ----------------------------
drop table if exists cms_user;
create table cms_user
(
    id           bigint(20)  not null auto_increment comment '用户ID',
    username     varchar(60) not null comment '用户名',
    user_no      varchar(60) comment '用户编号',
    device_no    varchar(60) comment '设备编号',
    password     varchar(100) comment '密码',
    nickname     varchar(30) comment '用户昵称',
    token        varchar(300) comment '认证',
    email        varchar(50) comment '用户邮箱',
    phone_number varchar(11) comment '手机号码',
    sex          char(1)    default 'N' comment '用户性别(M:男, F:女, N:未知)',
    avatar       varchar(100) comment '头像地址',
    default_group_id     bigint(20) comment '当前分组',
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
  auto_increment = 1 comment = '用户表';
ALTER TABLE `cms_user`
    ADD UNIQUE INDEX `index_username` (`username`) USING BTREE;
ALTER TABLE `cms_user`
    ADD INDEX `index_user_no` (`user_no`) USING BTREE;

INSERT INTO `cloud`.`cms_user`(`id`, `username`, `user_no`, `device_no`, `password`, `nickname`, `token`, `email`, `phone_number`, `sex`, `avatar`, `default_group_id`, `deleted`, `group_id`, `own_mode`, `group_mode`, `other_mode`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1, 'admin', 'U20211018000106', 'D20211129000200', 'J3KCwEKaFHA3gvmGqUjfT1A32MRzuyC5EPuZy3RWmVte', '系统管理员', '', NULL, NULL, 'N', NULL, 1, '0', 1, 'w', 'r', 'r', 'U20211018000106', '2021-10-13 20:35:41', 'U20211018000201', '2021-10-18 13:39:44');
INSERT INTO `cloud`.`cms_user`(`id`, `username`, `user_no`, `device_no`, `password`, `nickname`, `token`, `email`, `phone_number`, `sex`, `avatar`, `default_group_id`, `deleted`, `group_id`, `own_mode`, `group_mode`, `other_mode`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (2, 'dev', 'U20211018000202', 'D20211129000100', '8iLQLpiZZntVnMhZ8VX9YnhTvNREHK7kUrpCYXkgH5vC', '开发人员', '', NULL, NULL, 'N', NULL, 3, '0', 1, 'w', 'r', 'r', 'U20211018000106', '2021-10-16 21:59:42', 'U20211018000201', '2021-10-18 13:39:55');
INSERT INTO `cloud`.`cms_user`(`id`, `username`, `user_no`, `device_no`, `password`, `nickname`, `token`, `email`, `phone_number`, `sex`, `avatar`, `default_group_id`, `deleted`, `group_id`, `own_mode`, `group_mode`, `other_mode`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (3, 'ops', 'U20211018000201', 'D20211129000200', '7pvk763joCo25grKGoJfDVi9hHMkZoxJu8FLFoDDvyNU', '运维人员', '', NULL, NULL, 'N', NULL, 4, '0', 1, 'w', 'r', 'r', 'U20211018000106', '2021-10-16 22:00:00', 'U20211018000201', '2021-10-18 13:40:04');
INSERT INTO `cloud`.`cms_user`(`id`, `username`, `user_no`, `device_no`, `password`, `nickname`, `token`, `email`, `phone_number`, `sex`, `avatar`, `default_group_id`, `deleted`, `group_id`, `own_mode`, `group_mode`, `other_mode`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (4, 'demo', 'U20211129000000', 'D20211129000200', 'GR9UQsVXPsD3K7mxivrmcVrwaoFMFAyoJbh3S4iBrWd7', '演示用户', '', NULL, NULL, 'N', NULL, 5, '0', 1, 'w', 'r', 'r', 'U20211018000106', '2021-11-29 17:07:36', 'U20211018000202', '2021-11-29 17:07:36');


-- ----------------------------
-- 用户角色关联表  用户N-N角色
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
  auto_increment = 1 comment = '用户角色关联表';

-- ----------------------------
-- Records of cms_user_role
-- ----------------------------
INSERT INTO `cloud`.`cms_user_role`(`id`, `user_id`, `role_id`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (49, 1, 1, 'U20211018000201', '2021-10-18 13:39:44', 'U20211018000201', '2021-10-18 13:39:44');
INSERT INTO `cloud`.`cms_user_role`(`id`, `user_id`, `role_id`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (50, 2, 3, 'U20211018000201', '2021-10-18 13:39:55', 'U20211018000201', '2021-10-18 13:39:55');
INSERT INTO `cloud`.`cms_user_role`(`id`, `user_id`, `role_id`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (51, 3, 3, 'U20211018000201', '2021-10-18 13:40:04', 'U20211018000201', '2021-10-18 13:40:04');
INSERT INTO `cloud`.`cms_user_role`(`id`, `user_id`, `role_id`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (52, 4, 1, 'U20211018000202', '2021-11-29 17:07:36', 'U20211018000202', '2021-11-29 17:07:36');


-- ----------------------------
-- 用户分组关联表  用户N-N分组
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
  auto_increment = 1 comment = '用户分组关联表';
-- ----------------------------
-- Records of cms_user_group
-- ----------------------------
INSERT INTO `cloud`.`cms_user_group`(`id`, `user_id`, `group_id`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (28, 1, 1, 'U20211018000201', '2021-10-18 13:39:44', 'U20211018000201', '2021-10-18 13:39:44');
INSERT INTO `cloud`.`cms_user_group`(`id`, `user_id`, `group_id`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (29, 2, 3, 'U20211018000201', '2021-10-18 13:39:55', 'U20211018000201', '2021-10-18 13:39:55');
INSERT INTO `cloud`.`cms_user_group`(`id`, `user_id`, `group_id`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (30, 3, 4, 'U20211018000201', '2021-10-18 13:40:04', 'U20211018000201', '2021-10-18 13:40:04');
INSERT INTO `cloud`.`cms_user_group`(`id`, `user_id`, `group_id`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (31, 4, 5, 'U20211018000202', '2021-11-29 17:07:37', 'U20211018000202', '2021-11-29 17:07:37');


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
    status        bigint(20) comment '文件状态(0临时文件 1正常文件)',
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


