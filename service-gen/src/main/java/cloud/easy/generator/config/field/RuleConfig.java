package cloud.easy.generator.config.field;

import cloud.easy.utils.JsonUtils;
import lombok.Data;

/**
 * RuleConfig
 *
 * @author xu honglin
 * @date 2021/12/10 14:31
 */
@Data
public class RuleConfig {
    private String type;
    private Integer max;
    private Boolean require;

    public RuleConfig() {
    }

    public RuleConfig(Boolean require) {
        this.require = require;
    }

    public RuleConfig(Boolean require, String type, Integer max) {
        this.type = type;
        this.max = max;
        this.require = require;
    }

    @Override
    public String toString(){
        return JsonUtils.toString(this).replace("\"", "");
    }

}
