package pro.simplecloud.quna.dto;

import lombok.Data;
import pro.simplecloud.entity.ApiRequest;

import java.util.List;

/**
 * Title: QuestionnaireDto
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@Data
public class QuestionnaireDto implements ApiRequest {

    private Long id;

    private List<QuestionDto> questions;

    private QuestionDto question;

    private AnswerDto answer;

    /**
     * 问卷标题
     */
    private String title;

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
