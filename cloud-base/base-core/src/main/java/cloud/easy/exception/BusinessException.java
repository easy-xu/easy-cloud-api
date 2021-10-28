package cloud.easy.exception;

import cloud.easy.constant.Messages;

/**
 * Title: BusinessException
 * Description: 需要特殊处理逻辑的业务异常
 *
 * @author Xu Honglin
 * @version 1.0
 * @date 2021/4/1 10:58 首次创建
 * @date 2021/4/1 10:58 最后修改
 */
public class BusinessException extends BaseException {

    public BusinessException(Messages messages) {
        super(messages.getCode(), messages.getMessage());
    }

    public BusinessException(Messages messagesEnum, String message) {
        super(messagesEnum.getCode(), message);
    }
}
