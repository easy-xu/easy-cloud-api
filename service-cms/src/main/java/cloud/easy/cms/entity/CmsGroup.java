package cloud.easy.cms.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import cloud.easy.base.entity.AuthEntity;

/**
 * 分组实体类
 *
 * @author generator
 * @since 2021-12-08
 */
@Data
@TableName("cms_group")
@ApiModel(value = "CmsGroup", description = "分组实体类")
@EqualsAndHashCode(callSuper = true)
public class CmsGroup extends AuthEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 分组名称
     */
    @ApiModelProperty("分组名称")
    private String name;
    /**
     * 分组字符串
     */
    @ApiModelProperty("分组字符串")
    private String code;
    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remark;

}
