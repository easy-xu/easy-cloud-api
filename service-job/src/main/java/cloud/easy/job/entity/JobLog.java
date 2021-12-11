package cloud.easy.job.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;
import cloud.easy.base.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;

/**
 * 任务日志实体类
 *
 * @author generator
 * @since 2021-12-11
 */
@Data
@TableName("job_log")
@ApiModel(value = "JobLog", description = "任务日志实体类")
@EqualsAndHashCode(callSuper = true)
public class JobLog extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 任务
     */
    @NotNull(message = "任务不能为空")
    @ApiModelProperty("任务")
    private String jobId;
    /**
     * 日志分类
     */
    @ApiModelProperty("日志分类")
    private String type;
    /**
     * 执行时间
     */
    @ApiModelProperty("执行时间")
    private LocalDateTime execTime;
    /**
     * 执行结果
     */
    @ApiModelProperty("执行结果")
    private String execCode;
    /**
     * 执行结果描述
     */
    @Length(max = 65535, message = "执行结果描述长度不能超过65535")
    @ApiModelProperty("执行结果描述")
    private String execContent;

}
