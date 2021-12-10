package cloud.easy.cms.controller;

import cloud.easy.entity.ApiResponse;
import cloud.easy.base.dto.PrimaryKeyDto;
import cloud.easy.base.dto.PageQueryDto;
import cloud.easy.annotation.OptionLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import cloud.easy.cms.entity.CmsOption;
import cloud.easy.cms.service.ICmsOptionService;
import cloud.easy.base.controller.BaseController;

import static cloud.easy.base.utils.BaseUtil.uniqueValue;

/**
 * 操作类型控制器
 *
 * @author generator
 * @since 2021-12-09
 */
@RestController
@RequestMapping("/api/cms/option")
@Api(tags = "操作类型接口")
public class CmsOptionController extends BaseController<CmsOption, ICmsOptionService> {

    @Autowired
    public CmsOptionController(ICmsOptionService service) {
        super(service);
    }

    @Override
    @PostMapping("/get")
    public ApiResponse getEntity(@RequestBody PrimaryKeyDto primaryKey) {
        return super.getEntity(primaryKey);
    }

    @Override
    @OptionLog("操作类型保存")
    @PostMapping("/save")
    public ApiResponse saveEntity(@RequestBody CmsOption entity) {
        uniqueValue("code", entity.getCode(), service);
        return super.saveEntity(entity);
    }

    @Override
    @OptionLog("操作类型删除")
    @PostMapping("/delete")
    public ApiResponse deleteEntityById(@RequestBody PrimaryKeyDto primaryKey) {
        return super.deleteEntityById(primaryKey);
    }

    @Override
    @PostMapping("/page-list")
    public ApiResponse pageList(@RequestBody PageQueryDto<CmsOption> pageQueryDto) {
        return super.pageList(pageQueryDto);
    }

    @Override
    @PostMapping("/list")
    public ApiResponse listEntity(@RequestBody CmsOption entity) {
        return super.listEntity(entity);
    }

}

