package pro.simplecloud.aspect;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import pro.simplecloud.base.entity.PrimaryDataEntity;
import pro.simplecloud.base.enums.ModeEnum;
import pro.simplecloud.constant.Messages;
import pro.simplecloud.device.ApiHeaderHelper;
import pro.simplecloud.entity.ApiHeader;
import pro.simplecloud.exception.RequestException;

/**
 * Title: GroupModeCheckAspect
 * Description: 检查记录mode
 *
 * @author Xu Honglin
 * @version 1.0
 * @date 2021/7/21 13:33 首次创建
 * @date 2021/7/21 13:33 最后修改
 */
@Slf4j
@Order(1)
@Aspect
@Component
public class GroupModeAuthCheckAspect {

    /**
     * 检查查询方法。列表查询分页查询需要拼接条件
     */
    @AfterReturning(value = "execution( * pro.simplecloud.*.mapper.*Mapper.selectById(..))", returning = "res")
    public Object afterSelect(Object res) {
        if (res instanceof PrimaryDataEntity) {
            PrimaryDataEntity entity = (PrimaryDataEntity) res;
            if (noReadAuth(entity)) {
                throw new RequestException(Messages.AUTH_ERROR);
            } else {
                log.info("read auth: {}", entity.getId());
            }
        }
        return res;
    }

    /**
     * 检查更新操作
     */
    @Before(value = "execution( * pro.simplecloud.*.mapper.*Mapper.updateById(..))")
    public void beforeUpdate(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Object target = joinPoint.getTarget();
        if (target instanceof BaseMapper && args.length == 1 && args[0] instanceof PrimaryDataEntity) {
            PrimaryDataEntity entity = (PrimaryDataEntity) args[0];
            BaseMapper<?> mapper = (BaseMapper) target;
            Long id = entity.getId();
            //新增不校验权限
            if (id != null) {
                entity = (PrimaryDataEntity) mapper.selectById(id);
                if (noWriteAuth(entity)) {
                    throw new RequestException(Messages.AUTH_ERROR);
                } else {
                    log.info("write auth: {}", id);
                }
            }
        }
    }

    /**
     * 检查删除操作
     */
    @Before(value = "execution( * pro.simplecloud.*.mapper.*Mapper.deleteById(..))")
    public void beforeDelete(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Object target = joinPoint.getTarget();
        if (target instanceof BaseMapper && args.length == 1 && args[0] instanceof Long) {
            BaseMapper<?> mapper = (BaseMapper) target;
            Long id = (Long) args[0];
            PrimaryDataEntity entity = (PrimaryDataEntity) mapper.selectById(id);
            if (noWriteAuth(entity)) {
                throw new RequestException(Messages.AUTH_ERROR);
            } else {
                log.info("write auth: {}", id);
            }
        }
    }


    private boolean noReadAuth(PrimaryDataEntity entity) {
        ApiHeader header = ApiHeaderHelper.get();
        String userNo = header.getUserNo();
        Long defaultGroup = header.getDefaultGroup();
        String createBy = entity.getCreateBy();
        String path = header.getPath();
        if (path.startsWith("/api/open") || path.endsWith("/login")) {
            return false;
        }
        ModeEnum ownMode = entity.getOwnMode();
        if (userNo.equals(createBy) && !ownMode.equals(ModeEnum.NOT_READ)) {
            return false;
        }
        Long groupId = entity.getGroupId();
        ModeEnum groupMode = entity.getGroupMode();
        if (defaultGroup.equals(groupId) && !groupMode.equals(ModeEnum.NOT_READ)) {
            return false;
        }
        ModeEnum otherMode = entity.getOtherMode();
        return otherMode.equals(ModeEnum.NOT_READ);
    }

    private boolean noWriteAuth(PrimaryDataEntity entity) {
        log.info("entity:{}", entity);
        ApiHeader header = ApiHeaderHelper.get();
        String userNo = header.getUserNo();
        Long defaultGroup = header.getDefaultGroup();
        String path = header.getPath();
        if (path.startsWith("/api/open") || path.endsWith("/login")) {
            return false;
        }
        String createBy = entity.getCreateBy();
        ModeEnum ownMode = entity.getOwnMode();
        if (userNo.equals(createBy) && ownMode.equals(ModeEnum.READ_WRITE)) {
            return false;
        }
        Long groupId = entity.getGroupId();
        ModeEnum groupMode = entity.getGroupMode();
        if (defaultGroup.equals(groupId) && groupMode.equals(ModeEnum.READ_WRITE)) {
            return false;
        }
        ModeEnum otherMode = entity.getOtherMode();
        return !otherMode.equals(ModeEnum.READ_WRITE);
    }
}
