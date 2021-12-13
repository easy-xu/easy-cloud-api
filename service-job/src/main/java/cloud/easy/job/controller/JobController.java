package cloud.easy.job.controller;

import cloud.easy.annotation.Option;
import cloud.easy.base.dto.PrimaryKeyDto;
import cloud.easy.entity.ApiResponse;
import cloud.easy.entity.HttpResponse;
import cloud.easy.job.entity.JobConfig;
import cloud.easy.job.data.SpringJobData;
import cloud.easy.job.invoker.SpringJobInvoker;
import cloud.easy.job.quartz.SchedulerService;
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
    private SchedulerService schedulerService;
    @Resource
    private IJobConfigService jobConfigService;

    @Option(value = "任务调用", menuCode = "config", optionCode = "edit")
    @PostMapping("/run")
    public ApiResponse run(@Validated @RequestBody PrimaryKeyDto primaryKey) {
        JobConfig jobConfig = jobConfigService.getById(primaryKey.getId());
        SpringJobData jobData = new SpringJobData();
        jobData.setIdentity(String.valueOf(jobConfig.getId()));
        jobData.setCron(jobConfig.getCron());
        jobData.setBeanName(jobConfig.getBeanName());
        jobData.setArgs(jobConfig.getParams().split(","));
        new SpringJobInvoker(jobData).invoke();
        return HttpResponse.ok();
    }

    @Option(value = "开启任务", menuCode = "config", optionCode = "edit")
    @PostMapping("/start")
    public ApiResponse start(@Validated @RequestBody PrimaryKeyDto primaryKey) {
        JobConfig jobConfig = jobConfigService.getById(primaryKey.getId());
        SpringJobData jobData = new SpringJobData();
        jobData.setIdentity(String.valueOf(jobConfig.getId()));
        jobData.setCron(jobConfig.getCron());
        jobData.setBeanName(jobConfig.getBeanName());
        jobData.setArgs(jobConfig.getParams().split(","));
        schedulerService.scheduleJob(new SpringJobInvoker(jobData));
        return HttpResponse.ok();
    }
    @Option(value = "停止任务", menuCode = "config", optionCode = "edit")
    @PostMapping("/stop")
    public ApiResponse stop(@Validated @RequestBody PrimaryKeyDto primaryKey) {
        schedulerService.deleteJob(String.valueOf(primaryKey.getId()));
        return HttpResponse.ok();
    }
}
