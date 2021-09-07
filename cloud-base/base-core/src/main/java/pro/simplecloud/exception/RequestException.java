package pro.simplecloud.exception;

/**
 * Title: RequestException
 * Description: 客户端错误，只提示异常信息
 *
 * @author Xu Honglin
 * @version 1.0
 */
public class RequestException extends BaseException{
    public RequestException(String message) {
        super(400, message);
    }
}
