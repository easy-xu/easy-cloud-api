package cloud.easy.aspect;


import cloud.easy.annotation.Option;
import cloud.easy.cms.mapper.CmsMapperCust;
import cloud.easy.constant.Messages;
import cloud.easy.device.ApiHeaderHelper;
import cloud.easy.entity.ApiHeader;
import cloud.easy.exception.RequestException;
import cloud.easy.exception.SystemErrorException;
import cloud.easy.sys.entity.SysOptionLog;
import cloud.easy.sys.service.ISysOptionLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Title: OptionAspect
 * Description: 操作注解切面，第二优先级
 *
 * @author Xu Honglin
 * @version 1.0
 * @date 2021/7/21 13:33 首次创建
 * @date 2021/7/21 13:33 最后修改
 */
@Order(1)
@Aspect
@Component
public class OptionAspect {

    @Resource
    private ISysOptionLogService optionLogService;

    @Resource
    private CmsMapperCust cmsMapperCust;

    @Around("@annotation(cloud.easy.annotation.Option)")
    public Object controllerAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Option option = getAnnotationLog(joinPoint);
        String optionName = option.value();
        String menuCode = option.menuCode();
        String optionCode = option.optionCode();
        boolean isLog = option.optionLog();
        Object response = null;
        try {
            if (StringUtils.hasLength(menuCode) && StringUtils.hasLength(optionCode)){
                ApiHeader header = ApiHeaderHelper.get();
                String userNo = header.getUserNo();
                if (!StringUtils.hasLength(userNo)){
                    throw new SystemErrorException("操作校验应为标准接口，UserNo不能为空");
                }
                List<String> options = cmsMapperCust.userMenuOptions(menuCode, userNo);
                if (!options.contains(optionCode)){
                    throw new RequestException(Messages.AUTH_OPTION_ERROR);
                }
            }
            response = joinPoint.proceed(joinPoint.getArgs());
        } finally {
            if (isLog){
                SysOptionLog sysOptionLog = new SysOptionLog();
                sysOptionLog.setOptionName(optionName);
                ApiHeader header = ApiHeaderHelper.get();
                if (header!= null) {
                    sysOptionLog.setUserNo(header.getUserNo());
                    sysOptionLog.setDeviceNo(header.getDeviceNo());
                }
                optionLogService.saveOrUpdate(sysOptionLog);
            }
        }
        return response;
    }

    /**
     * 是否存在注解，如果存在就获取
     */
    private Option getAnnotationLog(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        return method.getAnnotation(Option.class);
    }
}
