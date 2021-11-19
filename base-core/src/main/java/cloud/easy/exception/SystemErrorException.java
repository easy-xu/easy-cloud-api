package cloud.easy.exception;

import cloud.easy.constant.Messages;

/**
 * Title: SystemErrorException
 * Description: 系统内部异常
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

    public SystemErrorException(Messages messages, Throwable cause) {
        super(messages, cause);
    }

    public SystemErrorException(Messages messages) {
        super(messages);
    }
}
