package cloud.easy.cms.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import cloud.easy.base.entity.BaseEntity;
import javax.validation.constraints.NotNull;

/**
 * 用户分组关联实体类
 *
 * @author generator
 * @since 2021-12-10
 */
@Data
@TableName("cms_user_group")
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
