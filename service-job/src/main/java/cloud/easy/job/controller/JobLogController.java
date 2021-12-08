package cloud.easy.job.controller;

import cloud.easy.base.controller.BaseController;
import cloud.easy.base.dto.PageQueryDto;
import cloud.easy.base.dto.PrimaryKeyDto;
import cloud.easy.entity.ApiResponse;
import cloud.easy.job.entity.JobLog;
import cloud.easy.job.service.IJobLogService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 任务日志控制器
 *
 * @author xu honglin
 * @since 2021-12-08
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
    @PostMapping("/get")
    public ApiResponse getEntity(@RequestBody PrimaryKeyDto primaryKey) {
        return super.getEntity(primaryKey);
    }

    @Override
    @PostMapping("/page-list")
    public ApiResponse pageList(@RequestBody PageQueryDto<JobLog> pageQueryDto) {
        return super.pageList(pageQueryDto);
    }

    @Override
    @PostMapping("/list")
    public ApiResponse listEntity(@RequestBody JobLog entity) {
        return super.listEntity(entity);
    }

}

