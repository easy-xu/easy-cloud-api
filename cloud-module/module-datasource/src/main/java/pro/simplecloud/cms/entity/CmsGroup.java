package pro.simplecloud.cms.entity;

import pro.simplecloud.entity.PrimaryDataEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 分组表
 * </p>
 *
 * @author Generator
 * @since 2021-10-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CmsGroup extends PrimaryDataEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 分组名称
     */
    private String name;

    /**
     * 分组字符串
     */
    private String code;

    /**
     * 备注
     */
    private String remark;


}
