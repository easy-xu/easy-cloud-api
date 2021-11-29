package cloud.easy.cms.service.impl;

import cloud.easy.cms.entity.CmsUser;
import cloud.easy.cms.mapper.CmsUserMapper;
import cloud.easy.cms.service.ICmsUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息主表 服务实现类
 * </p>
 *
 * @author Mybatis Plus
 * @since 2021-11-29
 */
@Service
public class CmsUserServiceImpl extends ServiceImpl<CmsUserMapper, CmsUser> implements ICmsUserService {

}
