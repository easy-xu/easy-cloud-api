package cloud.easy.job.dto;

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
     * 一次任务不重复的编号
     */
    private String requestId;

    /**
     * 任务标识
     */
    private String jobId;
    /**
     * 任务名称
     */
    private String jobName;
    /**
     * 周期表达式
     */
    private String cron;


}
