package pro.simplecloud.quna.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.simplecloud.constant.Messages;
import pro.simplecloud.entity.ApiResponse;
import pro.simplecloud.entity.HttpResponse;
import pro.simplecloud.quna.dto.PageDto;
import pro.simplecloud.quna.dto.QuestionnaireDto;
import pro.simplecloud.quna.service.QuestionnaireService;

import javax.annotation.Resource;
import java.util.List;

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

    @PostMapping("/query")
    public ApiResponse queryQuestionnaire(@RequestBody QuestionnaireDto questionnaireDto) {
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

    @PostMapping("/list")
    public ApiResponse listQuestionnaire(@RequestBody PageDto<QuestionnaireDto> pageDto) {
        if (pageDto == null) {
            return HttpResponse.reject(Messages.REQUEST_EMPTY);
        }
        pageDto = questionnaireService.pageList(pageDto);
        return HttpResponse.ok(pageDto);
    }

}
