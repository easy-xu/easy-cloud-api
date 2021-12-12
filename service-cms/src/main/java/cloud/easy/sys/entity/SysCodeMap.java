package cloud.easy.sys.entity;

import cloud.easy.base.entity.AuthEntity;
import cloud.easy.validation.UniqueField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 字典实体类
 *
 * @author generator
 * @since 2021-12-12
 */
@Data
@TableName("sys_code_map")
@ApiModel(value = "SysCodeMap", description = "字典实体类")
@UniqueField(field = {"codeType", "codeValue"}, message = "字典分类取值已存在")
@EqualsAndHashCode(callSuper = true)
public class SysCodeMap extends AuthEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 字典名称
     */
    @NotNull(message = "字典名称不能为空")
    @Length(max = 100, message = "字典名称长度不能超过100")
    @ApiModelProperty("字典名称")
    private String codeName;
    /**
     * 字典取值
     */
    @NotNull(message = "字典取值不能为空")
    @Length(max = 100, message = "字典取值长度不能超过100")
    @ApiModelProperty("字典取值")
    private String codeValue;
    /**
     * 字典分类
     */
    @NotNull(message = "字典分类不能为空")
    @Length(max = 30, message = "字典分类长度不能超过30")
    @ApiModelProperty("字典分类")
    private String codeType;
    /**
     * 显示顺序
     */
    @ApiModelProperty("显示顺序")
    private Integer orderNum;
    /**
     * 备注
     */
    @Length(max = 500, message = "备注长度不能超过500")
    @ApiModelProperty("备注")
    private String remark;

}
