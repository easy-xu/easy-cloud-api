package cloud.easy.cms.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import cloud.easy.base.entity.BaseEntity;
import javax.validation.constraints.NotNull;

/**
 * 用户角色关联实体类
 *
 * @author generator
 * @since 2021-12-11
 */
@Data
@TableName("cms_user_role")
@EqualsAndHashCode(callSuper = true)
public class CmsUserRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @NotNull(message = "用户ID不能为空")
    private Long userId;
    /**
     * 角色ID
     */
    @NotNull(message = "角色ID不能为空")
    private Long roleId;

}
