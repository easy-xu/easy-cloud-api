package cloud.easy.api.entity;

import cloud.easy.base.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 接口日志表
 * </p>
 *
 * @author Mybatis Plus
 * @since 2021-11-29
 */
@Getter
@Setter
@TableName("api_log")
public class ApiLog extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 请求流水号
     */
    private String requestId;

    /**
     * 用户编号
     */
    private String userNo;

    /**
     * 设备编号
     */
    private String deviceNo;

    /**
     * 接口编码
     */
    private String requestCode;

    /**
     * 接口地址
     */
    private String requestPath;

    /**
     * 业务编号
     */
    private String businessNo;

    /**
     * 系统编号
     */
    private String sysCode;

    /**
     * 接口耗时
     */
    private Long usedTime;

    /**
     * 结果编码
     */
    private Long responseCode;

    /**
     * 结果描述
     */
    private String responseMessage;


}
