package pro.simplecloud.quna.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.simplecloud.constant.Messages;
import pro.simplecloud.entity.ApiResponse;
import pro.simplecloud.entity.HttpResponse;
import pro.simplecloud.quna.dto.QuestionnaireDto;
import pro.simplecloud.quna.service.QuestionnaireService;

import javax.annotation.Resource;

/**
 * Title: QuestionnaireController
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@RestController
@RequestMapping("/api/questionnaire")
public class QuestionnaireController {

    @Resource
    private QuestionnaireService questionnaireService;

    @GetMapping("/get/{questionnaireId}")
    public ApiResponse getQuestionnaire(@PathVariable Long questionnaireId) {
        if (questionnaireId == null) {
            return HttpResponse.reject(Messages.ID_EMPTY);
        }
        QuestionnaireDto questionnaireDto = questionnaireService.getDetail(questionnaireId);
        return HttpResponse.ok(questionnaireDto);
    }


    @GetMapping("/init/{questionnaireId}")
    public ApiResponse initQuestionnaire(@PathVariable Long questionnaireId) {
        if (questionnaireId == null) {
            return HttpResponse.reject(Messages.ID_EMPTY);
        }
        QuestionnaireDto questionnaireDto = questionnaireService.init(questionnaireId);
        return HttpResponse.ok(questionnaireDto);
    }
}
