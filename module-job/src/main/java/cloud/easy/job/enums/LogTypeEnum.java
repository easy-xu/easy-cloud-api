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
public enum LogTypeEnum {
    //执行结果
    SYSTEM("S", "系统日志"),
    USER("U", "用户日志");


    @JsonValue
    @EnumValue
    private final String code;
    private final String name;

    LogTypeEnum(String code, String name) {
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
