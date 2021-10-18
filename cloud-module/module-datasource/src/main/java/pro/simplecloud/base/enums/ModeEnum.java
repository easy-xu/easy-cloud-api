package pro.simplecloud.base.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Title: ModeEnum
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
public enum ModeEnum {

    /**
     * 权限（-不可读不可写 r可读 w可读可写）
     */
    NOT_READ("-", "不可读写"),
    READ("r", "可读"),
    READ_WRITE("w", "可读写");

    @JsonValue
    @EnumValue
    private final String code;
    private final String name;

    ModeEnum(String code, String name){
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
