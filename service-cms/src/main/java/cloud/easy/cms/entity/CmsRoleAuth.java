package cloud.easy.cms.entity;

import cloud.easy.base.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色和权限关联实体类
 *
 * @author generator
 * @since 2021-12-10
 */
@Data
@TableName("cms_role_auth")
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
