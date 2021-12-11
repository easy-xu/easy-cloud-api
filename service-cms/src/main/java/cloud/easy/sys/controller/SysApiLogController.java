package cloud.easy.sys.controller;

import cloud.easy.entity.ApiResponse;
import cloud.easy.base.dto.PrimaryKeyDto;
import cloud.easy.base.dto.PageQueryDto;
import cloud.easy.annotation.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import cloud.easy.sys.entity.SysApiLog;
import cloud.easy.sys.service.ISysApiLogService;
import cloud.easy.base.controller.BaseController;

import static cloud.easy.base.utils.BaseUtil.notRequireId;
import static cloud.easy.base.utils.BaseUtil.requireId;

/**
 * 接口日志控制器
 *
 * @author generator
 * @since 2021-12-11
 */
@RestController
@RequestMapping("/api/sys/apilog")
@Api(tags = "接口日志接口")
public class SysApiLogController extends BaseController<SysApiLog, ISysApiLogService> {

    @Autowired
    public SysApiLogController(ISysApiLogService service) {
        super(service);
    }

    @Override
    @Option(value = "接口日志详情", menuCode = "apilog", optionCode = "query", optionLog = false)
    @PostMapping("/get")
    public ApiResponse getEntity(@Validated @RequestBody PrimaryKeyDto primaryKey) {
        return super.getEntity(primaryKey);
    }

    @Override
    @Option(value = "接口日志分页查询", menuCode = "apilog", optionCode = "query", optionLog = false)
    @PostMapping("/page-list")
    public ApiResponse pageList(@RequestBody PageQueryDto<SysApiLog> pageQueryDto) {
        return super.pageList(pageQueryDto);
    }

}

