package cloud.easy.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * UniqueFields
 *
 * @author xu honglin
 * @date 2021/12/11 18:17
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueFields {

    UniqueField[] value();

}
