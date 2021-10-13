package pro.simplecloud.cms.entity;

import pro.simplecloud.entity.LogicDeleteEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 菜单权限表
 * </p>
 *
 * @author Generator
 * @since 2021-10-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CmsMenu extends LogicDeleteEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 父菜单ID
     */
    private Long parentId;

    /**
     * 显示顺序
     */
    private Integer orderNum;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 组件路径
     */
    private String component;

    /**
     * 菜单类型（F目录 M菜单）
     */
    private String type;

    /**
     * 菜单状态（0显示 1隐藏）
     */
    private String visible;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 状态（0正常 1停用）
     */
    private String status;

    /**
     * 备注
     */
    private String remark;


}
