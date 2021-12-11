package cloud.easy.cms.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Title: AuthDto
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@Data
public class CodeDto {

    @NotNull(message = "编码不能为空")
    @ApiModelProperty("编码")
    private String code;
}
