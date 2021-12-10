package cloud.easy.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/** 适用于单一字段的唯一性校验
 * @author xuhonglin
 * @since 2021-12-10
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
@Constraint(validatedBy = {UniqueColumnValidator.class})
public @interface UniqueColumn {
	
	// 校验未通过时的返回信息
    String message() default "违反唯一约束";
    //表名
    String table();
    //字段名
    String column();
	//分组
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
