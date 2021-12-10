package cloud.easy.cms.controller;

import cloud.easy.annotation.OptionLog;
import cloud.easy.base.controller.BaseController;
import cloud.easy.base.dto.PageQueryDto;
import cloud.easy.base.dto.PrimaryKeyDto;
import cloud.easy.cms.entity.CmsGroup;
import cloud.easy.cms.service.ICmsGroupService;
import cloud.easy.entity.ApiResponse;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static cloud.easy.base.utils.BaseUtil.uniqueValue;

/**
 * 分组控制器
 *
 * @author generator
 * @since 2021-12-10
 */
@RestController
@RequestMapping("/api/cms/group")
@Api(tags = "分组接口")
public class CmsGroupController extends BaseController<CmsGroup, ICmsGroupService> {

    @Autowired
    public CmsGroupController(ICmsGroupService service) {
        super(service);
    }

    @Override
    @PostMapping("/get")
    public ApiResponse getEntity(@RequestBody PrimaryKeyDto primaryKey) {
        return super.getEntity(primaryKey);
    }

    @Override
    @OptionLog("分组保存")
    @PostMapping("/save")
    public ApiResponse saveEntity(@Validated @RequestBody CmsGroup entity) {
        uniqueValue("code", entity.getCode(), service);
        return super.saveEntity(entity);
    }

    @Override
    @OptionLog("分组删除")
    @PostMapping("/delete")
    public ApiResponse deleteEntityById(@RequestBody PrimaryKeyDto primaryKey) {
        return super.deleteEntityById(primaryKey);
    }

    @Override
    @PostMapping("/page-list")
    public ApiResponse pageList(@RequestBody PageQueryDto<CmsGroup> pageQueryDto) {
        return super.pageList(pageQueryDto);
    }

    @Override
    @PostMapping("/list")
    public ApiResponse listEntity(@RequestBody CmsGroup entity) {
        return super.listEntity(entity);
    }

    @Override
    @PostMapping("/count")
    public ApiResponse countEntity(@RequestBody CmsGroup entity) {
        return super.countEntity(entity);
    }

}

