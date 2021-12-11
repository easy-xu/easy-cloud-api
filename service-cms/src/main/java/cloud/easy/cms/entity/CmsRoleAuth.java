package cloud.easy.cms.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import cloud.easy.base.entity.BaseEntity;
import javax.validation.constraints.NotNull;

/**
 * 角色和权限关联实体类
 *
 * @author generator
 * @since 2021-12-11
 */
@Data
@TableName("cms_role_auth")
@EqualsAndHashCode(callSuper = true)
public class CmsRoleAuth extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    @NotNull(message = "角色ID不能为空")
    private Long roleId;
    /**
     * 权限ID
     */
    @NotNull(message = "权限ID不能为空")
    private Long authId;

}
