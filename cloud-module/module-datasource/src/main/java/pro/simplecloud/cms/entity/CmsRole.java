package pro.simplecloud.cms.entity;

import pro.simplecloud.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 角色信息表
 * </p>
 *
 * @author Generator
 * @since 2021-10-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CmsRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色权限字符串
     */
    private String roleCode;

    /**
     * 显示顺序
     */
    private Integer orderNum;

    /**
     * 角色状态（0正常 1停用）
     */
    private String status;

    /**
     * 备注
     */
    private String remark;


}
