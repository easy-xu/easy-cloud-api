package pro.simplecloud.cms.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pro.simplecloud.entity.BaseEntity;

/**
 * <p>
 * 角色和菜单关联表
 * </p>
 *
 * @author Generator
 * @since 2021-10-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CmsRoleMenu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 菜单ID
     */
    private Long menuId;


}
