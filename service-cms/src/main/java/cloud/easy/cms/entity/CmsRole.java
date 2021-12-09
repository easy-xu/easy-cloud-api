package cloud.easy.cms.entity;

import cloud.easy.base.entity.AuthEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 角色实体类
 *
 * @author generator
 * @since 2021-12-09
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
    @ApiModelProperty("角色名称")
    private String name;
    /**
     * 角色字符串
     */
    @ApiModelProperty("角色字符串")
    private String code;
    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remark;
    /**
     * 权限
     */
    @ApiModelProperty("权限")
    @TableField(exist = false)
    private List<Long> authIds;

}
