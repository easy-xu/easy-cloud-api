package cloud.easy.job.controller;

import cloud.easy.entity.ApiResponse;
import cloud.easy.base.dto.PrimaryKeyDto;
import cloud.easy.base.dto.PageQueryDto;
import cloud.easy.annotation.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import cloud.easy.job.entity.JobLog;
import cloud.easy.job.service.IJobLogService;
import cloud.easy.base.controller.BaseController;

import static cloud.easy.base.utils.BaseUtil.notRequireId;
import static cloud.easy.base.utils.BaseUtil.requireId;

/**
 * 任务日志控制器
 *
 * @author generator
 * @since 2021-12-11
 */
@RestController
@RequestMapping("/api/job/log")
@Api(tags = "任务日志接口")
public class JobLogController extends BaseController<JobLog, IJobLogService> {

    @Autowired
    public JobLogController(IJobLogService service) {
        super(service);
    }

    @Override
    @Option(value = "任务日志详情", menuCode = "config", optionCode = "query", optionLog = false)
    @PostMapping("/get")
    public ApiResponse getEntity(@Validated @RequestBody PrimaryKeyDto primaryKey) {
        return super.getEntity(primaryKey);
    }

    @Override
    @Option(value = "任务日志分页查询", menuCode = "config", optionCode = "query", optionLog = false)
    @PostMapping("/page-list")
    public ApiResponse pageList(@RequestBody PageQueryDto<JobLog> pageQueryDto) {
        return super.pageList(pageQueryDto);
    }

    @Override
    @Option(value = "任务日志列表查询", menuCode = "config", optionCode = "query", optionLog = false)
    @PostMapping("/list")
    public ApiResponse listEntity(@RequestBody JobLog entity) {
        return super.listEntity(entity);
    }

    @Override
    @Option(value = "任务日志计数", menuCode = "config", optionCode = "query", optionLog = false)
    @PostMapping("/count")
    public ApiResponse countEntity(@RequestBody JobLog entity) {
        return super.countEntity(entity);
    }

}

