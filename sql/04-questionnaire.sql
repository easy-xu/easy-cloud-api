-- ----------------------------
-- 测试问卷主表
-- ----------------------------
drop table if exists quna_config_questionnaire;
create table quna_config_questionnaire
(
    id              bigint(20) not null auto_increment comment '问卷ID',
    title           varchar(30) comment '问卷标题',
    short_desc      text comment '简要描述',
    question_num    bigint(20) comment '问题个数',
    participant_num bigint(20) comment '参与人数',
    like_num        bigint(20) comment '喜欢个数',
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
  auto_increment = 1 comment = '测试问卷主表';

-- ----------------------------
-- 测试问卷问题表
-- ----------------------------
drop table if exists quna_config_question;
create table quna_config_question
(
    id               bigint(20) not null auto_increment comment '问题ID',
    questionnaire_id bigint(20) not null comment '问卷ID',
    title            varchar(500) comment '问题标题',
    order_num        int(4) comment '问题排序',
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
  auto_increment = 1 comment = '测试问卷问题表';

-- ----------------------------
-- 测试问卷问题选项表
-- ----------------------------
drop table if exists quna_config_option;
create table quna_config_option
(
    id               bigint(20) not null auto_increment comment '选项ID',
    question_id      bigint(20) not null comment '问题ID',
    questionnaire_id bigint(20) not null comment '问卷ID',
    text             varchar(200) comment '选项文本',
    value            varchar(60) comment '选项值',
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
  auto_increment = 1 comment = '测试问卷问题选项表';

-- ----------------------------
-- 测试问卷结果配置表
-- ----------------------------
drop table if exists quna_config_result;
create table quna_config_result
(
    id               bigint(20) not null auto_increment comment '结果ID',
    questionnaire_id bigint(20) not null comment '问卷ID',
    title            varchar(60) comment '标题',
    short_desc       text comment '简要描述',
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
  auto_increment = 1 comment = '测试问卷结果配置表';


-- ----------------------------
-- 测试问卷结果描述配置表
-- ----------------------------
drop table if exists quna_config_result_description;
create table quna_config_result_description
(
    id               bigint(20) not null auto_increment comment '描述ID',
    result_id        bigint(20) not null comment '结果ID',
    questionnaire_id bigint(20) not null comment '问卷ID',
    name             varchar(60) comment '名称',
    value            text comment '描述文本',
    order_num        int(4) comment '问题排序',
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
  auto_increment = 1 comment = '测试问卷结果配置表';

-- ----------------------------
-- 测试问卷结果分值配置表
-- ----------------------------
drop table if exists quna_config_result_score;
create table quna_config_result_score
(
    id               bigint(20) not null auto_increment comment '结果ID',
    questionnaire_id bigint(20) not null comment '问卷ID',
    question_id      bigint(20) not null comment '问题ID',
    option_id        bigint(20) not null comment '选项ID',
    result_id        bigint(20) not null comment '结果ID',
    score            bigint(20) comment '分值',
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
  auto_increment = 1 comment = '测试问卷结果分值配置表';


-- ----------------------------
-- 测试问卷记录表
-- ----------------------------
drop table if exists quna_answer_questionnaire;
create table quna_answer_questionnaire
(
    id               bigint(20) not null auto_increment comment '问卷回答ID',
    questionnaire_id bigint(20) not null comment '问卷ID',
    question_index   int(4) comment '当前问题编号',
    flow             bigint(20) comment '问卷回答流程（0初始化 1回答中 2回答完毕 3生成结果）',
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
  auto_increment = 1 comment = '测试问卷回答表';

-- ----------------------------
-- 测试问卷问题记录表
-- ----------------------------
drop table if exists quna_answer_question;
create table quna_answer_question
(
    id           bigint(20) not null auto_increment comment '问题回答ID',
    answer_id    bigint(20) not null comment '问卷回答ID',
    question_id  bigint(20) not null comment '问题ID',
    option_id    bigint(20) comment '问题选择选项ID',
    option_value varchar(60) comment '问题选择选项值',
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
  auto_increment = 1 comment = '测试问卷问题回答表';

-- ----------------------------
-- 测试问卷答案结果表
-- ----------------------------
drop table if exists quna_answer_result;
create table quna_answer_result
(
    id          bigint(20) not null auto_increment comment '结果ID',
    answer_id   bigint(20) not null comment '问卷回答ID',
    result_id   bigint(20) not null comment '结果ID',
    score       bigint(20) comment '分值',
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
  auto_increment = 1 comment = '测试问卷答案结果表';
