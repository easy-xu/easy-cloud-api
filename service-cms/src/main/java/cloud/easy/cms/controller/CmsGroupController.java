package cloud.easy.cms.controller;

import cloud.easy.entity.ApiResponse;
import cloud.easy.base.dto.PrimaryKeyDto;
import cloud.easy.base.dto.PageQueryDto;
import cloud.easy.annotation.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import cloud.easy.cms.entity.CmsGroup;
import cloud.easy.cms.service.ICmsGroupService;
import cloud.easy.base.controller.BaseController;

import static cloud.easy.base.utils.BaseUtil.notRequireId;
import static cloud.easy.base.utils.BaseUtil.requireId;

/**
 * 分组控制器
 *
 * @author generator
 * @since 2021-12-11
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
    @Option(value = "分组详情", menuCode = "group", optionCode = "query", optionLog = false)
    @PostMapping("/get")
    public ApiResponse getEntity(@Validated @RequestBody PrimaryKeyDto primaryKey) {
        return super.getEntity(primaryKey);
    }

    @Option(value = "分组新增", menuCode = "group", optionCode = "add")
    @PostMapping("/add")
    public ApiResponse addEntity(@Validated @RequestBody CmsGroup entity) {
        notRequireId(entity);
        return super.saveEntity(entity);
    }

    @Option(value = "分组更新", menuCode = "group", optionCode = "edit")
    @PostMapping("/edit")
    public ApiResponse editEntity(@Validated @RequestBody CmsGroup entity) {
        requireId(entity);
        return super.saveEntity(entity);
    }

    @Override
    @Option(value = "分组删除", menuCode = "group", optionCode = "delete")
    @PostMapping("/delete")
    public ApiResponse deleteEntityById(@Validated @RequestBody PrimaryKeyDto primaryKey) {
        return super.deleteEntityById(primaryKey);
    }

    @Override
    @Option(value = "分组分页查询", menuCode = "group", optionCode = "query", optionLog = false)
    @PostMapping("/page-list")
    public ApiResponse pageList(@RequestBody PageQueryDto<CmsGroup> pageQueryDto) {
        return super.pageList(pageQueryDto);
    }

    @Override
    @Option(value = "分组列表查询", menuCode = "group", optionCode = "query", optionLog = false)
    @PostMapping("/list")
    public ApiResponse listEntity(@RequestBody CmsGroup entity) {
        return super.listEntity(entity);
    }

    @Override
    @Option(value = "分组计数", menuCode = "group", optionCode = "query", optionLog = false)
    @PostMapping("/count")
    public ApiResponse countEntity(@RequestBody CmsGroup entity) {
        return super.countEntity(entity);
    }

}

