package cloud.easy.cms.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import cloud.easy.base.entity.BaseEntity;
import javax.validation.constraints.NotNull;

/**
 * 权限操作关联实体类
 *
 * @author generator
 * @since 2021-12-11
 */
@Data
@TableName("cms_auth_option")
@EqualsAndHashCode(callSuper = true)
public class CmsAuthOption extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 权限ID
     */
    @NotNull(message = "权限ID不能为空")
    private Long authId;
    /**
     * 操作ID
     */
    @NotNull(message = "操作ID不能为空")
    private Long optionId;

}
