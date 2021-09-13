package pro.simplecloud.quna.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping("/get")
    public ApiResponse getQuestionnaire(@RequestBody QuestionnaireDto questionnaireDto) {
        if (questionnaireDto == null) {
            return HttpResponse.reject(Messages.REQUEST_EMPTY);
        }
        Long questionnaireId = questionnaireDto.getId();
        if (questionnaireId == null) {
            return HttpResponse.reject(Messages.ID_EMPTY);
        }
        questionnaireDto = questionnaireService.getDetail(questionnaireId);
        return HttpResponse.ok(questionnaireDto);
    }

}
