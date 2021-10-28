package cloud.easy.cms.entity;

import cloud.easy.base.entity.PrimaryDataEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 操作类型表
 * </p>
 *
 * @author Generator
 * @since 2021-10-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CmsOption extends PrimaryDataEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 操作名称
     */
    private String name;

    /**
     * 操作字符串
     */
    private String code;

    /**
     * 备注
     */
    private String remark;


}
