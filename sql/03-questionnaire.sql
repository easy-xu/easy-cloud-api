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
    deleted         char(1) default '0' comment '删除标志（0正常 1停用）',
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
    id               bigint(20) not null auto_increment comment '问题ID',
    questionnaire_id bigint(20) not null comment '问卷ID',
    title            varchar(500) comment '问题标题',
    order_num        bigint(20) comment '问题标题',
    deleted          char(1) default '0' comment '删除标志（0正常 1停用）',
    create_by        varchar(64) comment '创建者',
    create_time      datetime comment '创建时间',
    update_by        varchar(64) comment '更新者',
    update_time      datetime comment '更新时间',
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
    value       varchar(60) comment '选项值',
    deleted     char(1) default '0' comment '删除标志（0正常 1停用）',
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
    id               bigint(20) not null auto_increment comment '问卷回答ID',
    questionnaire_id bigint(20) not null comment '问卷ID',
    question_index   bigint(20) comment '当前问题编号',
    flow             bigint(20) comment '问卷回答流程（0初始化 1回答中 2回答完毕 3生成结果）',
    deleted          char(1) default '0' comment '删除标志（0正常 1停用）',
    create_by        varchar(64) comment '创建者',
    create_time      datetime comment '创建时间',
    update_by        varchar(64) comment '更新者',
    update_time      datetime comment '更新时间',
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
    deleted      char(1) default '0' comment '删除标志（0正常 1停用）',
    create_by    varchar(64) comment '创建者',
    create_time  datetime comment '创建时间',
    update_by    varchar(64) comment '更新者',
    update_time  datetime comment '更新时间',
    primary key (id)
) engine = innodb
  auto_increment = 1 comment = '测试问卷问题回答表';

-- 乐嘉性格色彩测试
INSERT INTO `simple-cloud`.`quna_config_questionnaire`(`title`, `question_num`)
VALUES ('乐嘉性格色彩测试', 30);

