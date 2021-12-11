package cloud.easy.cms.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import cloud.easy.base.entity.AuthEntity;
import cloud.easy.cms.enums.MenuTypeEnum;
import javax.validation.constraints.NotNull;
import cloud.easy.validation.UniqueField;
import org.hibernate.validator.constraints.Length;

/**
 * 菜单实体类
 *
 * @author generator
 * @since 2021-12-11
 */
@Data
@TableName("cms_menu")
@ApiModel(value = "CmsMenu", description = "菜单实体类")
@UniqueField(field = "code", message = "路径字符已存在")
@EqualsAndHashCode(callSuper = true)
public class CmsMenu extends AuthEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单名称
     */
    @NotNull(message = "菜单名称不能为空")
    @Length(max = 50, message = "菜单名称长度不能超过50")
    @ApiModelProperty("菜单名称")
    private String name;
    /**
     * 路径字符
     */
    @NotNull(message = "路径字符不能为空")
    @Length(max = 200, message = "路径字符长度不能超过200")
    @ApiModelProperty("路径字符")
    private String code;
    /**
     * 父菜单
     */
    @NotNull(message = "父菜单不能为空")
    @ApiModelProperty("父菜单")
    private Long parentId;
    /**
     * 显示顺序
     */
    @ApiModelProperty("显示顺序")
    private Integer orderNum;
    /**
     * 组件路径
     */
    @Length(max = 255, message = "组件路径长度不能超过255")
    @ApiModelProperty("组件路径")
    private String component;
    /**
     * 菜单类型
     */
    @NotNull(message = "菜单类型不能为空")
    @ApiModelProperty("菜单类型")
    private MenuTypeEnum type;
    /**
     * 菜单状态
     */
    @ApiModelProperty("菜单状态")
    private String visible;
    /**
     * 菜单图标
     */
    @Length(max = 100, message = "菜单图标长度不能超过100")
    @ApiModelProperty("菜单图标")
    private String icon;
    /**
     * 备注
     */
    @Length(max = 500, message = "备注长度不能超过500")
    @ApiModelProperty("备注")
    private String remark;

}
