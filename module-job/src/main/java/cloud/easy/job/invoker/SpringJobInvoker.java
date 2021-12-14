package cloud.easy.job.invoker;

import cloud.easy.job.data.SpringJobData;
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
        Job job = SpringUtils.getBean(jobData.getBeanName(), Job.class);
        String argStr = jobData.getArgStr();
        if (argStr != null) {
            job.run(argStr.split(","));
        } else {
            job.run();
        }
    }

    public interface Job {
        /**
         * 执行任务
         * @param args 参数
         */
        void run(String...args);
    }
}
