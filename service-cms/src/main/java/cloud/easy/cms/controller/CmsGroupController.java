package cloud.easy.cms.controller;

import cloud.easy.entity.ApiResponse;
import cloud.easy.base.dto.PrimaryKeyDto;
import cloud.easy.base.dto.PageQueryDto;
import cloud.easy.annotation.OptionLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import cloud.easy.cms.entity.CmsGroup;
import cloud.easy.cms.service.ICmsGroupService;
import cloud.easy.base.controller.BaseController;

/**
 * 分组控制器
 *
 * @author generator
 * @since 2021-12-09
 */
@RestController
@RequestMapping("/api/cms/group")
@Api(tags = "分组接口")
public class CmsGroupController extends BaseController<CmsGroup, ICmsGroupService> {

    @Autowired
    public CmsGroupController(ICmsGroupService service) {
        super(service);
    }

    @Override
    @PostMapping("/get")
    public ApiResponse getEntity(@RequestBody PrimaryKeyDto primaryKey) {
        return super.getEntity(primaryKey);
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
    public ApiResponse deleteEntityById(@RequestBody PrimaryKeyDto primaryKey) {
        return super.deleteEntityById(primaryKey);
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

