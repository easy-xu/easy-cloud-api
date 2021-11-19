package cloud.easy.config;

import cloud.easy.device.ApiHeaderHelper;
import cloud.easy.entity.ApiHeader;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

/**
 * Title:AutoFillHandler
 * Description: Mybatis plus字段自动充填
 *
 * @author Xu Honglin
 * @version 1.0
 */
@Slf4j
@Component
public class AutoFillHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
        ApiHeader apiHeader = ApiHeaderHelper.get();
        if (apiHeader != null && StringUtils.hasLength(apiHeader.getUserNo())) {
            this.strictInsertFill(metaObject, "createBy", String.class, apiHeader.getUserNo());
            this.strictInsertFill(metaObject, "updateBy", String.class, apiHeader.getUserNo());
        }
        if (apiHeader != null && apiHeader.getDefaultGroup() != null) {
            this.strictInsertFill(metaObject, "groupId", Long.class, apiHeader.getDefaultGroup());
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
        ApiHeader apiHeader = ApiHeaderHelper.get();
        if (apiHeader != null && StringUtils.hasLength(apiHeader.getUserNo())) {
            this.strictUpdateFill(metaObject, "updateBy", String.class, apiHeader.getUserNo());
        }
    }
}
