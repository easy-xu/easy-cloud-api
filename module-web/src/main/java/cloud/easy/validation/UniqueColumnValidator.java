package cloud.easy.validation;

import cloud.easy.base.mapper.BaseMapperCust;
import cloud.easy.utils.SpringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * UniqueCodeValidator
 *
 * @author xu honglin
 * @date 2021/12/10 15:45
 */
public class UniqueColumnValidator implements ConstraintValidator<UniqueColumn, String> {

    String table;
    String column;
    BaseMapperCust mapperCust;

    @Override
    public void initialize(UniqueColumn uniqueColumn) {
        table = uniqueColumn.table();
        column = uniqueColumn.column();
        mapperCust = SpringUtils.getBean(BaseMapperCust.class);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        //为空不校验
        if (value != null) {
            return mapperCust.countValue(table, column, value) == 0;
        }
        return true;
    }
}
