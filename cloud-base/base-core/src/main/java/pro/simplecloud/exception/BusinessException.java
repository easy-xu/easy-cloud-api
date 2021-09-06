package pro.simplecloud.exception;

import pro.simplecloud.exception.code.BusinessExceptionEnum;

/**
 * Title: BusinessException
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 * @date 2021/4/1 10:58 首次创建
 * @date 2021/4/1 10:58 最后修改
 */
public class BusinessException extends BaseException {

    public BusinessException(BusinessExceptionEnum exceptionEnum) {
        super(exceptionEnum.code, exceptionEnum.message);
    }

    public BusinessException(BusinessExceptionEnum exceptionEnum, String message) {
        super(exceptionEnum.code, message);
    }
}
