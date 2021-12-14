package cloud.easy.job.data;

import lombok.Data;

import java.io.Serializable;

/**
 * IJobData
 *
 * @author xu honglin
 * @date 2021/12/13 17:00
 */
@Data
public abstract class JobData implements Serializable {

    /**
     * 任务标识
     */
    private String identity;
    /**
     * 周期表达式
     */
    private String cron;


}
