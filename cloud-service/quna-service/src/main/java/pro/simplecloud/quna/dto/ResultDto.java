package pro.simplecloud.quna.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * Title: ResultDto
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@Data
public class ResultDto {
    /**
     * 结果ID
     */
    private Long id;

    /**
     * 问卷回答ID
     */
    private Long answerId;

    /**
     * 结果ID
     */
    private Long resultId;

    /**
     * 分值
     */
    private Long score;

    /**
     * 标题
     */
    private String title;

    /**
     * 结果描述文本
     */
    private String text;
}
