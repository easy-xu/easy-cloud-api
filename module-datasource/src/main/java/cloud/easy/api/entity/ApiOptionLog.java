package cloud.easy.api.entity;

import cloud.easy.base.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 操作记录表
 * </p>
 *
 * @author Mybatis Plus
 * @since 2021-11-29
 */
@Getter
@Setter
@TableName("api_option_log")
public class ApiOptionLog extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户编号
     */
    private String userNo;

    /**
     * 设备编号
     */
    private String deviceNo;

    /**
     * 操作名称
     */
    private String optionName;


}
