package pro.simplecloud.base.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import pro.simplecloud.base.entity.BaseEntity;
import pro.simplecloud.constant.Messages;
import pro.simplecloud.device.ApiHeaderHelper;
import pro.simplecloud.entity.ApiHeader;
import pro.simplecloud.exception.RequestException;

import static pro.simplecloud.base.enums.ModeEnum.READ;
import static pro.simplecloud.base.enums.ModeEnum.READ_WRITE;

/**
 * Title: BaseUtil
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
public class BaseUtil {

    public static  <T> QueryWrapper<T> groupModeAuthQuery(QueryWrapper<T> queryWrapper){
        ApiHeader header = ApiHeaderHelper.get();
        if (header == null) {
            return queryWrapper;
        }
        String userNo = header.getUserNo();
        Long defaultGroup = header.getDefaultGroup();
        return queryWrapper
                //当前用户可读写
                .eq("create_by", userNo).in("own_mode", READ, READ_WRITE)
                //同组可读写
                .or(q -> q.eq("group_id", defaultGroup).in("group_mode", READ, READ_WRITE))
                //其他组可读写
                .or(q -> q.in("other_mode", READ, READ_WRITE));
    }


    public static  <T extends BaseEntity> Long requireId(T entity) {
        Long id = notNull(entity).getId();
        if (id == null) {
            throw new RequestException(Messages.ID_EMPTY);
        }
        return id;
    }

    public  static  <O> O notNull(O object) {
        if (object == null) {
            throw new RequestException(Messages.REQUEST_EMPTY);
        }
        return object;
    }

}
