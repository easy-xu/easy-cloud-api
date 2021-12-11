package cloud.easy.cms.controller;

import cloud.easy.entity.ApiResponse;
import cloud.easy.base.dto.PrimaryKeyDto;
import cloud.easy.base.dto.PageQueryDto;
import cloud.easy.annotation.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import cloud.easy.cms.entity.CmsOption;
import cloud.easy.cms.service.ICmsOptionService;
import cloud.easy.base.controller.BaseController;

import static cloud.easy.base.utils.BaseUtil.notRequireId;
import static cloud.easy.base.utils.BaseUtil.requireId;

/**
 * 操作类型控制器
 *
 * @author generator
 * @since 2021-12-11
 */
@RestController
@RequestMapping("/api/cms/option")
@Api(tags = "操作类型接口")
public class CmsOptionController extends BaseController<CmsOption, ICmsOptionService> {

    @Autowired
    public CmsOptionController(ICmsOptionService service) {
        super(service);
    }

    @Override
    @Option(value = "操作类型详情", menuCode = "option", optionCode = "query", optionLog = false)
    @PostMapping("/get")
    public ApiResponse getEntity(@Validated @RequestBody PrimaryKeyDto primaryKey) {
        return super.getEntity(primaryKey);
    }

    @Option(value = "操作类型新增", menuCode = "option", optionCode = "add")
    @PostMapping("/add")
    public ApiResponse addEntity(@Validated @RequestBody CmsOption entity) {
        notRequireId(entity);
        return super.saveEntity(entity);
    }

    @Option(value = "操作类型更新", menuCode = "option", optionCode = "edit")
    @PostMapping("/edit")
    public ApiResponse editEntity(@Validated @RequestBody CmsOption entity) {
        requireId(entity);
        return super.saveEntity(entity);
    }

    @Override
    @Option(value = "操作类型删除", menuCode = "option", optionCode = "delete")
    @PostMapping("/delete")
    public ApiResponse deleteEntityById(@Validated @RequestBody PrimaryKeyDto primaryKey) {
        return super.deleteEntityById(primaryKey);
    }

    @Override
    @Option(value = "操作类型分页查询", menuCode = "option", optionCode = "query", optionLog = false)
    @PostMapping("/page-list")
    public ApiResponse pageList(@RequestBody PageQueryDto<CmsOption> pageQueryDto) {
        return super.pageList(pageQueryDto);
    }

    @Override
    @Option(value = "操作类型列表查询", menuCode = "option", optionCode = "query", optionLog = false)
    @PostMapping("/list")
    public ApiResponse listEntity(@RequestBody CmsOption entity) {
        return super.listEntity(entity);
    }

    @Override
    @Option(value = "操作类型计数", menuCode = "option", optionCode = "query", optionLog = false)
    @PostMapping("/count")
    public ApiResponse countEntity(@RequestBody CmsOption entity) {
        return super.countEntity(entity);
    }

}

