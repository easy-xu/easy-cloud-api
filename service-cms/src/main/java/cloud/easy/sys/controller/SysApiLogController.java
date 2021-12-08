package cloud.easy.sys.controller;

import cloud.easy.entity.ApiResponse;
import cloud.easy.base.dto.PrimaryKeyDto;
import cloud.easy.base.dto.PageQueryDto;
import cloud.easy.annotation.OptionLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import cloud.easy.sys.entity.SysApiLog;
import cloud.easy.sys.service.ISysApiLogService;
import cloud.easy.base.controller.BaseController;

/**
 * 接口日志控制器
 *
 * @author xu honglin
 * @since 2021-12-08
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
    @PostMapping("/get")
    public ApiResponse getEntity(@RequestBody PrimaryKeyDto primaryKey) {
        return super.getEntity(primaryKey);
    }

    @Override
    @PostMapping("/page-list")
    public ApiResponse pageList(@RequestBody PageQueryDto<SysApiLog> pageQueryDto) {
        return super.pageList(pageQueryDto);
    }

}

