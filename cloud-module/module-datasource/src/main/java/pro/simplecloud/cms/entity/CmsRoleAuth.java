package pro.simplecloud.cms.entity;

import pro.simplecloud.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 角色和权限关联表
 * </p>
 *
 * @author Generator
 * @since 2021-10-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CmsRoleAuth extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 权限ID
     */
    private Long authId;


}
