package cloud.easy.job.controller;

import cloud.easy.entity.ApiResponse;
import cloud.easy.base.dto.PageQueryDto;
import cloud.easy.annotation.OptionLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cloud.easy.job.entity.JobLog;
import cloud.easy.job.service.IJobLogService;
import cloud.easy.base.controller.BaseController;

/**
 * 任务日志控制器
 *
 * @author xu honglin
 * @since 2021-12-06
 */
@RestController
@RequestMapping("/api/job/log")
public class JobLogController extends BaseController<JobLog, IJobLogService> {

    @Autowired
    public JobLogController(IJobLogService service) {
        super(service);
    }

    @Override
    @PostMapping("/query")
    public ApiResponse queryEntity(@RequestBody JobLog entity) {
        return super.queryEntity(entity);
    }

    @Override
    @OptionLog("任务日志保存")
    @PostMapping("/save")
    public ApiResponse saveEntity(@RequestBody JobLog entity) {
        return super.saveEntity(entity);
    }

    @Override
    @OptionLog("任务日志删除")
    @PostMapping("/delete")
    public ApiResponse deleteEntity(@RequestBody JobLog entity) {
        return super.deleteEntity(entity);
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

