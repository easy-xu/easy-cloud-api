package cloud.easy.aspect;


import cloud.easy.base.entity.AuthEntity;
import cloud.easy.base.enums.ModeEnum;
import cloud.easy.constant.Messages;
import cloud.easy.device.ApiHeaderHelper;
import cloud.easy.entity.ApiHeader;
import cloud.easy.exception.RequestException;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

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
     * 检查查询方法。列表查询分页查询需要单独拼接条件
     */
    @AfterReturning(value = "execution( * cloud.easy.*.mapper.*Mapper.selectById(..))", returning = "res")
    public Object afterSelect(Object res) {
        if (needCheck() && !hasReadAuth(res)) {
            throw new RequestException(Messages.AUTH_ERROR);
        }
        return res;
    }

    /**
     * 检查更新操作
     */
    @Before(value = "execution( * cloud.easy.*.mapper.*Mapper.updateById(..))")
    public void beforeUpdate(JoinPoint joinPoint) {
        checkWriteAuth(joinPoint);
    }

    /**
     * 检查删除操作
     */
    @Before(value = "execution( * cloud.easy.*.mapper.*Mapper.deleteById(..))")
    public void beforeDelete(JoinPoint joinPoint) {
        checkWriteAuth(joinPoint);
    }

    private void checkWriteAuth(JoinPoint joinPoint) {
        Object target = joinPoint.getTarget();
        Long entityId = getEntityId(joinPoint);
        BaseMapper<?> mapper = (BaseMapper<?>) target;
        if (needCheck() && entityId != null) {
            Object entity = mapper.selectById(entityId);
            if (!hasWriteAuth(entity)) {
                throw new RequestException(Messages.AUTH_ERROR);
            }
        }
    }

    private Long getEntityId(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Object target = joinPoint.getTarget();
        if (!(target instanceof BaseMapper)) {
            return null;
        }
        if (args.length != 1) {
            return null;
        }
        Object arg = args[0];
        if (arg instanceof Long) {
            return (Long) arg;
        }
        if (arg instanceof AuthEntity) {
            return ((AuthEntity) arg).getId();
        }
        return null;
    }

    private boolean needCheck() {
        ApiHeader header = ApiHeaderHelper.get();
        if (header == null) {
            return false;
        }
        String token = header.getToken();
        if (token == null) {
            return false;
        }
        return header.isCheckAuth();
    }

    private boolean hasReadAuth(Object object) {
        if (!(object instanceof AuthEntity)) {
            return true;
        }
        AuthEntity entity = (AuthEntity) object;
        //全部可读
        ModeEnum otherMode = entity.getOtherMode();
        log.info("readAuth check -- otherMode:{}", otherMode);
        if (!otherMode.equals(ModeEnum.NOT_READ)) {
            return true;
        }
        //没有归属数据
        String createBy = entity.getCreateBy();
        Long groupId = entity.getGroupId();
        log.info("readAuth check -- createBy:{}", createBy);
        log.info("readAuth check -- groupId:{}", groupId);
        if (createBy == null && groupId == null) {
            return true;
        }
        ApiHeader header = ApiHeaderHelper.get();
        //个人可读
        String userNo = header.getUserNo();
        ModeEnum ownMode = entity.getOwnMode();
        log.info("readAuth check -- userNo:{}", userNo);
        log.info("readAuth check -- ownMode:{}", ownMode);
        if (userNo != null && userNo.equals(createBy) && !ownMode.equals(ModeEnum.NOT_READ)) {
            return true;
        }
        //同组可读
        Long userGroup = header.getDefaultGroup();
        ModeEnum groupMode = entity.getGroupMode();
        log.info("readAuth check -- userGroup:{}", userGroup);
        log.info("readAuth check -- groupMode:{}", groupMode);
        return userGroup != null && userGroup.equals(groupId) && !groupMode.equals(ModeEnum.NOT_READ);
    }

    private boolean hasWriteAuth(Object object) {
        if (!(object instanceof AuthEntity)) {
            return true;
        }
        AuthEntity entity = (AuthEntity) object;
        //全部可写
        ModeEnum otherMode = entity.getOtherMode();
        log.info("writeAuth check -- otherMode:{}", otherMode);
        if (otherMode.equals(ModeEnum.READ_WRITE)) {
            return true;
        }
        //没有归属数据
        String createBy = entity.getCreateBy();
        Long groupId = entity.getGroupId();
        log.info("writeAuth check -- createBy:{}", createBy);
        log.info("writeAuth check -- groupId:{}", groupId);
        if (createBy == null && groupId == null) {
            return true;
        }
        ApiHeader header = ApiHeaderHelper.get();
        //个人可写
        String userNo = header.getUserNo();
        ModeEnum ownMode = entity.getOwnMode();
        log.info("writeAuth check -- userNo:{}", userNo);
        log.info("writeAuth check -- ownMode:{}", ownMode);
        if (userNo != null && userNo.equals(createBy) && ownMode.equals(ModeEnum.READ_WRITE)) {
            return true;
        }
        //同组可写
        Long userGroup = header.getDefaultGroup();
        ModeEnum groupMode = entity.getGroupMode();
        log.info("writeAuth check -- userGroup:{}", userGroup);
        log.info("writeAuth check -- groupMode:{}", groupMode);
        return userGroup != null && userGroup.equals(groupId) && groupMode.equals(ModeEnum.READ_WRITE);
    }
}
