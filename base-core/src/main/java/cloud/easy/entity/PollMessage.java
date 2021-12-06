package cloud.easy.entity;

import lombok.Data;

/**
 * PullMessage
 *
 * @author xu honglin
 * @date 2021/12/3 21:20
 */
@Data
public class PollMessage {
    private String userNo;
    private Integer type;
    private String content;
    private String version;
}
