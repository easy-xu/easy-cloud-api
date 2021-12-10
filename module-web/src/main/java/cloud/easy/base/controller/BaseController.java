package cloud.easy.base.controller;

import cloud.easy.base.dto.BatchKeyDto;
import cloud.easy.base.dto.PageDto;
import cloud.easy.base.dto.PageQueryDto;
import cloud.easy.base.dto.PrimaryKeyDto;
import cloud.easy.base.entity.AuthEntity;
import cloud.easy.base.entity.BaseEntity;
import cloud.easy.base.enums.DeletedEnum;
import cloud.easy.constant.Messages;
import cloud.easy.entity.ApiResponse;
import cloud.easy.entity.HttpResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

import static cloud.easy.base.utils.BaseUtil.*;

/**
 * Title: BaseController
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
public class BaseController<T extends BaseEntity, S extends IService<T>> {

    protected final S service;

    public BaseController(S service) {
        this.service = service;
    }

    public ApiResponse queryEntity(T entity) {
        entity = service.getOne(Wrappers.query(notNull(entity)));
        if (entity == null) {
            return HttpResponse.error(Messages.NOT_FOUND);
        }
        return HttpResponse.ok(entity);
    }

    public ApiResponse getEntity(PrimaryKeyDto primaryKey) {
        Long id = requireId(primaryKey);
        T entity = service.getById(id);
        if (entity == null) {
            return HttpResponse.error(Messages.NOT_FOUND);
        }
        return HttpResponse.ok(entity);
    }

    public ApiResponse saveEntity(T entity) {
        if (!service.saveOrUpdate(notNull(entity))) {
            return HttpResponse.error(Messages.DB_SAVE_ERROR);
        }
        return HttpResponse.ok(entity.getId());
    }

    public ApiResponse deleteEntityById(PrimaryKeyDto primaryKey) {
        if (!service.removeById(requireId(primaryKey))) {
            return HttpResponse.error(Messages.DB_DELETE_ERROR);
        }
        return HttpResponse.ok();
    }

    public ApiResponse deleteEntity(T entity) {
        if (!service.removeById(requireId(entity))) {
            return HttpResponse.error(Messages.DB_DELETE_ERROR);
        }
        return HttpResponse.ok();
    }

    public ApiResponse deleteAllByIds(BatchKeyDto batchKey) {
        List<Long> ids = notNull(batchKey).getIds();
        if (ids == null) {
            return HttpResponse.reject(Messages.ID_EMPTY);
        }
        if (!service.removeByIds(ids)) {
            return HttpResponse.error(Messages.DB_DELETE_ERROR);
        }
        return HttpResponse.ok();
    }

    public ApiResponse pageList(PageQueryDto<T> pageQueryDto) {
        //分页
        PageDto pageDto = notNull(pageQueryDto).getPage();
        Page<T> page = new Page<>(pageDto.getCurrent(), pageDto.getPageSize());
        //查询条件
        QueryWrapper<T> queryWrapper = Wrappers.query(pageQueryDto.getQuery());
        service.page(page, groupModeAuthQuery(queryWrapper));
        pageDto.setTotal(page.getTotal());
        pageQueryDto.setRecords(page.getRecords());
        return HttpResponse.ok(pageQueryDto);
    }

    public ApiResponse listEntity(T entity) {
        //查询条件
        if (entity instanceof AuthEntity) {
            ((AuthEntity) entity).setDeleted(DeletedEnum.NOT_DELETED);
        }
        QueryWrapper<T> queryWrapper = Wrappers.query(notNull(entity));
        List<T> list = service.list(groupModeAuthQuery(queryWrapper));
        return HttpResponse.ok(list);
    }

}
