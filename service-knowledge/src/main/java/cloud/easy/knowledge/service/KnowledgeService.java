package cloud.easy.knowledge.service;

import cloud.easy.kl.entity.KlKnowledgeNode;
import cloud.easy.knowledge.dto.KnowledgeDto;

import java.util.List;

/**
 * Title: KnowledgeService
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
public interface KnowledgeService {
    /**
     * 查询节点树
     *
     * @param parentId 节点
     * @return List<KnowledgeDto>
     */
    List<KnowledgeDto> tree(Long parentId);

    /**
     * 查询词云
     *
     * @param parentId 节点
     * @return List<KnowledgeDto>
     */
    List<KnowledgeDto> wordCloud(Long parentId);


    /**
     * 保存数据
     *
     * @param entityDto 节点及内容
     */
    void saveEntityDto(KnowledgeDto entityDto);

    /**
     * 查询数据
     *
     * @param id 主键
     * @return KnowledgeDto
     */
    KnowledgeDto queryEntityDto(Long id);

    /**
     * 分页查询
     *
     * @param entityDto 查询提交
     * @return PageQueryDto<KlKnowledgeNode>
     */
    List<KlKnowledgeNode> listEntity(KnowledgeDto entityDto);
}
