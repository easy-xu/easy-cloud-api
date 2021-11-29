package cloud.easy.questionnaire.controller;

import cloud.easy.constant.Messages;
import cloud.easy.entity.ApiResponse;
import cloud.easy.entity.HttpResponse;
import cloud.easy.questionnaire.dto.QuestionDto;
import cloud.easy.quna.entity.QunaAnswerQuestionnaire;
import cloud.easy.questionnaire.service.QuestionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Title: QuestionController
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@RestController
@RequestMapping("/api/questionnaire/question")
public class QuestionController {

    @Resource
    private QuestionService questionService;

    @PostMapping("/query")
    public ApiResponse queryQuestionById(@RequestBody QuestionDto questionDto) {
        if (questionDto == null) {
            return HttpResponse.reject(Messages.REQUEST_EMPTY);
        }
        Long questionId = questionDto.getId();
        if (questionId == null) {
            return HttpResponse.reject(Messages.ID_EMPTY);
        }
        questionDto = questionService.getDetail(questionId);
        return HttpResponse.ok(questionDto);
    }

    @PostMapping("/index")
    public ApiResponse queryQuestionByIndex(@RequestBody QunaAnswerQuestionnaire answerQuestionnaire) {
        if (answerQuestionnaire == null) {
            return HttpResponse.reject(Messages.REQUEST_EMPTY);
        }
        Integer questionIndex = answerQuestionnaire.getQuestionIndex();
        Long questionnaireId = answerQuestionnaire.getQuestionnaireId();
        if (questionIndex == null || questionnaireId == null) {
            return HttpResponse.reject(Messages.ID_EMPTY);
        }
        QuestionDto questionDto = questionService.getDetailByIndex(questionnaireId, questionIndex);
        return HttpResponse.ok(questionDto);
    }
}
