package pro.simplecloud.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pro.simplecloud.constant.Messages;
import pro.simplecloud.dto.PageQueryDto;
import pro.simplecloud.entity.ApiResponse;
import pro.simplecloud.entity.BaseEntity;
import pro.simplecloud.entity.HttpResponse;

/**
 * Title: BaseController
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
public class BaseController<T extends BaseEntity, S extends IService<T>> {

    public BaseController(S service) {
        this.service = service;
    }
    private S service;

    @PostMapping("/query")
    public ApiResponse queryQuestionnaire(@RequestBody T entity) {
        if (entity == null) {
            return HttpResponse.reject(Messages.REQUEST_EMPTY);
        }
        Long id = entity.getId();
        if (id == null) {
            return HttpResponse.reject(Messages.ID_EMPTY);
        }
        entity = service.getById(id);
        if (entity == null) {
            return HttpResponse.error(Messages.NOT_FOUND);
        }
        return HttpResponse.ok(entity);
    }

    @PostMapping("/page-list")
    public ApiResponse pageListQuestionnaire(@RequestBody PageQueryDto<T> pageQueryDto) {
        if (pageQueryDto == null) {
            return HttpResponse.reject(Messages.REQUEST_EMPTY);
        }
        //分页
        Page<T> page = pageQueryDto.getPage();
        //查询条件
        QueryWrapper<T> queryWrapper  = Wrappers.query(pageQueryDto.getQuery());
        service.page(page, queryWrapper);
        return HttpResponse.ok(pageQueryDto);
    }

}
