package cloud.easy.job.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;
import cloud.easy.base.entity.BaseEntity;

/**
 * 任务日志实体类
 *
 * @author xu honglin
 * @since 2021-12-06
 */
@Data
@TableName("job_log")
@ApiModel(value = "JobLog对象", description = "job_log")
@EqualsAndHashCode(callSuper = true)
public class JobLog extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 任务Id
     */
    private String jobId;
    /**
     * 任务名称
     */
    private String jobName;
    /**
     * 日志分类
     */
    private String type;
    /**
     * 执行时间
     */
    private LocalDateTime execTime;
    /**
     * 执行结果
     */
    private String execCode;
    /**
     * 执行结果描述
     */
    private String execContent;

}
