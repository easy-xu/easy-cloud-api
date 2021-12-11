package cloud.easy.cms.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import cloud.easy.base.entity.AuthEntity;
import javax.validation.constraints.NotNull;
import cloud.easy.validation.UniqueField;
import org.hibernate.validator.constraints.Length;

/**
 * 操作类型实体类
 *
 * @author generator
 * @since 2021-12-11
 */
@Data
@TableName("cms_option")
@ApiModel(value = "CmsOption", description = "操作类型实体类")
@UniqueField(field = "code", message = "操作字符串已存在")
@EqualsAndHashCode(callSuper = true)
public class CmsOption extends AuthEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 操作名称
     */
    @NotNull(message = "操作名称不能为空")
    @Length(max = 50, message = "操作名称长度不能超过50")
    @ApiModelProperty("操作名称")
    private String name;
    /**
     * 操作字符串
     */
    @NotNull(message = "操作字符串不能为空")
    @Length(max = 100, message = "操作字符串长度不能超过100")
    @ApiModelProperty("操作字符串")
    private String code;
    /**
     * 备注
     */
    @Length(max = 500, message = "备注长度不能超过500")
    @ApiModelProperty("备注")
    private String remark;

}
