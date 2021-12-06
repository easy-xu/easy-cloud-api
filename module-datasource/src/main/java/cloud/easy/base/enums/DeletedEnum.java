package cloud.easy.base.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Title: DeletedEnum
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
public enum DeletedEnum {
    /**
     * 删除标志(0:正常, 1:停用)
     */
    DELETED("1", "停用"),
    NOT_DELETED("0", "正常");

    @JsonValue
    @EnumValue
    private final String code;
    private final String name;

    DeletedEnum(String code, String name) {
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
