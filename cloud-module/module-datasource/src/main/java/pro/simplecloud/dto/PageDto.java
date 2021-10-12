package pro.simplecloud.dto;

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
    private long total = 0;
    /**
     * 每页显示条数，默认 5
     */
    private long pageSize = 5;

    /**
     * 当前页
     */
    private long current = 1;
}
