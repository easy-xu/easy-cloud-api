package pro.simplecloud.cms.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.simplecloud.cms.dto.AuthDto;
import pro.simplecloud.cms.entity.*;
import pro.simplecloud.cms.mapper.CmsMapperCust;
import pro.simplecloud.cms.service.ICmsAuthMenuService;
import pro.simplecloud.cms.service.ICmsAuthOptionService;
import pro.simplecloud.cms.service.ICmsAuthService;
import pro.simplecloud.cms.service.AuthService;
import pro.simplecloud.utils.BeanUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Title: AuthServiceImpl
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AuthServiceImpl implements AuthService {

    @Resource
    private ICmsAuthService cmsAuthService;

    @Resource
    private ICmsAuthMenuService cmsAuthMenuService;

    @Resource
    private ICmsAuthOptionService cmsAuthOptionService;

    @Resource
    private CmsMapperCust cmsMapperCust;

    @Override
    public void save(AuthDto authDto) {
        CmsAuth cmsAuth = new CmsAuth();
        BeanUtils.copy(authDto, cmsAuth);
        cmsAuthService.saveOrUpdate(cmsAuth);
        Long authId = cmsAuth.getId();
        //删除关联历史
        CmsAuthMenu cmsAuthMenu = new CmsAuthMenu();
        cmsAuthMenu.setAuthId(authId);
        cmsAuthMenuService.remove(Wrappers.query(cmsAuthMenu));
        CmsAuthOption cmsAuthOption = new CmsAuthOption();
        cmsAuthOption.setAuthId(authId);
        cmsAuthOptionService.remove(Wrappers.query(cmsAuthOption));
        //新增关联
        List<Long> menuIds = authDto.getMenuIds();
        List<CmsAuthMenu> cmsAuthMenus = menuIds.stream().map(menuId -> {
            CmsAuthMenu authMenu = new CmsAuthMenu();
            authMenu.setMenuId(menuId);
            authMenu.setAuthId(authId);
            return authMenu;
        }).collect(Collectors.toList());
        cmsAuthMenuService.saveBatch(cmsAuthMenus);
        List<Long> optionIds = authDto.getOptionIds();
        List<CmsAuthOption> authOptions = optionIds.stream().map(optionId -> {
            CmsAuthOption authOption = new CmsAuthOption();
            authOption.setOptionId(optionId);
            authOption.setAuthId(authId);
            return authOption;
        }).collect(Collectors.toList());
        cmsAuthOptionService.saveBatch(authOptions);
    }

    @Override
    public AuthDto getDetail(Long id) {
        AuthDto authDto = new AuthDto();
        CmsAuth cmsAuth = cmsAuthService.getById(id);
        BeanUtils.copy(cmsAuth, authDto);
        //查询关联
        CmsAuthMenu cmsAuthMenu = new CmsAuthMenu();
        cmsAuthMenu.setAuthId(id);
        List<CmsAuthMenu> authMenus = cmsAuthMenuService.list(Wrappers.query(cmsAuthMenu));
        List<Long> menuIds = authMenus.stream().map(CmsAuthMenu::getMenuId).collect(Collectors.toList());
        authDto.setMenuIds(menuIds);
        CmsAuthOption cmsAuthOption = new CmsAuthOption();
        cmsAuthOption.setAuthId(id);
        List<CmsAuthOption> authOptions = cmsAuthOptionService.list(Wrappers.query(cmsAuthOption));
        List<Long> optionIds = authOptions.stream().map(CmsAuthOption::getOptionId).collect(Collectors.toList());
        authDto.setOptionIds(optionIds);
        return authDto;
    }

    @Override
    public List<String> userMenuOptions(String menuCode, String userNo) {
        //查询菜单是否有权限
        return cmsMapperCust.userMenuOptions(menuCode, userNo);
    }
}