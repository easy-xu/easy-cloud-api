package cloud.easy.job.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * JobData
 *
 * @author xu honglin
 * @date 2021/12/13 16:55
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SpringJobData extends JobData {

    /**
     * 服务名
     */
    private String beanName;
    /**
     * 执行参数
     */
    private String params;

}
