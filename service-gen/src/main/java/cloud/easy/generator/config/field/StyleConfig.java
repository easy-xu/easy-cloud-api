package cloud.easy.generator.config.field;

import lombok.Builder;
import lombok.Data;

/**
 * StyleConfig
 *
 * @author xu honglin
 * @date 2021/12/7 16:07
 */
@Data
@Builder
public class StyleConfig {
    private Boolean display;
    private String displayCondition;
    private Boolean hidden;
    private Boolean disable;
    private Integer width;

}
