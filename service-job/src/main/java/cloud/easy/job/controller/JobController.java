package cloud.easy.job.controller;

import cloud.easy.annotation.Option;
import cloud.easy.base.dto.PrimaryKeyDto;
import cloud.easy.entity.ApiResponse;
import cloud.easy.entity.HttpResponse;
import cloud.easy.job.entity.JobConfig;
import cloud.easy.job.invoker.JobInvoker;
import cloud.easy.job.service.IJobConfigService;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * JobController
 *
 * @author xu honglin
 * @date 2021/12/12 22:39
 */
@RestController
@RequestMapping("/api/job")
@Api(tags = "任务调度接口")
public class JobController {

    @Resource
    private JobInvoker jobInvoker;
    @Resource
    private IJobConfigService jobConfigService;

    @Option(value = "任务调用", menuCode = "config", optionCode = "edit")
    @PostMapping("/run")
    public ApiResponse run(@Validated @RequestBody PrimaryKeyDto primaryKey) {
        JobConfig jobConfig = jobConfigService.getById(primaryKey.getId());
        jobInvoker.invoke(jobConfig.getBeanName(), jobConfig.getParams().split(","));
        return HttpResponse.ok();
    }
}
