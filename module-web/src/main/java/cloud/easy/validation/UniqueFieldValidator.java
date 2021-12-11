package cloud.easy.validation;

import cloud.easy.base.entity.BaseEntity;
import cloud.easy.utils.ReflectUtil;
import cloud.easy.utils.SpringUtils;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import lombok.SneakyThrows;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * UniqueCodeValidator
 *
 * @author xu honglin
 * @date 2021/12/10 15:45
 */
public class UniqueFieldValidator implements ConstraintValidator<UniqueField, BaseEntity> {

    String[] fields;

    @Override
    public void initialize(UniqueField uniqueField) {
        fields = uniqueField.field();
    }

    @SneakyThrows
    @Override
    public boolean isValid(BaseEntity entity, ConstraintValidatorContext context) {
        Class<? extends BaseEntity> clazz = entity.getClass();
        String entityName = clazz.getCanonicalName();
        String serviceName = entityName.replace(".entity.", ".service.I") + "Service";
        Object service = SpringUtils.getBean(Class.forName(serviceName));
        if (!(service instanceof IService)) {
            return true;
        }
        IService<?> iService = (IService<?>) service;
        QueryChainWrapper<?> wrapper = iService.query();
        for (String fieldName : fields) {
            Object fieldValue = ReflectUtil.getValue(entity, fieldName);
            wrapper.eq(fieldName, fieldValue);
        }
        Long id = entity.getId();
        if (id != null) {
            wrapper.ne("id", id);
        }
        long count = wrapper.count();
        return count == 0;
    }
}
