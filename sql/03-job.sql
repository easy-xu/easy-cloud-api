
-- ----------------------------
-- 任务配置表
-- ----------------------------
drop table if exists job_config;
create table job_config
(
    id           bigint(20)  not null auto_increment comment '主键',
    name         varchar(60) not null comment '任务名称',
    cron         varchar(60) not null comment '周期表达式',
    bean_name    varchar(60) comment '任务类名',
    method_name  varchar(60) comment '任务方法名',
    params       varchar(500) comment '任务参数',
    last_trigger datetime comment '最后触发时间',
    deleted      char(1)    default '0' comment '状态(0:正常, 1:停用)',
    group_id     bigint(20) comment '数据分组',
    own_mode     char(1)    default 'w' comment '所有者权限(-:不可读不可写, r:可读, w:可读可写)',
    group_mode   char(1)    default 'r' comment '同分组权限(-:不可读不可写, r:可读, w:可读可写)',
    other_mode   char(1)    default '-' comment '其他分组权限(-:不可读不可写, r:可读, w:可读可写)',
    create_by    varchar(60) comment '创建者',
    create_time  datetime comment '创建时间',
    update_by    varchar(60) comment '更新者',
    update_time  datetime comment '更新时间',
    primary key (id)
) engine = innodb
  auto_increment = 1 comment = '任务表';

  -- ----------------------------
  -- 任务日志表
  -- ----------------------------
  drop table if exists job_log;
  create table job_log
  (
      id           bigint(20)  not null auto_increment comment '主键',
      job_id       varchar(60) not null comment '任务Id',
      type         char(1)    default '' comment '日志分类(S:系统, U:用户)',
      exec_time    datetime comment '执行时间',
      exec_code    char(1)    default '0' comment '执行结果(0:正常, 1:异常)',
      exec_content text comment '执行结果描述',
      create_by    varchar(60) comment '创建者',
      create_time  datetime comment '创建时间',
      update_by    varchar(60) comment '更新者',
      update_time  datetime comment '更新时间',
      primary key (id)
  ) engine = innodb
    auto_increment = 1 comment = '任务日志表';
ALTER TABLE job_log
    ADD INDEX `index_job_id` (`job_id`) USING BTREE;