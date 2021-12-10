package cloud.easy.base.dto;

import cloud.easy.base.entity.IEntity;
import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty("查询条件")
    private E query;
    @ApiModelProperty("分页")
    private PageDto page = new PageDto();
    @ApiModelProperty(hidden = true)
    private List<E> records;
}
