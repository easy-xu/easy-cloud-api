package cloud.easy.sys.controller;

import cloud.easy.entity.ApiResponse;
import cloud.easy.base.dto.PrimaryKeyDto;
import cloud.easy.base.dto.PageQueryDto;
import cloud.easy.annotation.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import cloud.easy.sys.entity.SysOptionLog;
import cloud.easy.sys.service.ISysOptionLogService;
import cloud.easy.base.controller.BaseController;

import static cloud.easy.base.utils.BaseUtil.notRequireId;
import static cloud.easy.base.utils.BaseUtil.requireId;

/**
 * 操作记录控制器
 *
 * @author generator
 * @since 2021-12-11
 */
@RestController
@RequestMapping("/api/sys/optionlog")
@Api(tags = "操作记录接口")
public class SysOptionLogController extends BaseController<SysOptionLog, ISysOptionLogService> {

    @Autowired
    public SysOptionLogController(ISysOptionLogService service) {
        super(service);
    }

    @Override
    @Option(value = "操作记录详情", menuCode = "optionlog", optionCode = "query", optionLog = false)
    @PostMapping("/get")
    public ApiResponse getEntity(@Validated @RequestBody PrimaryKeyDto primaryKey) {
        return super.getEntity(primaryKey);
    }

    @Override
    @Option(value = "操作记录分页查询", menuCode = "optionlog", optionCode = "query", optionLog = false)
    @PostMapping("/page-list")
    public ApiResponse pageList(@RequestBody PageQueryDto<SysOptionLog> pageQueryDto) {
        return super.pageList(pageQueryDto);
    }

}

