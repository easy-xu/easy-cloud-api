package pro.simplecloud.exception.code;


/**
 * Title: BusinessExceptionEnum
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 * @date 2020/7/17 18:47 首次创建
 * @date 2020/7/17 18:47 最后修改
 */
public enum BusinessExceptionEnum {

    /**
     * 区分编号的业务异常
     */
    BUSINESS_ERROR(5000, "business error"),

    NOT_LOGIN(5001, "not login"),

    ERROR_LOGIN(5002, "username or password error");


    public int code;
    public String message;

    BusinessExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
