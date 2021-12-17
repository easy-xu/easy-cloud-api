package cloud.easy.job.handler;

import cloud.easy.job.dto.SpringJobData;
import cloud.easy.utils.SpringUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * SpringJobTarget
 *
 * @author xu honglin
 * @date 2021/12/12 20:28
 */
@Slf4j
public class SpringJobHandler extends AbstractJobHandler<SpringJobData> {

    public SpringJobHandler(SpringJobData jobData) {
        super(jobData);
    }

    @Override
    protected void invoke() {
        Job job = SpringUtils.getBean(jobData.getBeanName(), Job.class);
        String params = jobData.getParams();
        if (params != null) {
            job.run(params.split(", ?"));
        } else {
            job.run();
        }
    }

    public interface Job {
        /**
         * 执行任务
         *
         * @param params 参数
         */
        void run(String... params);
    }
}
