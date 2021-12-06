package cloud.easy.message.controller;

import cloud.easy.device.ApiHeaderHelper;
import cloud.easy.entity.ApiHeader;
import cloud.easy.entity.ApiResponse;
import cloud.easy.entity.HttpResponse;
import cloud.easy.entity.PollMessage;
import cloud.easy.message.service.MessageContextService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * PullController
 *
 * @author xu honglin
 * @date 2021/12/1 21:46
 */
@Slf4j
@RestController
@RequestMapping("/api/message")
public class MessageController {

    @Resource
    private MessageContextService contextService;

    @PostMapping("/pull")
    public void pull(HttpServletRequest request, @RequestBody PollMessage pullMessage) {
        ApiHeader header = ApiHeaderHelper.get();
        pullMessage.setUserNo(header.getUserNo());
        AsyncContext context = request.startAsync();
        context.setTimeout(0);
        contextService.addPollClient(pullMessage, context, 29, TimeUnit.SECONDS);
    }

    @PostMapping("/push")
    public ApiResponse push(@RequestBody PollMessage pullMessage) {
        contextService.push(pullMessage);
        return HttpResponse.ok();
    }

}
