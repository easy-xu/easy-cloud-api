package cloud.easy.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * OptionLog
 *
 * @author xu honglin
 * @date 2021/12/1 11:26
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Option {
    //描述
    String value();
    //菜单编号
    String menuCode() default "";
    //操作编号
    String optionCode() default "";
    //是否记录日志
    boolean optionLog() default true;
}
