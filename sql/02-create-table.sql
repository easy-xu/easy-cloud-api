-- ----------------------------
-- 用户主表
-- ----------------------------
drop table if exists user_master;
create table user_master
(
    id           bigint(20)  not null auto_increment comment '用户ID',
    username     varchar(30) not null comment '用户账号',
    password     varchar(100) comment '密码',
    nickname     varchar(30) comment '用户昵称',
    type         varchar(2) default '00' comment '用户类型（00访客）',
    email        varchar(50) comment '用户邮箱',
    phone_number varchar(11) comment '手机号码',
    sex          char(1)    default '2' comment '用户性别（0男 1女 2未知）',
    avatar       varchar(100) comment '头像地址',
    status       char(1)    default '0' comment '帐号状态（0正常 1停用）',
    del_flag     char(1)    default '0' comment '删除标志（0代表存在 1代表删除）',
    create_by    varchar(64) comment '创建者',
    create_time  datetime comment '创建时间',
    update_by    varchar(64) comment '更新者',
    update_time  datetime comment '更新时间',
    primary key (id)
) engine = innodb
  auto_increment = 1 comment = '用户信息主表';

-- ----------------------------
-- 接口日志表字段
-- ----------------------------
drop table if exists sys_api_log;
create table sys_api_log
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
    create_by        varchar(64) comment '创建者',
    create_time      datetime comment '创建时间',
    update_time      datetime comment '更新时间',
    update_by        varchar(64) comment '更新者',
    primary key (id)
) engine = innodb
  auto_increment = 1 comment = '接口日志表';
ALTER TABLE sys_api_log
    ADD INDEX `index_request_id` (`request_id`) USING BTREE;


-- ----------------------------
-- 测试问卷主表
-- ----------------------------
drop table if exists quna_config_questionnaire;
create table quna_config_questionnaire
(
    id              bigint(20) not null auto_increment comment '问卷ID',
    title           varchar(30) comment '问卷标题',
    question_num    bigint(20) comment '问题个数',
    participant_num bigint(20) comment '参与人数',
    like_num        bigint(20) comment '喜欢个数',
    status          char(1) default '0' comment '问卷状态（0正常 1停用）',
    del_flag        char(1) default '0' comment '删除标志（0代表存在 1代表删除）',
    create_by       varchar(64) comment '创建者',
    create_time     datetime comment '创建时间',
    update_by       varchar(64) comment '更新者',
    update_time     datetime comment '更新时间',
    primary key (id)
) engine = innodb
  auto_increment = 1 comment = '测试问卷主表';

-- ----------------------------
-- 测试问卷问题表
-- ----------------------------
drop table if exists quna_config_question;
create table quna_config_question
(
    id          bigint(20) not null auto_increment comment '问题ID',
    quna_id     bigint(20) not null comment '问卷ID',
    title       varchar(500) comment '问题标题',
    status      char(1) default '0' comment '问题状态（0正常 1停用）',
    del_flag    char(1) default '0' comment '删除标志（0代表存在 1代表删除）',
    create_by   varchar(64) comment '创建者',
    create_time datetime comment '创建时间',
    update_by   varchar(64) comment '更新者',
    update_time datetime comment '更新时间',
    primary key (id)
) engine = innodb
  auto_increment = 1 comment = '测试问卷问题表';

-- ----------------------------
-- 测试问卷问题选项表
-- ----------------------------
drop table if exists quna_config_option;
create table quna_config_option
(
    id          bigint(20) not null auto_increment comment '选项ID',
    question_id bigint(20) not null comment '问题ID',
    text        varchar(200) comment '选项文本',
    status      char(1) default '0' comment '选项状态（0正常 1停用）',
    del_flag    char(1) default '0' comment '删除标志（0代表存在 1代表删除）',
    create_by   varchar(64) comment '创建者',
    create_time datetime comment '创建时间',
    update_by   varchar(64) comment '更新者',
    update_time datetime comment '更新时间',
    primary key (id)
) engine = innodb
  auto_increment = 1 comment = '测试问卷问题选项表';

-- ----------------------------
-- 测试问卷记录表
-- ----------------------------
drop table if exists quna_answer_questionnaire;
create table quna_answer_questionnaire
(
    id          bigint(20) not null auto_increment comment '问卷回答ID',
    quna_id     bigint(20) not null comment '问卷ID',
    status      char(1) default '0' comment '选项状态（0正常 1停用）',
    del_flag    char(1) default '0' comment '删除标志（0代表存在 1代表删除）',
    create_by   varchar(64) comment '创建者',
    create_time datetime comment '创建时间',
    update_by   varchar(64) comment '更新者',
    update_time datetime comment '更新时间',
    primary key (id)
) engine = innodb
  auto_increment = 1 comment = '测试问卷回答表';

-- ----------------------------
-- 测试问卷问题记录表
-- ----------------------------
drop table if exists quna_answer_question;
create table quna_answer_question
(
    id          bigint(20) not null auto_increment comment '问题回答ID',
    answer_id   bigint(20) not null comment '问卷回答ID',
    question_id bigint(20) not null comment '问题ID',
    status      char(1) default '0' comment '选项状态（0正常 1停用）',
    del_flag    char(1) default '0' comment '删除标志（0代表存在 1代表删除）',
    create_by   varchar(64) comment '创建者',
    create_time datetime comment '创建时间',
    update_by   varchar(64) comment '更新者',
    update_time datetime comment '更新时间',
    primary key (id)
) engine = innodb
  auto_increment = 1 comment = '测试问卷问题回答表';

-- questionnaire
-- quna_config_questionnaire,quna_config_question,quna_config_option,quna_answer_questionnaire,quna_answer_question
