package cloud.easy.base.dto;

import cloud.easy.base.entity.BaseEntity;
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
public class PageQueryDto<T extends BaseEntity> {
    private T query;
    private PageDto page = new PageDto();
    private List<T> records;
}
