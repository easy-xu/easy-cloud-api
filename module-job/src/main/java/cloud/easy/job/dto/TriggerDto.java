package cloud.easy.job.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * TriggerDto
 *
 * @author xu honglin
 * @date 2021/12/14 10:37
 */
@Data
public class TriggerDto implements Serializable {
    private String state;
    private Date startTime;
    private Date endTime;
    private Date previousFireTime;
    private Date  nextFireTime;
}
