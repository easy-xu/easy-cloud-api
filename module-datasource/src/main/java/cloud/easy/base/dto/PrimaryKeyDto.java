package cloud.easy.base.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * QueryDto
 *
 * @author xu honglin
 * @date 2021/12/8 11:27
 */
@Data
public class PrimaryKeyDto implements IDto{

    @NotNull(message = "id不能为空")
    @ApiModelProperty("id")
    private Long id;
}
