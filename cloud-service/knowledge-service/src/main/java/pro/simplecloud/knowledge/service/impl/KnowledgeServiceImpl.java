package pro.simplecloud.knowledge.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import pro.simplecloud.base.dto.PageDto;
import pro.simplecloud.base.dto.PageQueryDto;
import pro.simplecloud.kl.entity.KlKnowledgeContent;
import pro.simplecloud.kl.entity.KlKnowledgeNode;
import pro.simplecloud.kl.service.IKlKnowledgeContentService;
import pro.simplecloud.kl.service.IKlKnowledgeNodeService;
import pro.simplecloud.knowledge.dto.KnowledgeDto;
import pro.simplecloud.knowledge.service.KnowledgeService;
import pro.simplecloud.utils.BeanUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static pro.simplecloud.base.utils.BaseUtil.groupModeAuthQuery;
import static pro.simplecloud.base.utils.BaseUtil.notNull;

/**
 * Title: KnowledgeServiceImpl
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@Service
public class KnowledgeServiceImpl implements KnowledgeService {

    @Resource
    private IKlKnowledgeNodeService knowledgeNodeService;

    @Resource
    private IKlKnowledgeContentService knowledgeContentService;

    @Override
    public List<KnowledgeDto> tree(Long parentId) {
        parentId = parentId == null ? 0L : parentId;
        return getChildren(parentId);
    }

    @Override
    public List<KnowledgeDto> wordCloud(Long parentId) {
        parentId = parentId == null ? 0L : parentId;
        ArrayList<KnowledgeDto> knowledgeDtos = new ArrayList<>();
        getChildren(parentId, knowledgeDtos);
        return knowledgeDtos;
    }

    @Override
    public void saveEntityDto(KnowledgeDto entityDto) {
        KlKnowledgeNode klKnowledgeNode = new KlKnowledgeNode();
        BeanUtils.copy(entityDto, klKnowledgeNode);
        KlKnowledgeContent klKnowledgeContent = new KlKnowledgeContent();
        String markdown = entityDto.getMarkdown();
        if (markdown != null) {
            klKnowledgeContent.setId(entityDto.getContentId());
            klKnowledgeContent.setMarkdown(markdown);
            knowledgeContentService.saveOrUpdate(klKnowledgeContent);
            klKnowledgeNode.setContentId(klKnowledgeContent.getId());
        } else {
            klKnowledgeNode.setContentId(null);
        }
        knowledgeNodeService.saveOrUpdate(klKnowledgeNode);
    }

    @Override
    public KnowledgeDto queryEntityDto(Long id) {
        KnowledgeDto knowledgeDto = new KnowledgeDto();
        KlKnowledgeNode knowledgeNode = knowledgeNodeService.getById(id);
        BeanUtils.copy(knowledgeNode, knowledgeDto);
        Long contentId = knowledgeNode.getContentId();
        if (contentId != null) {
            KlKnowledgeContent knowledgeContent = knowledgeContentService.getById(contentId);
            if (knowledgeContent != null) {
                knowledgeDto.setMarkdown(knowledgeContent.getMarkdown());
            }
        }
        return knowledgeDto;
    }

    @Override
    public List<KlKnowledgeNode> listEntity(KnowledgeDto entityDto) {
        //查询条件
        QueryWrapper<KlKnowledgeNode> queryWrapper = Wrappers.query(entityDto);
        List<String> types = entityDto.getTypes();
        if (types != null) {
            queryWrapper.in("type", types);
        }
        return knowledgeNodeService.list(groupModeAuthQuery(queryWrapper));
    }

    private List<KnowledgeDto> getChildren(Long parentId) {
        KlKnowledgeNode node = new KlKnowledgeNode();
        node.setParentId(parentId);
        QueryWrapper<KlKnowledgeNode> query = Wrappers.query(node);
        //检查用户权限
        groupModeAuthQuery(query);
        query.orderByAsc("order_num");
        List<KlKnowledgeNode> children = knowledgeNodeService.list(query);
        return children.stream().map(item -> {
            KnowledgeDto knowledgeDto = new KnowledgeDto();
            BeanUtils.copy(item, knowledgeDto);
            List<KnowledgeDto> itemChildren = getChildren(item.getId());
            knowledgeDto.setChildren(itemChildren);
            return knowledgeDto;
        }).collect(Collectors.toList());
    }


    private List<KnowledgeDto> getChildren(Long parentId, List<KnowledgeDto> childrenList) {
        KlKnowledgeNode node = new KlKnowledgeNode();
        node.setParentId(parentId);
        QueryWrapper<KlKnowledgeNode> query = Wrappers.query(node);
        //检查用户权限
        groupModeAuthQuery(query);
        List<KlKnowledgeNode> children = knowledgeNodeService.list(query);
        return children.stream().map(item -> {
            KnowledgeDto knowledgeDto = new KnowledgeDto();
            BeanUtils.copy(item, knowledgeDto);
            childrenList.add(knowledgeDto);
            List<KnowledgeDto> itemChildren = getChildren(item.getId(), childrenList);
            //累计子元素个数
            int childCount = itemChildren.size();
            for (KnowledgeDto itemChild : itemChildren) {
                childCount = childCount + itemChild.getChildCount();
            }
            knowledgeDto.setChildCount(childCount);
            return knowledgeDto;
        }).collect(Collectors.toList());
    }
}
