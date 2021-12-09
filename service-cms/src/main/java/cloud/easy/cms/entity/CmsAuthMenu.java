package cloud.easy.cms.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import cloud.easy.base.entity.BaseEntity;

/**
 * 权限菜单关联实体类
 *
 * @author generator
 * @since 2021-12-09
 */
@Data
@TableName("cms_auth_menu")
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
