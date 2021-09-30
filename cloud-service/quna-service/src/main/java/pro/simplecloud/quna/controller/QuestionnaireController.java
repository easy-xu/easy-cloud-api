package pro.simplecloud.quna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.simplecloud.constant.Messages;
import pro.simplecloud.controller.BaseController;
import pro.simplecloud.entity.ApiResponse;
import pro.simplecloud.entity.HttpResponse;
import pro.simplecloud.quna.dto.QuestionnaireDto;
import pro.simplecloud.quna.entity.QunaAnswerQuestionnaire;
import pro.simplecloud.quna.entity.QunaConfigQuestionnaire;
import pro.simplecloud.quna.service.IQunaConfigQuestionnaireService;
import pro.simplecloud.quna.service.QuestionService;
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
public class QuestionnaireController extends BaseController<QunaConfigQuestionnaire, IQunaConfigQuestionnaireService> {

    @Resource
    private QuestionnaireService questionnaireService;

    @Autowired
    public QuestionnaireController(IQunaConfigQuestionnaireService service) {
        super(service);
    }


    @PostMapping("/import")
    public ApiResponse importExcel(@RequestBody QuestionnaireDto questionnaireDto){
        if (questionnaireDto == null) {
            HttpResponse.reject(Messages.REQUEST_EMPTY);
        }
        Long fileId = questionnaireDto.getFileId();
        if (fileId == null) {
            HttpResponse.reject(Messages.ID_EMPTY);
        }
        QunaConfigQuestionnaire questionnaire =  questionnaireService.importExcel(fileId);
        return HttpResponse.ok(questionnaire);
    }

    @PostMapping("/delete")
    public ApiResponse deleteConfig(@RequestBody QuestionnaireDto questionnaireDto){
        if (questionnaireDto == null) {
            HttpResponse.reject(Messages.REQUEST_EMPTY);
        }
        Long id = questionnaireDto.getId();
        if (id == null) {
            HttpResponse.reject(Messages.ID_EMPTY);
        }
        questionnaireService.deleteConfig(id);
        return HttpResponse.ok();
    }
}
