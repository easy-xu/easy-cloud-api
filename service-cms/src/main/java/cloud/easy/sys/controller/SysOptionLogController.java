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
import cloud.easy.sys.entity.SysOptionLog;
import cloud.easy.sys.service.ISysOptionLogService;

/**
 * <p>
 * 操作记录表 前端控制器
 * </p>
 *
 * @author Mybatis Plus
 * @since 2021-12-01
 */
@RestController
@RequestMapping("/api/sys/optionlog")
public class SysOptionLogController extends BaseController<SysOptionLog, ISysOptionLogService> {

    @Autowired
    public SysOptionLogController(ISysOptionLogService service) {
        super(service);
    }

    @Override
    @PostMapping("/query")
    public ApiResponse queryEntity(@RequestBody SysOptionLog entity) {
        return super.queryEntity(entity);
    }

    @Override
    @OptionLog("操作记录保存")
    @PostMapping("/save")
    public ApiResponse saveEntity(@RequestBody SysOptionLog entity) {
        return super.saveEntity(entity);
    }

    @Override
    @OptionLog("操作记录删除")
    @PostMapping("/delete")
    public ApiResponse deleteEntity(@RequestBody SysOptionLog entity) {
        return super.deleteEntity(entity);
    }

    @Override
    @PostMapping("/page-list")
    public ApiResponse pageList(@RequestBody PageQueryDto<SysOptionLog> pageQueryDto) {
        return super.pageList(pageQueryDto);
    }

    @Override
    @PostMapping("/list")
    public ApiResponse listEntity(@RequestBody SysOptionLog entity) {
        return super.listEntity(entity);
    }

}

