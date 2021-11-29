package cloud.easy.api.service.impl;

import cloud.easy.api.entity.ApiLog;
import cloud.easy.api.mapper.ApiLogMapper;
import cloud.easy.api.service.IApiLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 接口日志表 服务实现类
 * </p>
 *
 * @author Mybatis Plus
 * @since 2021-11-29
 */
@Service
public class ApiLogServiceImpl extends ServiceImpl<ApiLogMapper, ApiLog> implements IApiLogService {

}
