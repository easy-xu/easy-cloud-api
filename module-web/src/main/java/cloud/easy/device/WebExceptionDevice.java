package cloud.easy.device;

import cloud.easy.entity.ApiResponse;
import cloud.easy.entity.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * WebExceptionDevice
 *
 * @author xu honglin
 * @date 2021/12/10 14:09
 */
@Slf4j
@RestControllerAdvice
public class WebExceptionDevice {

    @ExceptionHandler({BindException.class})
    public ApiResponse validationException(BindException exception) {
        List<ObjectError> errors = exception.getAllErrors();
        if (!CollectionUtils.isEmpty(errors)) {
            StringBuilder builder = new StringBuilder();
            errors.forEach(e -> builder.append(e.getDefaultMessage()).append(","));
            String message = builder.deleteCharAt(builder.length()-1).toString();
            log.error("参数校验异常:{}", message);
            return HttpResponse.reject(message);
        }
        return HttpResponse.error(500, exception.getLocalizedMessage());
    }

}
