package cloud.easy.base.entity;

import cloud.easy.base.enums.DeletedEnum;
import cloud.easy.base.enums.ModeEnum;
import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(hidden = true)
    private DeletedEnum deleted;

    /**
     * 数据分组
     */
    @ApiModelProperty(hidden = true)
    private Long groupId;

    /**
     * 所有者权限(-:不可读写, r:可读, w:可读可写)
     */
    @ApiModelProperty(hidden = true)
    private ModeEnum ownMode;

    /**
     * 同分组权限(-:不可读写, r:可读, w:可读可写)
     */
    @ApiModelProperty(hidden = true)
    private ModeEnum groupMode;

    /**
     * 其他分组权限(-:不可读写, r:可读, w:可读可写)
     */
    @ApiModelProperty(hidden = true)
    private ModeEnum otherMode;

}
