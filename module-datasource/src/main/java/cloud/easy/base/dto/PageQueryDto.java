package cloud.easy.base.dto;

import cloud.easy.base.entity.IEntity;
import lombok.Data;

import java.util.List;


/**
 * Title: PageQueryDto
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@Data
public class PageQueryDto<E extends IEntity> implements IDto {
    private E query;
    private PageDto page = new PageDto();
    private List<E> records;
}
