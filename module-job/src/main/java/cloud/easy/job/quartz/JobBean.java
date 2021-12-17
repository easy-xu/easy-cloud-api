package cloud.easy.job.quartz;

import cloud.easy.job.constant.JobConstant;
import cloud.easy.job.handler.AbstractJobHandler;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * QuartzJobBean
 *
 * @author xu honglin
 * @date 2021/12/13 22:18
 */
@Slf4j
@DisallowConcurrentExecution
public class JobBean extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext context) {
        JobDetail jobDetail = context.getJobDetail();
        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        Object object = jobDataMap.get(JobConstant.JOB_INVOKER);
        if (object instanceof AbstractJobHandler) {
            AbstractJobHandler<?> jobHandler = (AbstractJobHandler<?>) object;
            jobHandler.runJob();
        }
    }
}
