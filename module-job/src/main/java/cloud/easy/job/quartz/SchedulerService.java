package cloud.easy.job.quartz;

import cloud.easy.job.constant.JobConstant;
import cloud.easy.job.dto.JobData;
import cloud.easy.job.dto.TriggerDto;
import cloud.easy.job.exception.JobException;
import cloud.easy.job.handler.AbstractJobHandler;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * SchedulerService
 *
 * @author xu honglin
 * @date 2021/12/13 15:18
 */
@Slf4j
@Service
public class SchedulerService {
    @Resource
    Scheduler scheduler;

    public void scheduleJob(AbstractJobHandler<? extends JobData> jobHandler) {
        JobData jobData = jobHandler.getJobData();
        String identity = jobData.getJobId();
        try {
            JobKey jobKey = new JobKey(identity);
            //配置计划任务的工作类名，这个类需要实现Job接口，在execute方法中实现所需要做的工作
            JobDetail jobDetail = JobBuilder
                    .newJob(JobBean.class)
                    .withIdentity(identity)
                    .build();
            //向工作类传递参数
            jobDetail.getJobDataMap().put(JobConstant.JOB_INVOKER, jobHandler);
            //表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
                    .cronSchedule(jobData.getCron());
            // 按新的cronExpression表达式构建一个新的trigger
            CronTrigger trigger = TriggerBuilder
                    .newTrigger()
                    .withIdentity(identity)
                    .withSchedule(scheduleBuilder)
                    .build();
            //检查是否重复设置
            if (scheduler.checkExists(jobKey)) {
                throw new JobException(400, "开启任务异常:任务已存在");
            }
            // 注入到管理类
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            throw new JobException(400, "开启任务异常:" + e.getMessage(), e);
        }
    }


    public void pauseJob(String jobIdentity) {
        try {
            scheduler.pauseJob(getJobKey(jobIdentity));
        } catch (SchedulerException e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            throw new JobException(400, "暂停任务异常:" + e.getMessage(), e);
        }
    }


    public void deleteJob(String jobIdentity) {
        try {
            JobKey jobKey = getJobKey(jobIdentity);
            scheduler.pauseJob(jobKey);
            scheduler.deleteJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            throw new JobException(400, "开启任务异常:" + e.getMessage(), e);
        }
    }

    public void triggerOnce(String jobIdentity) {
        try {
            scheduler.triggerJob(getJobKey(jobIdentity));
        } catch (SchedulerException e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            throw new JobException(400, "触发任务异常:" + e.getMessage(), e);
        }
    }

    public TriggerDto getJobTriggerDto(String jobIdentity) {
        TriggerDto triggerDto = new TriggerDto();
        try {
            TriggerKey triggerKey = new TriggerKey(jobIdentity);
            Trigger trigger = scheduler.getTrigger(triggerKey);
            Trigger.TriggerState triggerState = scheduler.getTriggerState(triggerKey);
            if (trigger != null) {
                triggerDto.setStartTime(trigger.getStartTime());
                triggerDto.setEndTime(trigger.getEndTime());
                triggerDto.setPreviousFireTime(trigger.getPreviousFireTime());
                if (Trigger.TriggerState.NORMAL.equals(triggerState)){
                    triggerDto.setNextFireTime(trigger.getNextFireTime());
                }
            }
            triggerDto.setState(triggerState.name());
        } catch (SchedulerException e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            //throw new JobException(400, "获取任务异常:" + e.getMessage(), e);
        }
        return triggerDto;
    }

    private JobKey getJobKey(String jobIdentity) {
        JobKey jobKey = new JobKey(jobIdentity);
        try {
            if (!scheduler.checkExists(jobKey)) {
                throw new JobException(400, "任务未开启");
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            throw new JobException(400, "任务异常:" + e.getMessage(), e);
        }
        return jobKey;
    }
}
