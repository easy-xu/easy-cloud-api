package cloud.easy.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Title: BeanUtils
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 * @date 2021/7/2 18:24 首次创建
 * @date 2021/7/2 18:24 最后修改
 */
public class BeanUtils {

    public static void copy(Object source, Object target) {
        BeanCopier beanCopier = BeanCopier.create(source.getClass(), target.getClass(), false);
        beanCopier.copy(source, target, null);
    }

    public static void copyNotNull(Object source, Object target) {
        BeanCopier beanCopier = BeanCopier.create(source.getClass(), target.getClass(), true);
        beanCopier.copy(source, target, new NotNullConverter(target));
    }

    /**
     * @param source  源对象
     * @param target  目标对象
     * @param mapping <filed, <sourceValue, targetValue>>
     */
    public static void copy(Object source, Object target, Map<String, Map<String, String>> mapping) {
        BeanCopier beanCopier = BeanCopier.create(source.getClass(), target.getClass(), true);
        beanCopier.copy(source, target, new StringMappingConverter(mapping));
    }

    /**
     * 字符串映射
     */
    @Slf4j
    static class StringMappingConverter implements Converter {

        private Map<String, Map<String, String>> mappingField;

        StringMappingConverter(Map<String, Map<String, String>> mappingField) {
            this.mappingField = mappingField;
        }

        @Override
        public Object convert(Object value, Class target, Object content) {
            if (value == null) {
                return null;
            }
            //移除set
            int setLength = 3;
            String field = content.toString().substring(setLength);
            Map<String, String> mapping = mappingField.get(field);
            if (mapping != null) {
                log.info("field: {}", field);
                log.info("value: {}", value);
                String mappedValue = mapping.get(value.toString());
                log.info("mapped: {}", mappedValue);
                return mappedValue;
            } else {
                return value;
            }
        }
    }

    /**
     * value非空才复制
     */
    static class NotNullConverter implements Converter {
        private final Object target;

        public NotNullConverter(Object target) {
            this.target = target;
        }

        @Override
        public Object convert(Object value, Class field, Object setMethod) {
            if (value != null) {
                return value;
            }
            try {
                Class<?> targetClass = target.getClass();
                Method getMethod = targetClass.getMethod(setMethod.toString().replaceFirst("set", "get"));
                return getMethod.invoke(target);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}
