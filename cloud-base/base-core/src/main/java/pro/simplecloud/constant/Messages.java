package pro.simplecloud.constant;

/**
 * Title: Messages
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
public enum Messages {


    /**
     * 成功
     */
    OK(200, "成功"),
    /**
     * 客户端错误
     */
    USERNAME_EMPTY(400,"用户名不能为空"),
    USERNAME_EXIST(400, "用户名已注册"),
    LOGIN_ERROR(400, "用户名或密码错误"),
    ID_EMPTY(400, "ID不能为空"),

    NOT_FOUND(400, "数据不存在"),
    /**
     * 服务端错误
     */
    DB_DATA_ERROR(500, "数据库数据错误")
    ;
    private int code;
    private String message;

    Messages(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}