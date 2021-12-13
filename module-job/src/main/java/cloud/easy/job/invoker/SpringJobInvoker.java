package cloud.easy.job.invoker;

import cloud.easy.job.data.SpringJobData;
import cloud.easy.job.service.IJobService;
import cloud.easy.utils.SpringUtils;

/**
 * SpringJobTarget
 *
 * @author xu honglin
 * @date 2021/12/12 20:28
 */
public class SpringJobInvoker extends JobInvoker<SpringJobData> {

    public SpringJobInvoker(SpringJobData jobData) {
        super(jobData);
    }

    @Override
    public void invoke() {
        IJobService job = SpringUtils.getBean(jobData.getBeanName(), IJobService.class);
        job.run(jobData.getArgs());
    }
}
