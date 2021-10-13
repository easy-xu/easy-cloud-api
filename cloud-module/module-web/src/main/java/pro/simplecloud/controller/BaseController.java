package pro.simplecloud.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestBody;
import pro.simplecloud.constant.Messages;
import pro.simplecloud.dto.BaseEntityDto;
import pro.simplecloud.dto.PageDto;
import pro.simplecloud.dto.PageQueryDto;
import pro.simplecloud.entity.ApiResponse;
import pro.simplecloud.entity.BaseEntity;
import pro.simplecloud.entity.HttpResponse;
import pro.simplecloud.exception.RequestException;

import java.util.List;

/**
 * Title: BaseController
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
public class BaseController<T extends BaseEntity, S extends IService<T>> {

    private S service;

    public BaseController(S service) {
        this.service = service;
    }

    public ApiResponse queryEntity(@RequestBody T entity) {
        Long id = requireId(entity);
        entity = service.getById(id);
        if (entity == null) {
            return HttpResponse.error(Messages.NOT_FOUND);
        }
        return HttpResponse.ok(entity);
    }

    public ApiResponse saveEntity(@RequestBody T entity) {
        if (!service.saveOrUpdate(notNull(entity))) {
            return HttpResponse.error(Messages.DB_SAVE_ERROR);
        }
        return HttpResponse.ok(entity.getId());
    }

    public ApiResponse deleteEntity(@RequestBody T entity) {
        if (!service.removeById(requireId(entity))) {
            return HttpResponse.error(Messages.DB_DELETE_ERROR);
        }
        return HttpResponse.ok();
    }

    public ApiResponse deleteAllEntity(@RequestBody BaseEntityDto entity) {
        List<Long> ids = notNull(entity).getIds();
        if (ids == null) {
            return HttpResponse.reject(Messages.ID_EMPTY);
        }
        if (!service.removeByIds(ids)) {
            return HttpResponse.error(Messages.DB_DELETE_ERROR);
        }
        return HttpResponse.ok();
    }

    public ApiResponse pageList(@RequestBody PageQueryDto<T> pageQueryDto) {
        //分页
        PageDto pageDto = notNull(pageQueryDto).getPage();
        Page<T> page = new Page<>(pageDto.getCurrent(), pageDto.getPageSize());
        //查询条件
        QueryWrapper<T> queryWrapper = Wrappers.query(pageQueryDto.getQuery());
        service.page(page, queryWrapper);
        pageDto.setTotal(page.getTotal());
        pageQueryDto.setRecords(page.getRecords());
        return HttpResponse.ok(pageQueryDto);
    }

    public ApiResponse listEntity(@RequestBody T entity) {
        //查询条件
        QueryWrapper<T> queryWrapper = Wrappers.query(notNull(entity));
        List<T> list = service.list(queryWrapper);
        return HttpResponse.ok(list);
    }

    protected Long requireId(T entity) {
        Long id = notNull(entity).getId();
        if (id == null) {
            throw new RequestException(Messages.ID_EMPTY);
        }
        return id;
    }

    protected <O> O notNull(O object) {
        if (object == null) {
            throw new RequestException(Messages.REQUEST_EMPTY);
        }
        return object;
    }
}
