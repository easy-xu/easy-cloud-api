package cloud.easy.cms.entity;

import cloud.easy.base.entity.PrimaryDataEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 操作类型表
 * </p>
 *
 * @author Mybatis Plus
 * @since 2021-11-29
 */
@Getter
@Setter
@TableName("cms_option")
public class CmsOption extends PrimaryDataEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 操作名称
     */
    private String name;

    /**
     * 操作字符串
     */
    private String code;

    /**
     * 备注
     */
    private String remark;


}
