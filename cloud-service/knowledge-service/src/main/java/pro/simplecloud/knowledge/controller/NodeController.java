package pro.simplecloud.knowledge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.simplecloud.base.controller.BaseController;
import pro.simplecloud.base.dto.PageQueryDto;
import pro.simplecloud.entity.ApiResponse;
import pro.simplecloud.entity.HttpResponse;
import pro.simplecloud.kl.entity.KlKnowledgeNode;
import pro.simplecloud.kl.service.IKlKnowledgeNodeService;
import pro.simplecloud.knowledge.dto.KnowledgeDto;
import pro.simplecloud.knowledge.service.KnowledgeService;

import javax.annotation.Resource;
import java.util.List;

import static pro.simplecloud.base.utils.BaseUtil.notNull;
import static pro.simplecloud.base.utils.BaseUtil.requireId;

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
        entityDto= knowledgeService.queryEntityDto(requireId(entityDto));
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
