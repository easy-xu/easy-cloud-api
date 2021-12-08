package cloud.easy.cms.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import cloud.easy.base.entity.AuthEntity;
import cloud.easy.cms.enums.MenuTypeEnum;

/**
 * 菜单实体类
 *
 * @author generator
 * @since 2021-12-08
 */
@Data
@TableName("cms_menu")
@ApiModel(value = "CmsMenu", description = "菜单实体类")
@EqualsAndHashCode(callSuper = true)
public class CmsMenu extends AuthEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单名称
     */
    @ApiModelProperty("菜单名称")
    private String name;
    /**
     * 路径字符
     */
    @ApiModelProperty("路径字符")
    private String code;
    /**
     * 父菜单
     */
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
    @ApiModelProperty("组件路径")
    private String component;
    /**
     * 菜单类型
     */
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
    @ApiModelProperty("菜单图标")
    private String icon;
    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remark;

}
