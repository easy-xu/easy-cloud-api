package cloud.easy.job.controller;

import cloud.easy.entity.ApiResponse;
import cloud.easy.base.dto.PrimaryKeyDto;
import cloud.easy.base.dto.PageQueryDto;
import cloud.easy.annotation.OptionLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import cloud.easy.job.entity.JobConfig;
import cloud.easy.job.service.IJobConfigService;
import cloud.easy.base.controller.BaseController;

/**
 * 任务控制器
 *
 * @author generator
 * @since 2021-12-10
 */
@RestController
@RequestMapping("/api/job/config")
@Api(tags = "任务接口")
public class JobConfigController extends BaseController<JobConfig, IJobConfigService> {

    @Autowired
    public JobConfigController(IJobConfigService service) {
        super(service);
    }

    @Override
    @PostMapping("/get")
    public ApiResponse getEntity(@RequestBody PrimaryKeyDto primaryKey) {
        return super.getEntity(primaryKey);
    }

    @Override
    @OptionLog("任务保存")
    @PostMapping("/save")
    public ApiResponse saveEntity(@Validated @RequestBody JobConfig entity) {
        return super.saveEntity(entity);
    }

    @Override
    @OptionLog("任务删除")
    @PostMapping("/delete")
    public ApiResponse deleteEntityById(@RequestBody PrimaryKeyDto primaryKey) {
        return super.deleteEntityById(primaryKey);
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

    @Override
    @PostMapping("/count")
    public ApiResponse countEntity(@RequestBody JobConfig entity) {
        return super.countEntity(entity);
    }

}

