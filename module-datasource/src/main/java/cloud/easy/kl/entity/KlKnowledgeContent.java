package cloud.easy.kl.entity;

import cloud.easy.base.entity.PrimaryDataEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 知识图谱节点表
 * </p>
 *
 * @author Generator
 * @since 2021-10-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class KlKnowledgeContent extends PrimaryDataEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 内容markdown
     */
    private String markdown;

    /**
     * 备注
     */
    private String remark;


}
