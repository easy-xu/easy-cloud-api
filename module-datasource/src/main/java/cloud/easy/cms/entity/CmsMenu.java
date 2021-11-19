package cloud.easy.cms.entity;

import cloud.easy.base.entity.PrimaryDataEntity;
import cloud.easy.cms.enums.MenuTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 菜单权限表
 * </p>
 *
 * @author Generator
 * @since 2021-10-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CmsMenu extends PrimaryDataEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 路径字符
     */
    private String code;

    /**
     * 父菜单ID
     */
    private Long parentId;

    /**
     * 显示顺序
     */
    private Integer orderNum;

    /**
     * 组件路径
     */
    private String component;

    /**
     * 菜单类型（F目录 M菜单）
     */
    private MenuTypeEnum type;

    /**
     * 菜单状态（0显示 1隐藏）
     */
    private String visible;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 备注
     */
    private String remark;


}
