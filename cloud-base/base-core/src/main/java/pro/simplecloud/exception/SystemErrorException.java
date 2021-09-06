package pro.simplecloud.exception;

/**
 * Title: SystemErrorException
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 * @date 2021/4/1 10:57 首次创建
 * @date 2021/4/1 10:57 最后修改
 */
public class SystemErrorException extends BaseException {

    public SystemErrorException(String message, Throwable cause) {
        super(500, message, cause);
    }

    public SystemErrorException(String message) {
        super(500, message);
    }
}
