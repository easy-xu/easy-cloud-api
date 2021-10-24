package pro.simplecloud.knowledge.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pro.simplecloud.kl.entity.KlKnowledgeNode;

import java.util.List;

/**
 * Title: KnowledgeDto
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class KnowledgeDto extends KlKnowledgeNode {
    private List<KnowledgeDto> children;
    private int childCount;
    /**
     * 内容markdown
     */
    private String markdown;
}
