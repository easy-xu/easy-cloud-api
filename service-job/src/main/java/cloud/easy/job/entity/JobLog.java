package cloud.easy.job.entity;

import cloud.easy.base.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * 任务日志实体类
 *
 * @author generator
 * @since 2021-12-10
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
