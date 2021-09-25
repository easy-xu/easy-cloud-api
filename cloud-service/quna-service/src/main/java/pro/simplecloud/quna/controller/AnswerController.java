package pro.simplecloud.quna.controller;

import org.springframework.web.bind.annotation.*;
import pro.simplecloud.constant.Messages;
import pro.simplecloud.entity.ApiResponse;
import pro.simplecloud.entity.HttpResponse;
import pro.simplecloud.quna.entity.QunaAnswerQuestion;
import pro.simplecloud.quna.entity.QunaAnswerQuestionnaire;
import pro.simplecloud.quna.service.AnswerService;

import javax.annotation.Resource;

/**
 * Title: AnswerController
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
    public ApiResponse initQuestionnaire(@RequestBody QunaAnswerQuestionnaire answerQuestionnaire) {
        if (answerQuestionnaire == null) {
            return HttpResponse.reject(Messages.REQUEST_EMPTY);
        }
        Long questionnaireId = answerQuestionnaire.getQuestionnaireId();
        if (questionnaireId == null) {
            return HttpResponse.reject(Messages.ID_EMPTY);
        }
        answerQuestionnaire = answerService.init(questionnaireId);
        return HttpResponse.ok(answerQuestionnaire);
    }


    @PostMapping("/status")
    public ApiResponse status(@RequestBody QunaAnswerQuestionnaire answerQuestionnaire) {
        if (answerQuestionnaire == null) {
            return HttpResponse.reject(Messages.REQUEST_EMPTY);
        }
        Long answerId = answerQuestionnaire.getId();
        if (answerId == null) {
            return HttpResponse.reject(Messages.ID_EMPTY);
        }
        answerQuestionnaire = answerService.status(answerId);
        return HttpResponse.ok(answerQuestionnaire);
    }

    @PostMapping("/query")
    public ApiResponse queryAnswer(@RequestBody QunaAnswerQuestionnaire answerQuestionnaire) {
        if (answerQuestionnaire == null) {
            return HttpResponse.reject(Messages.REQUEST_EMPTY);
        }
        Long answerId = answerQuestionnaire.getId();
        Long questionnaireId = answerQuestionnaire.getQuestionnaireId();
        if (answerId != null) {
            answerQuestionnaire = answerService.getDetail(answerId);
            return HttpResponse.ok(answerQuestionnaire);

        }
        if (questionnaireId != null) {
            answerQuestionnaire = answerService.getDetailByQuestionnaireId(questionnaireId);
            return HttpResponse.ok(answerQuestionnaire);
        }
        return HttpResponse.reject(Messages.ID_EMPTY);
    }


    @PostMapping("/question/save")
    public ApiResponse saveAnswerQuestion(@RequestBody QunaAnswerQuestion answerQuestion) {
        if (answerQuestion == null) {
            return HttpResponse.reject(Messages.REQUEST_EMPTY);
        }
        answerQuestion = answerService.saveAnswerQuestion(answerQuestion);
        return HttpResponse.ok(answerQuestion);
    }

    @PostMapping("/question/query")
    public ApiResponse queryAnswerQuestion(@RequestBody QunaAnswerQuestion answerQuestion) {
        if (answerQuestion == null) {
            return HttpResponse.reject(Messages.REQUEST_EMPTY);
        }
        Long answerId = answerQuestion.getAnswerId();
        Long questionId = answerQuestion.getQuestionId();
        if (answerId == null || questionId == null) {
            return HttpResponse.reject(Messages.ID_EMPTY);
        }
        answerQuestion = answerService.getAnswerQuestion(answerId, questionId);
        return HttpResponse.ok(answerQuestion);
    }
}
