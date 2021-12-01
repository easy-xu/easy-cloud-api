package cloud.easy.cms.entity;

import cloud.easy.base.entity.AuthDataEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 分组表
 * </p>
 *
 * @author Mybatis Plus
 * @since 2021-11-29
 */
@Getter
@Setter
@TableName("cms_group")
public class CmsGroup extends AuthDataEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 分组名称
     */
    private String name;

    /**
     * 分组字符串
     */
    private String code;

    /**
     * 备注
     */
    private String remark;


}
