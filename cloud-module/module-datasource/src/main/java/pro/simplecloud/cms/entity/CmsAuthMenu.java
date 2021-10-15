package pro.simplecloud.cms.entity;

import pro.simplecloud.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户和角色关联表
 * </p>
 *
 * @author Generator
 * @since 2021-10-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CmsAuthMenu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 权限ID
     */
    private Long authId;

    /**
     * 菜单ID
     */
    private Long menuId;


}
