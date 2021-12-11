package cloud.easy.cms.service.impl;

import cloud.easy.cms.entity.CmsAuth;
import cloud.easy.cms.entity.CmsAuthMenu;
import cloud.easy.cms.entity.CmsAuthOption;
import cloud.easy.cms.mapper.CmsAuthMapper;
import cloud.easy.cms.service.ICmsAuthMenuService;
import cloud.easy.cms.service.ICmsAuthOptionService;
import cloud.easy.cms.service.ICmsAuthService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 权限业务实现类
 *
 * @author generator
 * @since 2021-12-11
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CmsAuthServiceImpl extends ServiceImpl<CmsAuthMapper, CmsAuth> implements ICmsAuthService {

    @Resource
    private ICmsAuthMenuService cmsAuthMenuService;

    @Resource
    private ICmsAuthOptionService cmsAuthOptionService;

    @Override
    public boolean saveOrUpdate(CmsAuth cmsAuth) {
        super.saveOrUpdate(cmsAuth);
        Long authId = cmsAuth.getId();
        //删除关联历史
        CmsAuthMenu cmsAuthMenu = new CmsAuthMenu();
        cmsAuthMenu.setAuthId(authId);
        cmsAuthMenuService.remove(Wrappers.query(cmsAuthMenu));
        CmsAuthOption cmsAuthOption = new CmsAuthOption();
        cmsAuthOption.setAuthId(authId);
        cmsAuthOptionService.remove(Wrappers.query(cmsAuthOption));
        //新增关联
        List<Long> menuIds = cmsAuth.getMenuIds();
        List<CmsAuthMenu> cmsAuthMenus = menuIds.stream().map(menuId -> {
            CmsAuthMenu authMenu = new CmsAuthMenu();
            authMenu.setMenuId(menuId);
            authMenu.setAuthId(authId);
            return authMenu;
        }).collect(Collectors.toList());
        cmsAuthMenuService.saveBatch(cmsAuthMenus);
        List<Long> optionIds = cmsAuth.getOptionIds();
        List<CmsAuthOption> authOptions = optionIds.stream().map(optionId -> {
            CmsAuthOption authOption = new CmsAuthOption();
            authOption.setOptionId(optionId);
            authOption.setAuthId(authId);
            return authOption;
        }).collect(Collectors.toList());
        cmsAuthOptionService.saveBatch(authOptions);
        return true;
    }

    @Override
    public CmsAuth getById(Serializable id) {
        CmsAuth cmsAuth = super.getById(id);
        //查询关联
        CmsAuthMenu cmsAuthMenu = new CmsAuthMenu();
        cmsAuthMenu.setAuthId((Long) id);
        List<CmsAuthMenu> authMenus = cmsAuthMenuService.list(Wrappers.query(cmsAuthMenu));
        List<Long> menuIds = authMenus.stream().map(CmsAuthMenu::getMenuId).collect(Collectors.toList());
        cmsAuth.setMenuIds(menuIds);
        CmsAuthOption cmsAuthOption = new CmsAuthOption();
        cmsAuthOption.setAuthId((Long) id);
        List<CmsAuthOption> authOptions = cmsAuthOptionService.list(Wrappers.query(cmsAuthOption));
        List<Long> optionIds = authOptions.stream().map(CmsAuthOption::getOptionId).collect(Collectors.toList());
        cmsAuth.setOptionIds(optionIds);
        return cmsAuth;
    }

    @Override
    public boolean removeById(Serializable id) {
        CmsAuthMenu cmsAuthMenu = new CmsAuthMenu();
        cmsAuthMenu.setAuthId((Long) id);
        cmsAuthMenuService.remove(Wrappers.query(cmsAuthMenu));
        CmsAuthOption cmsAuthOption = new CmsAuthOption();
        cmsAuthOption.setAuthId((Long) id);
        cmsAuthOptionService.remove(Wrappers.query(cmsAuthOption));
        super.removeById(id);
        return true;
    }
}

