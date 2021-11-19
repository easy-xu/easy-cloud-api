package cloud.easy.cms.entity;

import cloud.easy.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户和分组关联表
 * </p>
 *
 * @author Generator
 * @since 2021-10-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CmsUserGroup extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 分组ID
     */
    private Long groupId;


}
