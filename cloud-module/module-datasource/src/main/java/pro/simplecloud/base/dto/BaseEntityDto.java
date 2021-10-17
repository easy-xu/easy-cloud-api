package pro.simplecloud.base.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pro.simplecloud.base.entity.BaseEntity;

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
