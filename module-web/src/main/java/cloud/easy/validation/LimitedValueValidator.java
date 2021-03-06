package cloud.easy.validation;

import lombok.SneakyThrows;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

/**
 * LimitedValueValidator
 *
 * @author xu honglin
 * @date 2021/12/10 15:45
 */
public class LimitedValueValidator implements ConstraintValidator<LimitedValue, Object> {

    String[] values;

    @Override
    public void initialize(LimitedValue uniqueField) {
        values = uniqueField.values();
    }

    @SneakyThrows
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        //枚举类型不用校验
        if (value instanceof Enum) {
            return true;
        }
        //校验字符串类型
        List<String> list = Arrays.asList(values);
        return list.contains(String.valueOf(value));
    }
}
