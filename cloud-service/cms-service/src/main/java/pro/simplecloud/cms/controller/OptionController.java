package pro.simplecloud.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.simplecloud.cms.entity.CmsOption;
import pro.simplecloud.cms.service.ICmsOptionService;
import pro.simplecloud.base.controller.BaseController;
import pro.simplecloud.base.dto.PageQueryDto;
import pro.simplecloud.entity.ApiResponse;

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
    @PostMapping("/save")
    public ApiResponse saveEntity(@RequestBody CmsOption entity) {
        return super.saveEntity(entity);
    }

    @Override
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
