package pro.simplecloud.quna.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.simplecloud.constant.Messages;
import pro.simplecloud.entity.ApiResponse;
import pro.simplecloud.entity.HttpResponse;
import pro.simplecloud.quna.dto.QuestionDto;
import pro.simplecloud.quna.service.QuestionService;

import javax.annotation.Resource;

/**
 * Title: QuestionController
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@RestController
@RequestMapping("/api/question")
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
    public ApiResponse queryQuestionByIndex(@RequestBody AnswerDto answerDto) {
        if (answerDto == null) {
            return HttpResponse.reject(Messages.REQUEST_EMPTY);
        }
        Long questionIndex = answerDto.getQuestionIndex();
        Long questionnaireId = answerDto.getQuestionnaireId();
        if (questionIndex == null || questionnaireId == null) {
            return HttpResponse.reject(Messages.ID_EMPTY);
        }
        QuestionDto questionDto = questionService.getDetailByIndex(questionnaireId, questionIndex);
        return HttpResponse.ok(questionDto);
    }
}
