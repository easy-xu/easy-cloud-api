package cloud.easy.aspect;


import cloud.easy.annotation.OptionLog;
import cloud.easy.device.ApiHeaderHelper;
import cloud.easy.entity.ApiHeader;
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

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * Title: LogAspect
 * Description: 日志环绕切面
 *
 * @author Xu Honglin
 * @version 1.0
 * @date 2021/7/21 13:33 首次创建
 * @date 2021/7/21 13:33 最后修改
 */
@Order(99)
@Aspect
@Component
public class OptionLogAspect {

    @Resource
    private ISysOptionLogService optionLogService;

    @Around("@annotation(cloud.easy.annotation.OptionLog)")
    public Object controllerAround(ProceedingJoinPoint joinPoint) throws Throwable {
        OptionLog optionLog = getAnnotationLog(joinPoint);
        String optionName = optionLog.value();
        Object response = null;
        try {
            response = joinPoint.proceed(joinPoint.getArgs());
        } finally {
            SysOptionLog sysOptionLog = new SysOptionLog();
            sysOptionLog.setOptionName(optionName);
            ApiHeader header = ApiHeaderHelper.get();
            if (header!= null) {
                sysOptionLog.setUserNo(header.getUserNo());
                sysOptionLog.setDeviceNo(header.getDeviceNo());
            }
            optionLogService.saveOrUpdate(sysOptionLog);
        }
        return response;
    }

    /**
     * 是否存在注解，如果存在就获取
     */
    private OptionLog getAnnotationLog(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        return method.getAnnotation(OptionLog.class);
    }
}
