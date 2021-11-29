package cloud.easy.questionnaire.controller;

import cloud.easy.constant.Messages;
import cloud.easy.entity.ApiResponse;
import cloud.easy.entity.HttpResponse;
import cloud.easy.quna.entity.QunaAnswerQuestion;
import cloud.easy.quna.entity.QunaAnswerQuestionnaire;
import cloud.easy.questionnaire.service.AnswerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Title: AnswerController
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@RestController
@RequestMapping("/api/questionnaire/answer")
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
        Long answerId = answerQuestion.getAnswerId();
        Long questionId = answerQuestion.getQuestionId();
        if (answerId == null || questionId == null) {
            return HttpResponse.reject(Messages.ID_EMPTY);
        }
        answerService.saveAnswerQuestion(answerQuestion);
        return HttpResponse.ok();
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