INSERT INTO `simple-cloud`.`quna_config_question`(`questionnaire_id`, `title`, `order_num`)
VALUES (1, ' 关于人生观，我的内心其实是：', '1');
INSERT INTO `simple-cloud`.`quna_config_question`(`questionnaire_id`, `title`, `order_num`)
VALUES (1, '如果爬山旅游，大多数状况下，在下山回来的路线我最可能：', '2');
INSERT INTO `simple-cloud`.`quna_config_question`(`questionnaire_id`, `title`, `order_num`)
VALUES (1, ' 说话时，我更看重：', '3');
INSERT INTO `simple-cloud`.`quna_config_question`(`questionnaire_id`, `title`, `order_num`)
VALUES (1, ' 在大多数时候，我的内心更想要：', '4');
INSERT INTO `simple-cloud`.`quna_config_question`(`questionnaire_id`, `title`, `order_num`)
VALUES (1, ' 我认为自己在情感上的基本特点是：', '5');
INSERT INTO `simple-cloud`.`quna_config_question`(`questionnaire_id`, `title`, `order_num`)
VALUES (1, ' 我认为自己除了工作外，在控制欲上面，我：', '6');
INSERT INTO `simple-cloud`.`quna_config_question`(`questionnaire_id`, `title`, `order_num`)
VALUES (1, ' 当与情人交往时，我最希望对方：', '7');
INSERT INTO `simple-cloud`.`quna_config_question`(`questionnaire_id`, `title`, `order_num`)
VALUES (1, ' 在人际交往时，我：', '8');
INSERT INTO `simple-cloud`.`quna_config_question`(`questionnaire_id`, `title`, `order_num`)
VALUES (1, '我做事情，经常：', '9');
INSERT INTO `simple-cloud`.`quna_config_question`(`questionnaire_id`, `title`, `order_num`)
VALUES (1, ' 通常我完成任务的方式是：', '10');
INSERT INTO `simple-cloud`.`quna_config_question`(`questionnaire_id`, `title`, `order_num`)
VALUES (1, ' 如果有人深深惹恼我时，我：', '11');
INSERT INTO `simple-cloud`.`quna_config_question`(`questionnaire_id`, `title`, `order_num`)
VALUES (1, ' 在人际关系中，我最在意的是：', '12');
INSERT INTO `simple-cloud`.`quna_config_question`(`questionnaire_id`, `title`, `order_num`)
VALUES (1, '在工作上，我表现出来更多的是：', '13');
INSERT INTO `simple-cloud`.`quna_config_question`(`questionnaire_id`, `title`, `order_num`)
VALUES (1, ' 我过往的老师最有可能对我的评价是：', '14');
INSERT INTO `simple-cloud`.`quna_config_question`(`questionnaire_id`, `title`, `order_num`)
VALUES (1, ' 朋友对我的评价最有可能的是：', '15');
INSERT INTO `simple-cloud`.`quna_config_question`(`questionnaire_id`, `title`, `order_num`)
VALUES (1, ' 在帮助他人的问题上，我内心的想法是：', '16');
INSERT INTO `simple-cloud`.`quna_config_question`(`questionnaire_id`, `title`, `order_num`)
VALUES (1, ' 面对他人对自己的赞美，我内心：', '17');
INSERT INTO `simple-cloud`.`quna_config_question`(`questionnaire_id`, `title`, `order_num`)
VALUES (1, ' 面对生活，我更像：', '18');
INSERT INTO `simple-cloud`.`quna_config_question`(`questionnaire_id`, `title`, `order_num`)
VALUES (1, ' 对于规则，我内心的态度是：', '19');
INSERT INTO `simple-cloud`.`quna_config_question`(`questionnaire_id`, `title`, `order_num`)
VALUES (1, ' 我认为自己在行为上的基本特点是：', '20');
INSERT INTO `simple-cloud`.`quna_config_question`(`questionnaire_id`, `title`, `order_num`)
VALUES (1, ' 当我做错事时，我倾向于：', '21');
INSERT INTO `simple-cloud`.`quna_config_question`(`questionnaire_id`, `title`, `order_num`)
VALUES (1, ' 当结束一段刻骨铭心的感情时，我会：', '22');
INSERT INTO `simple-cloud`.`quna_config_question`(`questionnaire_id`, `title`, `order_num`)
VALUES (1, ' 面对他人的倾诉，我回顾自己大多时候本能上倾向于：', '23');
INSERT INTO `simple-cloud`.`quna_config_question`(`questionnaire_id`, `title`, `order_num`)
VALUES (1, ' 我在以下哪个群体中交流较感满足？', '24');
INSERT INTO `simple-cloud`.`quna_config_question`(`questionnaire_id`, `title`, `order_num`)
VALUES (1, ' 在内心的真实想法里，我觉得工作：', '25');
INSERT INTO `simple-cloud`.`quna_config_question`(`questionnaire_id`, `title`, `order_num`)
VALUES (1, ' 如果我是领导，我内心更希望在部属心目中，我是：', '26');
INSERT INTO `simple-cloud`.`quna_config_question`(`questionnaire_id`, `title`, `order_num`)
VALUES (1, ' 我对认同的需求是：', '27');
INSERT INTO `simple-cloud`.`quna_config_question`(`questionnaire_id`, `title`, `order_num`)
VALUES (1, ' 当我还是个孩子的时候，我：', '28');
INSERT INTO `simple-cloud`.`quna_config_question`(`questionnaire_id`, `title`, `order_num`)
VALUES (1, ' 如果我是父母，我也许是：', '29');
INSERT INTO `simple-cloud`.`quna_config_question`(`questionnaire_id`, `title`, `order_num`)
VALUES (1, '以下有四组格言，哪组里整体上最符合我的感觉?', '30');


INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (1, '希望能有各种各样的人生体验，所以想法极其多样化。', 'A');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (1, '在合理的基础上，谨慎确定目标，一旦确定会坚定不移地去做。', 'B');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (1, '更加在乎取得一切有可能的成就。', 'C');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (1, '毫不喜欢风险，喜欢享受稳定或现状。', 'D');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (2, '好玩有趣，所以宁愿新路线回巢。', 'A');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (2, '安全稳妥，所以宁愿原路线返回。', 'B');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (2, '挑战困难，所以宁愿新路线回巢。', 'C');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (2, '方便省心，所以宁愿原路线返回。', 'D');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (3, '感觉效果。有时可能会略显得夸张。', 'A');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (3, '描述精确。有时可能略过冗长。', 'B');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (3, '达成结果。有时可能过于直接让别人不高兴。', 'C');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (3, '人际感受。有时可能会不愿讲真话。', 'D');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (4, '刺激。经常冒出新点子，想做就做，喜欢与众不同。', 'A');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (4, '安全。头脑冷静，不易冲动。', 'B');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (4, '挑战。生命中竞赛随处可见，有强烈的“赢”的欲望。', 'C');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (4, '稳定。满足自己所拥有的，很少羡慕别人。', 'D');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (5, '情绪多变，经常波动。', 'A');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (5, '外表自我抑制强，但内心感情起伏大，一旦挫伤难以平复。', 'B');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (5, '感情不拖泥带水，只是一旦不稳定，容易发怒。', 'C');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (5, '天性情绪四平八稳。', 'D');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (6, '没有控制欲，只有感染带动他人的欲望，但自控能力不算强。', 'A');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (6, '用规则来保持我对自己的控制和对他人的要求。', 'B');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (6, '内心是有控制欲和希望别人服从我的。', 'C');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (6, '没兴趣影响别人，也不愿别人来控制我。', 'D');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (7, '经常赞美我，让我享受开心', 'A');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (7, '可随时默契到我内心所想，对我的需求极其敏丄感。', 'B');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (7, '得到对方的认可，我是正确的并且我对其是有价值的。', 'C');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (7, '尊重并且相处静谧的。', 'D');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (8, '本质上还是认为与人交往比长时间独处是有乐趣的。', 'A');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (8, '非常审慎缓慢地进入，常会被人认为容易有距离感。', 'B');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (8, '希望在人际关系中占据主导地位。', 'C');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (8, '顺其自然，不温不火，相对被动。', 'D');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (9, '缺少长性，不喜欢长期做相同无变化的事情。', 'A');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (9, '缺少果断，期待最好的结果但总能先看到事情的不利面。', 'B');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (9, '缺少耐性，有时行事过于草率。', 'C');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (9, '缺少紧迫，行动迟缓，难下决心。', 'D');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (10, '常赶在最后期限前完成，是临时抱佛脚的高手。', 'A');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (10, '自己有严格规定的程序，精确地做，不要麻烦别人。', 'B');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (10, '先做，快速做。', 'C');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (10, '使用传统的方法按部就班，需要时从他人处得到帮忙。', 'D');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (11, '内心感到受伤，认为没有原谅的可能，可最终很多时候还是会原谅对方。', 'A');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (11, '深深感到愤怒，如此之深怎可忘记？我会牢记，同时未来完全避开那个家伙。', 'B');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (11, '会火冒三丈，并且内心期望有机会狠狠地回应。', 'C');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (11, '避免摊牌，因为还不到那个地步或者自己再去找新朋友。', 'D');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (12, '得到他人的赞美和欢迎。', 'A');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (12, '得到他人的理解和欣赏。', 'B');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (12, '得到他人的感激和尊敬。', 'C');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (12, '得到他人的尊重和接纳。', 'D');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (13, '充满热忱，有很多想法且很有灵性。', 'A');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (13, '心思细腻，完美精确，而且为人可靠。', 'B');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (13, '坚强而直截了当，而且有推动力。', 'C');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (13, '有耐心，适应性强而且善于协调。', 'D');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (14, '情绪起伏大，善于表达和抒发情感。', 'A');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (14, '严格保护自己的私密，有时会显得孤独或是不合群。', 'B');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (14, '动作敏捷又独立，并且喜欢自己做事情。', 'C');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (14, '看起来安稳轻松，反应度偏低，比较温和。', 'D');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (15, '喜欢对朋友述说，也有感染别人的力量。', 'A');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (15, '能够提出很多周全的问题，而且需要许多精细的解说。', 'B');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (15, '愿意直言想法，有时会直率而犀利地谈论不喜欢的人', 'C');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (15, '通常与他人一起是倾听者。', 'D');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (16, '别人来找我，不太会拒绝，会尽力帮他。', 'A');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (16, '值得帮助的人应该帮助。', 'B');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (16, '很少承诺要帮，但我若承诺必兑现。', 'C');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (16, '虽无英雄打虎胆，常有自告奋勇心。', 'D');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (17, '没有也无所谓，特别欣喜那也不至于。', 'A');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (17, '我不需无关痛痒的赞美，宁可对方欣赏我的能力。', 'B');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (17, '思考对方的真实性或立即回避众人的关注。', 'C');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (17, '赞美多多益善，总是令人愉悦的。', 'D');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (18, '随和派－外面的世界我无关，我觉得自己这样还不错。', 'A');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (18, '行动派－我不进步，别人就会进步，所以我必须不停地前进。', 'B');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (18, '分析派－在问题未发生之前，就该想好所有的可能。', 'C');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (18, '无忧派－每天的生活开心快乐最重要。', 'D');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (19, '不愿违反规则，但可能因为松散而无法达到规则的要求。', 'A');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (19, '打破规则，希望由自己来制定规则而不是遵守规则。', 'B');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (19, '严格遵守规则，并且竭尽全力做到规则内的最好。', 'C');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (19, '不喜被规则束缚，不按规则出牌会觉得新鲜有趣。', 'D');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (20, '慢条斯理，办事按部就班，能与周围的人协调一致。', 'A');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (20, '目标明确，集中精力为实现目标而努力，善于抓住核心要点。', 'B');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (20, '慎重小心，为做好预防及善后，会不惜一切而尽心操劳。', 'C');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (20, '丰富跃动，不喜欢制度和约束，倾向于快速反应。', 'D');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (21, '害怕但表面不露声色丄。', 'A');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (21, '不承认而且辩驳，但内心其实已经明白。', 'B');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (21, '愧疚和痛苦，容易停留在自我压抑中。', 'C');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (21, '难为情，希望逃避别人的批评。', 'D');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (22, '很难受，可日子总要过，时间会冲淡一切的。', 'A');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (22, '虽然觉得受伤，但一旦下定决心，就会努力把过去的影子摔掉。', 'B');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (22, '深陷在悲伤的情绪中，在相当长的时期里难以自拔，也不愿再接受新的人。', 'C');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (22, '痛不欲生，需要找朋友倾诉或者找到渠道发泄，寻求化解之道。', 'D');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (23, '能够认同并理解对方当时的感受。', 'A');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (23, '快速做出一些定论或判断。', 'B');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (23, '给予一些分析或推理，帮助对方理顺思路。', 'C');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (23, '可能会随着他的情绪起伏而起伏，也会发表一些评论或意见。', 'D');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (24, '舒服轻松的氛围中，心平气和地最终达成一致结论。', 'A');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (24, '彼此展开充分激烈的辩论并有收获。', 'B');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (24, '有意义地详细讨论事情的好坏和影响。', 'C');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (24, '很开心并且随意无拘束地闲谈。', 'D');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (25, '不必有太大压力，可以让我做我熟悉的工作就很不错。', 'A');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (25, '应该以最快的速度完成，且争取去完成更多的任务。', 'B');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (25, '要么不做，要做就做到最好。', 'C');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (25, '如果能将好玩融合其中那就太棒了，不过如果不喜欢的工作实在没劲。', 'D');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (26, '可以亲近的和善于为他们着想的。', 'A');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (26, '有很强的能力和富有领导力的。', 'B');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (26, '公平公正且足以信赖的。', 'C');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (26, '被他们喜欢并且觉得富有感召力的。', 'D');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (27, '无论别人是否认同，生活都是要继续的。', 'A');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (27, '精英群体的认同最重要。', 'B');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (27, '只要我在乎的那些人认同我就足够了。', 'C');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (27, '所见之人无论贵贱都对我认同那有多好。', 'D');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (28, '不太会积极尝试新事物，通常比较喜欢旧有的和熟悉的。', 'A');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (28, '是孩子王，大家经常听我的决定。', 'B');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (28, '害羞见生人，有意识地回避。', 'C');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (28, '调皮可爱，乐观而又热心。', 'D');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (29, '容易说服或者宽容的。', 'A');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (29, '比较严厉', 'B');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (29, '坚持自己的想法和比较挑剔的。', 'C');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (29, '积极参与到子女中一起玩，被小朋友们们热烈欢迎的。', 'D');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (30, '最深刻的真理是最简单和最平凡的。要在人世间取得成功必须大智若愚。好脾气是一个人在社交中所能穿着的最佳服饰。知足是人生在世最大的幸福。', 'A');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (30, '走自己的路，让人家去说吧。虽然世界充满了苦难，但是苦难总是能战胜的。有所成就是人生唯一的真正的乐趣。对我而言解决一个问题和享受一个假期一样好。', 'B');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (30, '一个不注意小事情的人，永远不会成功大事业。理性是灵魂中最高贵的因素。切忌浮夸铺张。与其说得过分，不如说得不全。谨慎比大胆要有力量得多。', 'C');
INSERT INTO `simple-cloud`.`quna_config_option`(`question_id`, `text`, `value`)
VALUES (30, '幸福在于对生命的喜悦和激情。任何时候都要最真实地对待你自己，这比什么都重要。使生活变成幻想，再把幻想化为现实。幸福不在于拥有金钱，而在于获得成就时的喜悦以及产生创造力的激情。', 'D');
