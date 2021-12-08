package cloud.easy.cms.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Title: MenuTypeEnum
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
public enum MenuTypeEnum {
    //菜单类型（F目录 M菜单）
    FOLDER("F", "目录"),
    MENU("M", "菜单");

    @JsonValue
    @EnumValue
    private final String code;
    private final String name;

    MenuTypeEnum(String code, String name) {
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
