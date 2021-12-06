package cloud.easy.job.controller;

import cloud.easy.entity.ApiResponse;
import cloud.easy.base.dto.PageQueryDto;
import cloud.easy.annotation.OptionLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cloud.easy.job.entity.JobConfig;
import cloud.easy.job.service.IJobConfigService;
import cloud.easy.base.controller.BaseController;

/**
 * 任务控制器
 *
 * @author xu honglin
 * @since 2021-12-06
 */
@RestController
@RequestMapping("/api/job/config")
public class JobConfigController extends BaseController<JobConfig, IJobConfigService> {

    @Autowired
    public JobConfigController(IJobConfigService service) {
        super(service);
    }

    @Override
    @PostMapping("/query")
    public ApiResponse queryEntity(@RequestBody JobConfig entity) {
        return super.queryEntity(entity);
    }

    @Override
    @OptionLog("任务保存")
    @PostMapping("/save")
    public ApiResponse saveEntity(@RequestBody JobConfig entity) {
        return super.saveEntity(entity);
    }

    @Override
    @OptionLog("任务删除")
    @PostMapping("/delete")
    public ApiResponse deleteEntity(@RequestBody JobConfig entity) {
        return super.deleteEntity(entity);
    }

    @Override
    @PostMapping("/page-list")
    public ApiResponse pageList(@RequestBody PageQueryDto<JobConfig> pageQueryDto) {
        return super.pageList(pageQueryDto);
    }

    @Override
    @PostMapping("/list")
    public ApiResponse listEntity(@RequestBody JobConfig entity) {
        return super.listEntity(entity);
    }

}

