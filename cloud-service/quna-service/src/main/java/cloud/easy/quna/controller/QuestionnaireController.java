package cloud.easy.quna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cloud.easy.constant.Messages;
import cloud.easy.base.controller.BaseController;
import cloud.easy.base.dto.PageQueryDto;
import cloud.easy.entity.ApiResponse;
import cloud.easy.entity.HttpResponse;
import cloud.easy.quna.dto.QuestionnaireDto;
import cloud.easy.quna.entity.QunaConfigQuestionnaire;
import cloud.easy.quna.service.IQunaConfigQuestionnaireService;
import cloud.easy.quna.service.QuestionnaireService;

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

    @Override
    @PostMapping("/query")
    public ApiResponse queryEntity(@RequestBody QunaConfigQuestionnaire entity) {
        return super.queryEntity(entity);
    }

    @Override
    @PostMapping("/save")
    public ApiResponse saveEntity(@RequestBody QunaConfigQuestionnaire entity) {
        return super.saveEntity(entity);
    }

    @Override
    @PostMapping("/page-list")
    public ApiResponse pageList(@RequestBody PageQueryDto<QunaConfigQuestionnaire> pageQueryDto) {
        return super.pageList(pageQueryDto);
    }

    @PostMapping("/import")
    public ApiResponse importExcel(@RequestBody QuestionnaireDto questionnaireDto) {
        if (questionnaireDto == null) {
            return HttpResponse.reject(Messages.REQUEST_EMPTY);
        }
        Long fileId = questionnaireDto.getFileId();
        if (fileId == null) {
            return HttpResponse.reject(Messages.ID_EMPTY);
        }
        QunaConfigQuestionnaire questionnaire = questionnaireService.importExcel(fileId);
        return HttpResponse.ok(questionnaire);
    }

    @Override
    @PostMapping("/delete")
    public ApiResponse deleteEntity(@RequestBody QunaConfigQuestionnaire questionnaireDto) {
        if (questionnaireDto == null) {
            return HttpResponse.reject(Messages.REQUEST_EMPTY);
        }
        Long id = questionnaireDto.getId();
        if (id == null) {
            HttpResponse.reject(Messages.ID_EMPTY);
        }
        questionnaireService.deleteConfig(id);
        return HttpResponse.ok();
    }
}
