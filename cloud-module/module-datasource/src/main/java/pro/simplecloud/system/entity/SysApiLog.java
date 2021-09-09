package pro.simplecloud.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 接口日志表
 * </p>
 *
 * @author Generator
 * @since 2021-09-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysApiLog implements Serializable {

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
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 更新者
     */
    private String updateBy;


}
