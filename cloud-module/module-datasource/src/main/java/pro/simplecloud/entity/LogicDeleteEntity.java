package pro.simplecloud.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Title: LogicDeleteEntity
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class LogicDeleteEntity extends BaseEntity {

    /**
     * 删除标志（0正常 1停用）
     */
    @JsonIgnore
    @TableLogic
    private String deleted;

}
