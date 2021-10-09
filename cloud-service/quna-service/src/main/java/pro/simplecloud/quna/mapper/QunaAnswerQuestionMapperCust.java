package pro.simplecloud.quna.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 测试问卷答案表 Mapper 接口
 * </p>
 *
 * @author Generator
 * @since 2021-09-14
 */
public interface QunaAnswerQuestionMapperCust {

    /**
     * 查询未回答的收个问题
     *
     * @param answerId        回答id
     * @param questionnaireId 问卷id
     * @return Long
     */
    @Select("select order_num from quna_config_question cq where cq.questionnaire_id = #{questionnaireId} and not exists ( select 1 from quna_answer_question aq where cq.id= aq.question_id and aq.answer_id = #{answerId} ) ORDER BY order_num LIMIT 1")
    Integer firstNotAnswerQuestionIndex(@Param("questionnaireId") Long questionnaireId, @Param("answerId") Long answerId);

}
