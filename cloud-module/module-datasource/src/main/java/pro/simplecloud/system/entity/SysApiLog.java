package pro.simplecloud.system.entity;

import pro.simplecloud.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 接口日志表
 * </p>
 *
 * @author Generator
 * @since 2021-09-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysApiLog extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 请求流水号
     */
    private String requestId;

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

    /**
     * 删除标志（0正常 1停用）
     */
    private String status;


}
