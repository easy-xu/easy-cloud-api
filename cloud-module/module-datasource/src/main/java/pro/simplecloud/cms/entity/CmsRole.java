package pro.simplecloud.cms.entity;

import pro.simplecloud.entity.LogicDeleteEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 角色信息表
 * </p>
 *
 * @author Generator
 * @since 2021-10-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CmsRole extends LogicDeleteEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色权限字符串
     */
    private String code;

    /**
     * 状态（0正常 1停用）
     */
    private String status;

    /**
     * 备注
     */
    private String remark;


}
