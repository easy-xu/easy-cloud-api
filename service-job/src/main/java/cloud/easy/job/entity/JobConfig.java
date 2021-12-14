package cloud.easy.job.entity;

import cloud.easy.base.entity.AuthEntity;
import cloud.easy.job.data.TriggerDto;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 任务实体类
 *
 * @author generator
 * @since 2021-12-11
 */
@Data
@TableName("job_config")
@ApiModel(value = "JobConfig", description = "任务实体类")
@EqualsAndHashCode(callSuper = true)
public class JobConfig extends AuthEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 任务名称
     */
    @NotNull(message = "任务名称不能为空")
    @Length(max = 60, message = "任务名称长度不能超过60")
    @ApiModelProperty("任务名称")
    private String name;
    /**
     * 周期表达式
     */
    @NotNull(message = "周期表达式不能为空")
    @Length(max = 60, message = "周期表达式长度不能超过60")
    @ApiModelProperty("周期表达式")
    private String cron;
    /**
     * 任务类名
     */
    @Length(max = 60, message = "任务类名长度不能超过60")
    @ApiModelProperty("任务类名")
    private String beanName;
    /**
     * 任务方法名
     */
    @Length(max = 60, message = "任务方法名长度不能超过60")
    @ApiModelProperty("任务方法名")
    private String methodName;
    /**
     * 任务参数
     */
    @Length(max = 500, message = "任务参数长度不能超过500")
    @ApiModelProperty("任务参数")
    private String params;


    @ApiModelProperty(hidden = true)
    @TableField(exist = false)
    private TriggerDto trigger;

}
