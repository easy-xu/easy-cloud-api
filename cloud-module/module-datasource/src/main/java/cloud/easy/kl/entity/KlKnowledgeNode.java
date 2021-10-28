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
public class KlKnowledgeNode extends PrimaryDataEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 节点标题
     */
    private String name;

    /**
     * 父节点ID
     */
    private Long parentId;

    /**
     * 内容ID
     */
    private Long contentId;

    /**
     * 显示顺序
     */
    private Integer orderNum;

    /**
     * 节点类型（F目录 M菜单）
     */
    private String type;

    /**
     * 菜单状态（0显示 1隐藏）
     */
    private String visible;

    /**
     * 备注
     */
    private String remark;


}
