package pro.simplecloud.quna.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import pro.simplecloud.quna.dto.ResultDto;
import pro.simplecloud.quna.entity.QunaAnswerResult;

import java.util.List;

/**
 * <p>
 * 测试问卷答案结果表 Mapper 接口
 * </p>
 *
 * @author Generator
 * @since 2021-09-14
 */
public interface QunaAnswerResultMapperCust {

    /**
     * 根据回答id合计分数
     * @param answerId 回答id
     * @return List<QunaAnswerResult>
     */
    @Select("SELECT aq.answer_id answer_id, rs.result_id result_id, sum( rs.score ) score  FROM quna_answer_question aq, quna_config_result_score rs WHERE aq.answer_id = #{answerId} AND aq.option_id = rs.option_id GROUP BY rs.result_id")
    List<QunaAnswerResult> sunScore(Long answerId);

    /**
     * 根据回答id查询计算结果
     * @param answerId 回答id
     * @return List<ResultDto>
     */
    @Select("SELECT r.*, cr.title, cr.short_desc FROM quna_answer_result r LEFT JOIN quna_config_result cr ON r.result_id = cr.id WHERE r.answer_id = #{answerId} ORDER BY r.score DESC")
    List<ResultDto> listByAnswerId(Long answerId);
}
