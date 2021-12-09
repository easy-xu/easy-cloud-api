package cloud.easy.cms.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import cloud.easy.base.entity.BaseEntity;

/**
 * 用户和角色关联实体类
 *
 * @author generator
 * @since 2021-12-09
 */
@Data
@TableName("cms_user_role")
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
