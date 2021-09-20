package pro.simplecloud.quna.controller;

import org.springframework.web.bind.annotation.*;
import pro.simplecloud.constant.Messages;
import pro.simplecloud.entity.ApiResponse;
import pro.simplecloud.entity.HttpResponse;
import pro.simplecloud.quna.dto.AnswerDto;
import pro.simplecloud.quna.dto.AnswerQuestionDto;
import pro.simplecloud.quna.dto.ResultDto;
import pro.simplecloud.quna.service.AnswerService;
import pro.simplecloud.quna.service.ResultService;

import javax.annotation.Resource;
import java.util.List;

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
    private ResultService resultService;

    @PostMapping("/list")
    public ApiResponse list(@RequestBody ResultDto resultDto){
        if (resultDto == null) {
            return HttpResponse.reject(Messages.REQUEST_EMPTY);
        }
        Long answerId = resultDto.getAnswerId();
        if (answerId == null) {
            return HttpResponse.reject(Messages.ID_EMPTY);
        }
        List<ResultDto> resultDtos = resultService.listByAnswerId(answerId);
        return HttpResponse.ok(resultDtos);
    }

}
