package cloud.easy.cms.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import cloud.easy.base.entity.AuthEntity;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * 分组实体类
 *
 * @author generator
 * @since 2021-12-10
 */
@Data
@TableName("cms_group")
@ApiModel(value = "CmsGroup", description = "分组实体类")
@EqualsAndHashCode(callSuper = true)
public class CmsGroup extends AuthEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 分组名称
     */
    @NotNull(message = "分组名称不能为空")
    @Max(value = 30, message = "分组名称长度不能超过30")
    @ApiModelProperty("分组名称")
    private String name;
    /**
     * 分组字符串
     */
    @NotNull(message = "分组字符串不能为空")
    @Max(value = 100, message = "分组字符串长度不能超过100")
    @ApiModelProperty("分组字符串")
    private String code;
    /**
     * 备注
     */
    @Max(value = 500, message = "备注长度不能超过500")
    @ApiModelProperty("备注")
    private String remark;

}
