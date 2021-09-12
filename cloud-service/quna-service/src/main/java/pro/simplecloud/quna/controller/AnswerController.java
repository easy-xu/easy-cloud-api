package pro.simplecloud.quna.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.simplecloud.constant.Messages;
import pro.simplecloud.entity.ApiResponse;
import pro.simplecloud.entity.HttpResponse;
import pro.simplecloud.quna.dto.AnswerDto;
import pro.simplecloud.quna.dto.QuestionnaireDto;
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

    @GetMapping("/init/{questionnaireId}")
    public ApiResponse initQuestionnaire(@PathVariable Long questionnaireId) {
        if (questionnaireId == null) {
            return HttpResponse.reject(Messages.ID_EMPTY);
        }
        AnswerDto answerDto = answerService.init(questionnaireId);
        return HttpResponse.ok(answerDto);
    }

}
