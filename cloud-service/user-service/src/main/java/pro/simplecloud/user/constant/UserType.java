package pro.simplecloud.user.constant;

/**
 * Title: UserType
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
public enum UserType {
    //用户类型（00访客）
    VISITOR("00"),
    USER("01");

    public final String code;

    UserType(String code) {
        this.code = code;
    }
}
