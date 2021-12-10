package cloud.easy.base.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Title: PageDto
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@Data
public class PageDto {
    /**
     * 总数
     */
    @ApiModelProperty(hidden = true)
    private long total = 0;
    /**
     * 每页显示条数，默认 5
     */
    @ApiModelProperty("每页显示条数")
    private long pageSize = 5;

    /**
     * 当前页
     */
    @ApiModelProperty("当前页")
    private long current = 1;
}
