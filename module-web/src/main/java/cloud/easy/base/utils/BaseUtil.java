package cloud.easy.base.utils;

import cloud.easy.base.dto.PrimaryKeyDto;
import cloud.easy.base.entity.AuthEntity;
import cloud.easy.base.entity.BaseEntity;
import cloud.easy.constant.Messages;
import cloud.easy.device.ApiHeaderHelper;
import cloud.easy.entity.ApiHeader;
import cloud.easy.exception.RequestException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import lombok.extern.slf4j.Slf4j;

import static cloud.easy.base.enums.ModeEnum.READ;
import static cloud.easy.base.enums.ModeEnum.READ_WRITE;

/**
 * Title: BaseUtil
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@Slf4j
public class BaseUtil {

    private BaseUtil() {
    }

    public static <T> QueryWrapper<T> groupModeAuthQuery(QueryWrapper<T> queryWrapper) {
        ApiHeader header = ApiHeaderHelper.get();
        if (header == null) {
            return queryWrapper;
        }
        if (!(queryWrapper.getEntity() instanceof AuthEntity)) {
            return queryWrapper;
        }

        String userNo = header.getUserNo();
        Long defaultGroup = header.getDefaultGroup();
        return queryWrapper.and(qw -> qw
                //当前用户可读写
                .eq("create_by", userNo).in("own_mode", READ, READ_WRITE)
                //同组可读写
                .or(q -> q.eq("group_id", defaultGroup).in("group_mode", READ, READ_WRITE))
                //其他组可读写
                .or(q -> q.in("other_mode", READ, READ_WRITE)));
    }


    public static <T extends BaseEntity> Long requireId(T entity) {
        Long id = notNull(entity).getId();
        if (id == null) {
            log.error("主键不能为空");
            throw new RequestException(Messages.ID_EMPTY);
        }
        return id;
    }

    public static <T extends BaseEntity> void uniqueValue(String column, Object value, IService<T> service) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(column, value);
        if (service.count(queryWrapper) > 0) {
            log.error("{}={}, 违反唯一约束", column, value);
            throw new RequestException(Messages.INDEX_ERROR);
        }
    }

    public static Long requireId(PrimaryKeyDto primaryKey) {
        Long id = notNull(primaryKey).getId();
        if (id == null) {
            log.error("主键不能为空");
            throw new RequestException(Messages.ID_EMPTY);
        }
        return id;
    }

    public static <O> O notNull(O object) {
        if (object == null) {
            log.error("入参不能为空");
            throw new RequestException(Messages.REQUEST_EMPTY);
        }
        return object;
    }

}
