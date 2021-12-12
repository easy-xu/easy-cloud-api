package cloud.easy.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/** 适用于固定选项内容校验
 * @author xuhonglin
 * @since 2021-12-10
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Constraint(validatedBy = {LimitedValueValidator.class})
public @interface LimitedValue {
	
	// 校验未通过时的返回信息
    String message() default "取值不正确";
    //字段名
    String[] values();
	//分组
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
