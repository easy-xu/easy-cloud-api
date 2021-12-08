package cloud.easy.base.dto;

import lombok.Data;

import java.util.List;

/**
 * BatchDto
 *
 * @author xu honglin
 * @date 2021/12/8 11:02
 */
@Data
public class BatchKeyDto {
    /**
     * 批量主键
     */
    private List<Long> ids;
}
