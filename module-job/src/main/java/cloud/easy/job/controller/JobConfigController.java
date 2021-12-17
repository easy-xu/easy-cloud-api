package cloud.easy.job.controller;

import cloud.easy.entity.ApiResponse;
import cloud.easy.base.dto.PrimaryKeyDto;
import cloud.easy.base.dto.PageQueryDto;
import cloud.easy.annotation.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import cloud.easy.job.entity.JobConfig;
import cloud.easy.job.service.IJobConfigService;
import cloud.easy.base.controller.BaseController;

import static cloud.easy.base.utils.BaseUtil.notRequireId;
import static cloud.easy.base.utils.BaseUtil.requireId;

/**
 * 任务控制器
 *
 * @author generator
 * @since 2021-12-11
 */
@RestController
@RequestMapping("/api/job/config")
@Api(tags = "任务配置接口")
public class JobConfigController extends BaseController<JobConfig, IJobConfigService> {

    @Autowired
    public JobConfigController(IJobConfigService service) {
        super(service);
    }

    @Override
    @Option(value = "任务配置详情", menuCode = "config", optionCode = "query", optionLog = false)
    @PostMapping("/get")
    public ApiResponse getEntity(@Validated @RequestBody PrimaryKeyDto primaryKey) {
        return super.getEntity(primaryKey);
    }

    @Option(value = "任务配置新增", menuCode = "config", optionCode = "add")
    @PostMapping("/add")
    public ApiResponse addEntity(@Validated @RequestBody JobConfig entity) {
        notRequireId(entity);
        return super.saveEntity(entity);
    }

    @Option(value = "任务配置更新", menuCode = "config", optionCode = "edit")
    @PostMapping("/edit")
    public ApiResponse editEntity(@Validated @RequestBody JobConfig entity) {
        requireId(entity);
        return super.saveEntity(entity);
    }

    @Override
    @Option(value = "任务配置删除", menuCode = "config", optionCode = "delete")
    @PostMapping("/delete")
    public ApiResponse deleteEntityById(@Validated @RequestBody PrimaryKeyDto primaryKey) {
        return super.deleteEntityById(primaryKey);
    }

    @Override
    @Option(value = "任务配置分页查询", menuCode = "config", optionCode = "query", optionLog = false)
    @PostMapping("/page-list")
    public ApiResponse pageList(@RequestBody PageQueryDto<JobConfig> pageQueryDto) {
        return super.pageList(pageQueryDto);
    }

    @Override
    @Option(value = "任务配置列表查询", menuCode = "config", optionCode = "query", optionLog = false)
    @PostMapping("/list")
    public ApiResponse listEntity(@RequestBody JobConfig entity) {
        return super.listEntity(entity);
    }

    @Override
    @Option(value = "任务配置计数", menuCode = "config", optionCode = "query", optionLog = false)
    @PostMapping("/count")
    public ApiResponse countEntity(@RequestBody JobConfig entity) {
        return super.countEntity(entity);
    }

}

