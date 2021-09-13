package pro.simplecloud.quna.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.simplecloud.constant.Messages;
import pro.simplecloud.entity.ApiResponse;
import pro.simplecloud.entity.HttpResponse;
import pro.simplecloud.quna.dto.AnswerDto;
import pro.simplecloud.quna.dto.AnswerQuestionDto;
import pro.simplecloud.quna.service.AnswerService;

import javax.annotation.Resource;

/**
 * Title: QuestionnaireController
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@RestController
@RequestMapping("/api/answer")
public class AnswerController {

    @Resource
    private AnswerService answerService;

    @PostMapping("/init")
    public ApiResponse initQuestionnaire(@RequestBody AnswerDto answerDto) {
        if (answerDto == null) {
            return HttpResponse.reject(Messages.REQUEST_EMPTY);
        }
        Long questionnaireId = answerDto.getQuestionnaireId();
        if (questionnaireId == null) {
            return HttpResponse.reject(Messages.ID_EMPTY);
        }
        answerDto = answerService.init(questionnaireId);
        return HttpResponse.ok(answerDto);
    }

    @PostMapping("/get")
    public ApiResponse getAnswer(@RequestBody AnswerDto answerDto) {
        if (answerDto == null) {
            return HttpResponse.reject(Messages.REQUEST_EMPTY);
        }
        Long answerId = answerDto.getId();
        if (answerId == null) {
            return HttpResponse.reject(Messages.ID_EMPTY);
        }
        answerDto = answerService.getDetail(answerId);
        return HttpResponse.ok(answerDto);
    }

    @PostMapping("/question/save")
    public ApiResponse saveAnswerQuestion(@RequestBody AnswerQuestionDto answerQuestionDto) {
        if (answerQuestionDto == null) {
            return HttpResponse.reject(Messages.REQUEST_EMPTY);
        }
        answerQuestionDto = answerService.saveAnswerQuestion(answerQuestionDto);
        return HttpResponse.ok(answerQuestionDto);
    }

    @PostMapping("/question/get")
    public ApiResponse getAnswerQuestion(@RequestBody AnswerQuestionDto answerQuestionDto) {
        if (answerQuestionDto == null) {
            return HttpResponse.reject(Messages.REQUEST_EMPTY);
        }
        Long answerId = answerQuestionDto.getAnswerId();
        Long questionId = answerQuestionDto.getQuestionId();
        if (answerId == null || questionId == null) {
            return HttpResponse.reject(Messages.ID_EMPTY);
        }
        answerQuestionDto = answerService.getAnswerQuestion(answerId, questionId);
        return HttpResponse.ok(answerQuestionDto);
    }
}
