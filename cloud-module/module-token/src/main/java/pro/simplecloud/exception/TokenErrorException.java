package pro.simplecloud.exception;

/**
 * Title: TokenErrorException
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 * @date 2021/7/2 18:11 首次创建
 * @date 2021/7/2 18:11 最后修改
 */
public class TokenErrorException extends BaseException {
    public TokenErrorException(String message) {
        super(401, message);
    }

    public TokenErrorException(String message, Throwable e) {
        super(401, message, e);
    }
}
