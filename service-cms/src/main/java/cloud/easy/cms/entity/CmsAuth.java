package cloud.easy.cms.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;
import cloud.easy.base.entity.AuthEntity;
import cloud.easy.validation.UniqueColumn;
import javax.validation.constraints.NotNull;
import com.baomidou.mybatisplus.annotation.TableField;
import org.hibernate.validator.constraints.Length;

/**
 * 权限实体类
 *
 * @author generator
 * @since 2021-12-11
 */
@Data
@TableName("cms_auth")
@ApiModel(value = "CmsAuth", description = "权限实体类")
@EqualsAndHashCode(callSuper = true)
public class CmsAuth extends AuthEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 权限名称
     */
    @NotNull(message = "权限名称不能为空")
    @Length(max = 50, message = "权限名称长度不能超过50")
    @ApiModelProperty("权限名称")
    private String name;
    /**
     * 权限字符串
     */
    @NotNull(message = "权限字符串不能为空")
    @Length(max = 100, message = "权限字符串长度不能超过100")
    @UniqueColumn(table = "cms_auth", column = "code", message = "权限字符串已存在")
    @ApiModelProperty("权限字符串")
    private String code;
    /**
     * 备注
     */
    @Length(max = 500, message = "备注长度不能超过500")
    @ApiModelProperty("备注")
    private String remark;
    /**
     * 权限菜单
     */
    @ApiModelProperty("权限菜单")
    @TableField(exist = false)
    private List<Long> menuIds;
    /**
     * 权限操作
     */
    @ApiModelProperty("权限操作")
    @TableField(exist = false)
    private List<Long> optionIds;

}
