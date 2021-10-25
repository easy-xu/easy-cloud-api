-- ----------------------------
-- 知识图谱节点表
-- ----------------------------
drop table if exists kl_knowledge_node;
create table kl_knowledge_node
(
    id          bigint(20)  not null auto_increment comment 'ID',
    name        varchar(60) not null comment '节点标题',
    parent_id   bigint(20)   default 0 comment '父节点ID',
    content_id  bigint(20) comment '内容ID',
    order_num   int(4)       default 0 comment '显示顺序',
    type        varchar(2)   default '' comment '节点类型（N节点 NC节点内容 C内容）',
    visible     char(1)      default 0 comment '菜单状态（0显示 1隐藏）',
    deleted     char(1)      default '0' comment '删除标志（0正常 1停用）',
    group_id    bigint(20) comment '数据分组',
    own_mode    char(1)      default 'w' comment '所有者权限（-不可读不可写 r可读 w可读可写）',
    group_mode  char(1)      default 'r' comment '同分组权限（-不可读不可写 r可读 w可读可写）',
    other_mode  char(1)      default 'r' comment '其他分组权限（-不可读不可写 r可读 w可读可写）',
    create_by   varchar(60) comment '创建者',
    create_time datetime comment '创建时间',
    update_by   varchar(60) comment '更新者',
    update_time datetime comment '更新时间',
    remark      varchar(500) default null comment '备注',
    primary key (id)
) engine = innodb
  auto_increment = 1 comment = '知识图谱节点表';


-- ----------------------------
-- 知识图谱内容表
-- ----------------------------
drop table if exists kl_knowledge_content;
create table kl_knowledge_content
(
    id          bigint(20) not null auto_increment comment 'ID',
    markdown    text       not null comment '内容markdown',
    deleted     char(1)      default '0' comment '删除标志（0正常 1停用）',
    group_id    bigint(20) comment '数据分组',
    own_mode    char(1)      default 'w' comment '所有者权限（-不可读不可写 r可读 w可读可写）',
    group_mode  char(1)      default 'r' comment '同分组权限（-不可读不可写 r可读 w可读可写）',
    other_mode  char(1)      default 'r' comment '其他分组权限（-不可读不可写 r可读 w可读可写）',
    create_by   varchar(60) comment '创建者',
    create_time datetime comment '创建时间',
    update_by   varchar(60) comment '更新者',
    update_time datetime comment '更新时间',
    remark      varchar(500) default null comment '备注',
    primary key (id)
) engine = innodb
  auto_increment = 1 comment = '知识图谱节点表';
