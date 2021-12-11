package cloud.easy.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 适用于固定选项内容校验
 * @author xuhonglin
 * @since 2021-12-10
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Constraint(validatedBy = {UniqueFieldValidator.class})
public @interface TableMappingValue {
	
	// 校验未通过时的返回信息
    String message() default "取值不正确";
    //类型
    String codeType();
	//分组
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
