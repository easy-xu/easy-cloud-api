package pro.simplecloud.quna.controller;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/get/{questionId}")
    public ApiResponse getQuestion(@PathVariable String questionId) {
        if (!StringUtils.hasLength(questionId)) {
            return HttpResponse.reject(Messages.ID_EMPTY);
        }
        QuestionDto questionDto = questionService.getDetail(questionId);
        return HttpResponse.ok(questionDto);
    }
}
