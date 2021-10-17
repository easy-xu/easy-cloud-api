package pro.simplecloud.base.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Title: PrimaryDataEntity
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PrimaryDataEntity extends BaseEntity {

    /**
     * 删除标志（0正常 1停用）
     */
    private String deleted;

    /**
     * 数据分组
     */
    private Long groupId;

    /**
     * 所有者权限（-不可读不可写 r可读 w可读可写）
     */
    private String ownMode;

    /**
     * 同分组权限（-不可读不可写 r可读 w可读可写）
     */
    private String groupMode;

    /**
     * 其他分组权限（-不可读不可写 r可读 w可读可写）
     */
    private String otherMode;

}
