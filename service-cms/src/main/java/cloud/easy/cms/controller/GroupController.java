package cloud.easy.cms.controller;

import cloud.easy.annotation.OptionLog;
import cloud.easy.base.controller.BaseController;
import cloud.easy.base.dto.PageQueryDto;
import cloud.easy.cms.entity.CmsGroup;
import cloud.easy.cms.service.ICmsGroupService;
import cloud.easy.entity.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @OptionLog("分组详情")
    @PostMapping("/query")
    public ApiResponse queryEntity(@RequestBody CmsGroup entity) {
        return super.queryEntity(entity);
    }

    @Override
    @OptionLog("分组保存")
    @PostMapping("/save")
    public ApiResponse saveEntity(@RequestBody CmsGroup entity) {
        return super.saveEntity(entity);
    }

    @Override
    @OptionLog("分组删除")
    @PostMapping("/delete")
    public ApiResponse deleteEntity(@RequestBody CmsGroup entity) {
        return super.deleteEntity(entity);
    }

    @Override
    @OptionLog("分组查询")
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
