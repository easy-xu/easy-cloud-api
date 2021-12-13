package cloud.easy.job.invoker;

import cloud.easy.job.data.JobData;
import lombok.Data;

/**
 * JobInvoker
 *
 * @author xu honglin
 * @date 2021/12/12 20:22
 */
@Data
public  abstract class JobInvoker<T extends JobData> {

    protected T jobData;

    public JobInvoker(T jobData) {
        this.jobData = jobData;
    }

    /**
     * 调用任务
     */
    public abstract void invoke();

}
