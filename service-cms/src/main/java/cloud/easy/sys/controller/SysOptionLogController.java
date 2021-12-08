package cloud.easy.sys.controller;

import cloud.easy.entity.ApiResponse;
import cloud.easy.base.dto.PrimaryKeyDto;
import cloud.easy.base.dto.PageQueryDto;
import cloud.easy.annotation.OptionLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import cloud.easy.sys.entity.SysOptionLog;
import cloud.easy.sys.service.ISysOptionLogService;
import cloud.easy.base.controller.BaseController;

/**
 * 操作记录控制器
 *
 * @author xu honglin
 * @since 2021-12-08
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
    @PostMapping("/get")
    public ApiResponse getEntity(@RequestBody PrimaryKeyDto primaryKey) {
        return super.getEntity(primaryKey);
    }

    @Override
    @PostMapping("/page-list")
    public ApiResponse pageList(@RequestBody PageQueryDto<SysOptionLog> pageQueryDto) {
        return super.pageList(pageQueryDto);
    }

}

