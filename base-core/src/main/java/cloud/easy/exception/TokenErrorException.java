package cloud.easy.exception;

import cloud.easy.constant.Messages;

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
    public TokenErrorException(Messages message) {
        super(message.getCode(), message.getMessage());
    }
}
