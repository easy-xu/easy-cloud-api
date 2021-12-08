package cloud.easy.job.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;
import cloud.easy.base.entity.AuthDataEntity;

/**
 * 任务实体类
 *
 * @author xu honglin
 * @since 2021-12-08
 */
@Data
@TableName("job_config")
@ApiModel(value = "JobConfig", description = "任务实体类")
@EqualsAndHashCode(callSuper = true)
public class JobConfig extends AuthDataEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 任务名称
     */
    @ApiModelProperty("任务名称")
    private String name;
    /**
     * 周期表达式
     */
    @ApiModelProperty("周期表达式")
    private String cron;
    /**
     * 任务类名
     */
    @ApiModelProperty("任务类名")
    private String beanName;
    /**
     * 任务方法名
     */
    @ApiModelProperty("任务方法名")
    private String methodName;
    /**
     * 任务参数
     */
    @ApiModelProperty("任务参数")
    private String params;
    /**
     * 最后触发时间
     */
    @ApiModelProperty("最后触发时间")
    private LocalDateTime lastTrigger;

}
