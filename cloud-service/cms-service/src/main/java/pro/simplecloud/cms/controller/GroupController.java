package pro.simplecloud.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.simplecloud.cms.entity.CmsGroup;
import pro.simplecloud.cms.service.ICmsGroupService;
import pro.simplecloud.base.controller.BaseController;
import pro.simplecloud.base.dto.PageQueryDto;
import pro.simplecloud.entity.ApiResponse;

/**
 * Title: GroupController
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@RestController
@RequestMapping("/api/cms/group")
public class GroupController extends BaseController<CmsGroup, ICmsGroupService> {


    @Autowired
    public GroupController(ICmsGroupService service) {
        super(service);
    }

    @Override
    @PostMapping("/query")
    public ApiResponse queryEntity(@RequestBody CmsGroup entity) {
        return super.queryEntity(entity);
    }

    @Override
    @PostMapping("/save")
    public ApiResponse saveEntity(@RequestBody CmsGroup entity) {
        return super.saveEntity(entity);
    }

    @Override
    @PostMapping("/delete")
    public ApiResponse deleteEntity(@RequestBody CmsGroup entity) {
        return super.deleteEntity(entity);
    }

    @Override
    @PostMapping("/page-list")
    public ApiResponse pageList(@RequestBody PageQueryDto<CmsGroup> pageQueryDto) {
        return super.pageList(pageQueryDto);
    }

    @Override
    @PostMapping("/list")
    public ApiResponse listEntity(@RequestBody CmsGroup entity) {
        return super.listEntity(entity);
    }

}
