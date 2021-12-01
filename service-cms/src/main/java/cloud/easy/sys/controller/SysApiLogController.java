package cloud.easy.sys.controller;

import cloud.easy.entity.ApiResponse;
import cloud.easy.base.controller.BaseController;
import cloud.easy.base.dto.PageQueryDto;
import cloud.easy.annotation.OptionLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cloud.easy.sys.entity.SysApiLog;
import cloud.easy.sys.service.ISysApiLogService;

/**
 * <p>
 * 接口日志表 前端控制器
 * </p>
 *
 * @author Mybatis Plus
 * @since 2021-12-01
 */
@RestController
@RequestMapping("/api/sys/apilog")
public class SysApiLogController extends BaseController<SysApiLog, ISysApiLogService> {

    @Autowired
    public SysApiLogController(ISysApiLogService service) {
        super(service);
    }

    @Override
    @OptionLog("接口日志查询")
    @PostMapping("/query")
    public ApiResponse queryEntity(@RequestBody SysApiLog entity) {
        return super.queryEntity(entity);
    }

    @Override
    @OptionLog("接口日志保存")
    @PostMapping("/save")
    public ApiResponse saveEntity(@RequestBody SysApiLog entity) {
        return super.saveEntity(entity);
    }

    @Override
    @OptionLog("接口日志删除")
    @PostMapping("/delete")
    public ApiResponse deleteEntity(@RequestBody SysApiLog entity) {
        return super.deleteEntity(entity);
    }

    @Override
    @OptionLog("接口日志查询")
    @PostMapping("/page-list")
    public ApiResponse pageList(@RequestBody PageQueryDto<SysApiLog> pageQueryDto) {
        return super.pageList(pageQueryDto);
    }

    @Override
    @PostMapping("/list")
    public ApiResponse listEntity(@RequestBody SysApiLog entity) {
        return super.listEntity(entity);
    }

}

