package cloud.easy.knowledge.controller;

import cloud.easy.base.dto.PageQueryDto;
import cloud.easy.entity.ApiResponse;
import cloud.easy.entity.HttpResponse;
import cloud.easy.kl.entity.KlKnowledgeNode;
import cloud.easy.kl.service.IKlKnowledgeNodeService;
import cloud.easy.knowledge.dto.KnowledgeDto;
import cloud.easy.knowledge.service.KnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

import static cloud.easy.base.utils.BaseUtil.notNull;
import static cloud.easy.base.utils.BaseUtil.requireId;

/**
 * Title: NodeController
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@RestController
@RequestMapping("/api/knowledge/node")
public class NodeController extends BaseController<KlKnowledgeNode, IKlKnowledgeNodeService> {

    @Resource
    private KnowledgeService knowledgeService;

    @Autowired
    public NodeController(IKlKnowledgeNodeService service) {
        super(service);
    }

    @PostMapping("/tree")
    public ApiResponse tree(@RequestBody KlKnowledgeNode knowledgeNode) {
        List<KnowledgeDto> treeNode = knowledgeService.tree(notNull(knowledgeNode).getParentId());
        return HttpResponse.ok(treeNode);
    }

    @PostMapping("/word-cloud")
    public ApiResponse wordCloud(@RequestBody KlKnowledgeNode knowledgeNode) {
        List<KnowledgeDto> wordCloud = knowledgeService.wordCloud(notNull(knowledgeNode).getParentId());
        return HttpResponse.ok(wordCloud);
    }

    @PostMapping("/query")
    public ApiResponse queryEntity(@RequestBody KnowledgeDto entityDto) {
        entityDto = knowledgeService.queryEntityDto(requireId(entityDto));
        return HttpResponse.ok(entityDto);
    }

    @PostMapping("/save")
    public ApiResponse saveEntity(@RequestBody KnowledgeDto entityDto) {
        knowledgeService.saveEntityDto(notNull(entityDto));
        return HttpResponse.ok();
    }

    @Override
    @PostMapping("/delete")
    public ApiResponse deleteEntity(@RequestBody KlKnowledgeNode entity) {
        return super.deleteEntity(entity);
    }

    @Override
    @PostMapping("/page-list")
    public ApiResponse pageList(@RequestBody PageQueryDto<KlKnowledgeNode> pageQueryDto) {
        return super.pageList(pageQueryDto);
    }

    @PostMapping("/list")
    public ApiResponse listEntity(@RequestBody KnowledgeDto entityDto) {
        List<KlKnowledgeNode> list = knowledgeService.listEntity(entityDto);
        return HttpResponse.ok(list);
    }

}
