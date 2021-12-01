package cloud.easy.cms.controller;

import cloud.easy.annotation.OptionLog;
import cloud.easy.base.controller.BaseController;
import cloud.easy.base.dto.PageQueryDto;
import cloud.easy.cms.entity.CmsOption;
import cloud.easy.cms.service.ICmsOptionService;
import cloud.easy.entity.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Title: OptionController
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@RestController
@RequestMapping("/api/cms/option")
public class OptionController extends BaseController<CmsOption, ICmsOptionService> {


    @Autowired
    public OptionController(ICmsOptionService service) {
        super(service);
    }

    @Override
    @PostMapping("/query")
    public ApiResponse queryEntity(@RequestBody CmsOption entity) {
        return super.queryEntity(entity);
    }

    @Override
    @OptionLog("操作保存")
    @PostMapping("/save")
    public ApiResponse saveEntity(@RequestBody CmsOption entity) {
        return super.saveEntity(entity);
    }

    @Override
    @OptionLog("操作删除")
    @PostMapping("/delete")
    public ApiResponse deleteEntity(@RequestBody CmsOption entity) {
        return super.deleteEntity(entity);
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
