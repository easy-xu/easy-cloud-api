package pro.simplecloud.quna.controller;

import org.springframework.web.bind.annotation.*;
import pro.simplecloud.constant.Messages;
import pro.simplecloud.entity.ApiResponse;
import pro.simplecloud.entity.HttpResponse;
import pro.simplecloud.quna.dto.AnswerDto;
import pro.simplecloud.quna.dto.AnswerQuestionDto;
import pro.simplecloud.quna.dto.ResultDto;
import pro.simplecloud.quna.service.AnswerService;

import javax.annotation.Resource;

/**
 * Title: ResultController
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@RestController
@RequestMapping("/api/result")
public class ResultController {

    @Resource
    private AnswerService answerService;

}
