package cloud.easy.cms.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import cloud.easy.base.entity.AuthEntity;

/**
 * 操作类型实体类
 *
 * @author generator
 * @since 2021-12-08
 */
@Data
@TableName("cms_option")
@ApiModel(value = "CmsOption", description = "操作类型实体类")
@EqualsAndHashCode(callSuper = true)
public class CmsOption extends AuthEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 操作名称
     */
    @ApiModelProperty("操作名称")
    private String name;
    /**
     * 操作字符串
     */
    @ApiModelProperty("操作字符串")
    private String code;
    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remark;

}
