package cloud.easy.cms.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;
import cloud.easy.base.entity.AuthEntity;

/**
 * 权限实体类
 *
 * @author generator
 * @since 2021-12-09
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
    @ApiModelProperty("权限名称")
    private String name;
    /**
     * 权限字符串
     */
    @ApiModelProperty("权限字符串")
    private String code;
    /**
     * 备注
     */
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
