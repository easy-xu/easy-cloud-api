package cloud.easy.quna.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import cloud.easy.quna.dto.ResultDto;
import cloud.easy.quna.entity.QunaAnswerQuestionnaire;
import cloud.easy.quna.entity.QunaAnswerResult;

import java.util.List;

/**
 * <p>
 * 测试问卷答案结果表 Mapper 接口
 * </p>
 *
 * @author Generator
 * @since 2021-09-14
 */
public interface QunaMapperCust {

    /**
     * 查询未回答的收个问题
     *
     * @param answerId        回答id
     * @param questionnaireId 问卷id
     * @return Long
     */
    @Select("select order_num from quna_config_question cq where cq.questionnaire_id = #{questionnaireId} and not exists ( select 1 from quna_answer_question aq where cq.id= aq.question_id and aq.answer_id = #{answerId} ) ORDER BY order_num LIMIT 1")
    Integer firstNotAnswerQuestionIndex(@Param("questionnaireId") Long questionnaireId, @Param("answerId") Long answerId);

    /**
     * 根据回答id合计分数
     *
     * @param answerId 回答id
     * @return List<QunaAnswerResult>
     */
    @Select("SELECT aq.answer_id answer_id, rs.result_id result_id, sum( rs.score ) score  FROM quna_answer_question aq, quna_config_result_score rs WHERE aq.answer_id = #{answerId} AND aq.option_id = rs.option_id GROUP BY rs.result_id")
    List<QunaAnswerResult> sunScore(Long answerId);

    /**
     * 根据回答id查询计算结果
     *
     * @param answerId 回答id
     * @return List<ResultDto>
     */
    @Select("SELECT r.*, cr.title, cr.short_desc FROM quna_answer_result r LEFT JOIN quna_config_result cr ON r.result_id = cr.id WHERE r.answer_id = #{answerId} ORDER BY r.result_id ASC")
    List<ResultDto> listByAnswerId(Long answerId);

    /**
     * 查询当前用户最近一次回答
     * @param questionnaireId 问卷id
     * @param userNo 用户编号
     * @return QunaAnswerQuestionnaire
     */
    @Select("SELECT * FROM quna_answer_questionnaire WHERE create_by = #{userNo} AND questionnaire_id = #{questionnaireId} ORDER BY update_time DESC LIMIT 1")
    QunaAnswerQuestionnaire latestAnswer(@Param("questionnaireId") Long questionnaireId, @Param("userNo") String userNo);
}
