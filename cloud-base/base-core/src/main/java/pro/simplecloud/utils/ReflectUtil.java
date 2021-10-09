package pro.simplecloud.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Title: ReflectUtil
 * Description: 反射调用工具类
 *
 * @author Xu Honglin
 * @version 1.0
 */
public class ReflectUtil {

    /**
     * 缓存类属性信息
     */
    private static Map<String, Field> fieldCash = new HashMap<>();

    private ReflectUtil() {
    }

    /**
     * 反射set方法赋值，可以赋值父类属性
     *
     * @param instance
     * @param value
     * @param <T>
     */
    public static <T> void setValue(T instance, String fieldName, Object value) throws ReflectiveOperationException {
        Class<?> aClass = instance.getClass();
        Field field = getField(aClass, fieldName);
        //拼接set方法名
        String setMethodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        Method method = aClass.getMethod(setMethodName, field.getType());
        method.invoke(instance, value);
    }

    public static <T> Object getValue(T instance, String fieldName) throws ReflectiveOperationException {
        Class<?> aClass = instance.getClass();
        //拼接get方法名
        String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        Method method = aClass.getMethod(getMethodName);
        return method.invoke(instance);
    }

    /**
     * 获取包括父指定属性
     *
     * @param tClass
     * @return
     */
    private static Field getField(Class<?> tClass, String fieldName) throws NoSuchFieldException {
        String key = tClass.getName() + "#" + fieldName;
        Field field = null;
        if (fieldCash.containsKey(key)) {
            field = fieldCash.get(key);
        } else {
            synchronized (ReflectUtil.class) {
                if (fieldCash.containsKey(key)) {
                    field = fieldCash.get(key);
                } else {
                    field = tClass.getDeclaredField(fieldName);
                    fieldCash.put(key, field);
                }
            }
        }
        return field;
    }
}
