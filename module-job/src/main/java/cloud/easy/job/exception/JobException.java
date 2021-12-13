package cloud.easy.job.exception;

import cloud.easy.exception.BaseException;

/**
 * JobException
 *
 * @author xu honglin
 * @date 2021/12/13 17:56
 */
public class JobException extends BaseException {
    public JobException(int code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public JobException(int code, String message) {
        super(code, message);
    }
}
