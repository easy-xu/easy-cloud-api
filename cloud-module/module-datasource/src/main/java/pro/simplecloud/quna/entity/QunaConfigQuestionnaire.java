package pro.simplecloud.quna.entity;

import pro.simplecloud.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 测试问卷主表
 * </p>
 *
 * @author Generator
 * @since 2021-10-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class QunaConfigQuestionnaire extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 问卷标题
     */
    private String title;

    /**
     * 简要描述
     */
    private String shortDesc;

    /**
     * 问题个数
     */
    private Long questionNum;

    /**
     * 参与人数
     */
    private Long participantNum;

    /**
     * 喜欢个数
     */
    private Long likeNum;


}
