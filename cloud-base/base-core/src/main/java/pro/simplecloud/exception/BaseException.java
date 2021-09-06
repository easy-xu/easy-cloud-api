package pro.simplecloud.exception;

/**
 * Title: RequestErrorException
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 * @date 2020/7/17 15:46 首次创建
 * @date 2020/7/17 15:46 最后修改
 */
public class BaseException extends RuntimeException {

    private int code;

    public BaseException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public BaseException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
