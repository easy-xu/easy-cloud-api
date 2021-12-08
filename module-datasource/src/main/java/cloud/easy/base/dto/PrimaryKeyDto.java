package cloud.easy.base.dto;

import lombok.Data;

/**
 * QueryDto
 *
 * @author xu honglin
 * @date 2021/12/8 11:27
 */
@Data
public class PrimaryKeyDto implements IDto{
    /**
     * 主键
     */
    private Long id;
}
