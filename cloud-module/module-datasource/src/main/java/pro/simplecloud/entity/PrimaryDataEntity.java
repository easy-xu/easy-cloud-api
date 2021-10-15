package pro.simplecloud.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Title: PrimaryDataEntity
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PrimaryDataEntity extends BaseEntity {

    /**
     * 删除标志（0正常 1停用）
     */
    @TableLogic
    private String deleted;

    /**
     * 数据分组
     */
    private String dataGroup;

    /**
     * 数据权限
     */
    private String dataMode;

}
