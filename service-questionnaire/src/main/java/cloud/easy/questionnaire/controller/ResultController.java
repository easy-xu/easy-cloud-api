package cloud.easy.questionnaire.controller;

import cloud.easy.constant.Messages;
import cloud.easy.entity.ApiResponse;
import cloud.easy.entity.HttpResponse;
import cloud.easy.questionnaire.dto.ResultDto;
import cloud.easy.questionnaire.service.ResultService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/api/questionnaire/result")
public class ResultController {

    @Resource
    private ResultService resultService;

    @PostMapping("/list")
    public ApiResponse list(@RequestBody ResultDto resultDto) {
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
