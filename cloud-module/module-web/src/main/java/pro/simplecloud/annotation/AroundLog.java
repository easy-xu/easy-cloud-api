package pro.simplecloud.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Title: AroundLog
 * Description: 日志注解
 *
 * @author Xu Honglin
 * @version 1.0
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AroundLog {

    String apiCode() default "";

    String apiName() default "";
}
