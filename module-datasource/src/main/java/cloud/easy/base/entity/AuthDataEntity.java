package cloud.easy.base.entity;

import cloud.easy.base.enums.DeletedEnum;
import cloud.easy.base.enums.ModeEnum;
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
public class AuthDataEntity extends BaseEntity {

    /**
     * 逻辑删除(0:正常, 1:停用)
     */
    private DeletedEnum deleted;

    /**
     * 数据分组
     */
    private Long groupId;

    /**
     * 所有者权限(-:不可读写, r:可读, w:可读可写)
     */
    private ModeEnum ownMode;

    /**
     * 同分组权限(-:不可读写, r:可读, w:可读可写)
     */
    private ModeEnum groupMode;

    /**
     * 其他分组权限(-:不可读写, r:可读, w:可读可写)
     */
    private ModeEnum otherMode;

}
