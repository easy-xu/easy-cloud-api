package cloud.easy.cms.entity;

import cloud.easy.base.entity.AuthEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 角色实体类
 *
 * @author generator
 * @since 2021-12-10
 */
@Data
@TableName("cms_role")
@ApiModel(value = "CmsRole", description = "角色实体类")
@EqualsAndHashCode(callSuper = true)
public class CmsRole extends AuthEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 角色名称
     */
    @NotNull(message = "角色名称不能为空")
    @Max(value = 30, message = "角色名称长度不能超过30")
    @ApiModelProperty("角色名称")
    private String name;
    /**
     * 角色字符串
     */
    @NotNull(message = "角色字符串不能为空")
    @Max(value = 100, message = "角色字符串长度不能超过100")
    @ApiModelProperty("角色字符串")
    private String code;
    /**
     * 备注
     */
    @Max(value = 500, message = "备注长度不能超过500")
    @ApiModelProperty("备注")
    private String remark;
    /**
     * 权限
     */
    @ApiModelProperty("权限")
    @TableField(exist = false)
    private List<Long> authIds;

}
