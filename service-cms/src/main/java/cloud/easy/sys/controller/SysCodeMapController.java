package cloud.easy.sys.controller;

import cloud.easy.entity.ApiResponse;
import cloud.easy.base.dto.PrimaryKeyDto;
import cloud.easy.base.dto.PageQueryDto;
import cloud.easy.annotation.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import cloud.easy.sys.entity.SysCodeMap;
import cloud.easy.sys.service.ISysCodeMapService;
import cloud.easy.base.controller.BaseController;

import static cloud.easy.base.utils.BaseUtil.notRequireId;
import static cloud.easy.base.utils.BaseUtil.requireId;

/**
 * 字典控制器
 *
 * @author generator
 * @since 2021-12-12
 */
@RestController
@RequestMapping("/api/sys/codemap")
@Api(tags = "字典接口")
public class SysCodeMapController extends BaseController<SysCodeMap, ISysCodeMapService> {

    @Autowired
    public SysCodeMapController(ISysCodeMapService service) {
        super(service);
    }

    @Override
    @Option(value = "字典详情", menuCode = "codemap", optionCode = "query", optionLog = false)
    @PostMapping("/get")
    public ApiResponse getEntity(@Validated @RequestBody PrimaryKeyDto primaryKey) {
        return super.getEntity(primaryKey);
    }

    @Option(value = "字典新增", menuCode = "codemap", optionCode = "add")
    @PostMapping("/add")
    public ApiResponse addEntity(@Validated @RequestBody SysCodeMap entity) {
        notRequireId(entity);
        return super.saveEntity(entity);
    }

    @Option(value = "字典更新", menuCode = "codemap", optionCode = "edit")
    @PostMapping("/edit")
    public ApiResponse editEntity(@Validated @RequestBody SysCodeMap entity) {
        requireId(entity);
        return super.saveEntity(entity);
    }

    @Override
    @Option(value = "字典删除", menuCode = "codemap", optionCode = "delete")
    @PostMapping("/delete")
    public ApiResponse deleteEntityById(@Validated @RequestBody PrimaryKeyDto primaryKey) {
        return super.deleteEntityById(primaryKey);
    }

    @Override
    @Option(value = "字典分页查询", menuCode = "codemap", optionCode = "query", optionLog = false)
    @PostMapping("/page-list")
    public ApiResponse pageList(@RequestBody PageQueryDto<SysCodeMap> pageQueryDto) {
        return super.pageList(pageQueryDto);
    }

    @Override
    @Option(value = "字典列表查询", menuCode = "codemap", optionCode = "query", optionLog = false)
    @PostMapping("/list")
    public ApiResponse listEntity(@RequestBody SysCodeMap entity) {
        return super.listEntity(entity);
    }

    @Override
    @Option(value = "字典计数", menuCode = "codemap", optionCode = "query", optionLog = false)
    @PostMapping("/count")
    public ApiResponse countEntity(@RequestBody SysCodeMap entity) {
        return super.countEntity(entity);
    }

}

