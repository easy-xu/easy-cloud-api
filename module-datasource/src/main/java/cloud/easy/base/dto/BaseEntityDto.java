package cloud.easy.base.dto;

import cloud.easy.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Title: BaseEntityDto
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseEntityDto extends BaseEntity {
    private List<Long> ids;
}
