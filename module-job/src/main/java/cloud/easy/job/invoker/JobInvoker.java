package cloud.easy.job.invoker;

import cloud.easy.job.data.JobData;
import lombok.Data;

import java.io.Serializable;

/**
 * JobInvoker
 *
 * @author xu honglin
 * @date 2021/12/12 20:22
 */
@Data
public  abstract class JobInvoker<T extends JobData> implements Serializable {

    protected T jobData;

    protected JobInvoker(T jobData) {
        this.jobData = jobData;
    }

    /**
     * 调用任务
     */
    public abstract void invoke();

}
