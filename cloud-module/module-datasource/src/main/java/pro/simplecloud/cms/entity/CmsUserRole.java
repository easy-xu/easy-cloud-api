package pro.simplecloud.cms.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pro.simplecloud.entity.BaseEntity;

/**
 * <p>
 * 用户和角色关联表
 * </p>
 *
 * @author Generator
 * @since 2021-10-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CmsUserRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 角色ID
     */
    private Long roleId;


}
