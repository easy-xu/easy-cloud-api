package cloud.easy.cms.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Title: UserTypeEnum
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
public enum UserTypeEnum {
    //用户类型（00访客）
    VISITOR("00", "访客"),
    USER("01", "注册用户");


    @JsonValue
    @EnumValue
    private final String code;
    private final String name;

    UserTypeEnum(String code, String name){
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
