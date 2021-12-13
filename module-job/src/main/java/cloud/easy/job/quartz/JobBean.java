package cloud.easy.job.quartz;

import cloud.easy.job.constant.JobConstant;
import cloud.easy.job.invoker.JobInvoker;
import org.quartz.*;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * QuartzJobBean
 *
 * @author xu honglin
 * @date 2021/12/13 22:18
 */
@DisallowConcurrentExecution
public class JobBean extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext context) {
        JobDetail jobDetail = context.getJobDetail();
        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        Object jobInvoker = jobDataMap.get(JobConstant.JOB_INVOKER);
        if (jobInvoker instanceof JobInvoker){
            ((JobInvoker<?>) jobInvoker).invoke();
        }
    }
}
