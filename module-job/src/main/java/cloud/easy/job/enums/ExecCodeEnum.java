package cloud.easy.job.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Title: ExecCodeEnum
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
public enum ExecCodeEnum {
    //执行结果
    SUCCESS("0", "成功"),
    ERROR("1", "异常");


    @JsonValue
    @EnumValue
    private final String code;
    private final String name;

    ExecCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
