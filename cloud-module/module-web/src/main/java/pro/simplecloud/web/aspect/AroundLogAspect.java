package pro.simplecloud.web.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import pro.simplecloud.web.annotation.AroundLog;
import pro.simplecloud.web.entity.ApiResponse;

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
@Slf4j
@Order(1)
@Aspect
@Component
public class AroundLogAspect {

    @Around("@annotation(pro.simplecloud.web.annotation.AroundLog)")
    public Object controllerAround(ProceedingJoinPoint joinPoint) throws Throwable {
        AroundLog aroundLog = getAnnotationLog(joinPoint);
        String apiName = aroundLog.apiName();
        if (!StringUtils.hasLength(apiName)) {
            apiName = joinPoint.getSignature().getName();
        }
        log.info("---- {} start ----", apiName);
        Object response = null;
        try {
            response = joinPoint.proceed(joinPoint.getArgs());
            if (response instanceof ApiResponse) {
                log.info("{} response code: {}", apiName, ((ApiResponse) response).getCode());
                log.info("{} response message: {}", apiName, ((ApiResponse) response).getMessage());
            }
        } finally {
            log.info("---- {} end ----", apiName);
        }
        return response;
    }

    /**
     * 是否存在注解，如果存在就获取
     */
    private AroundLog getAnnotationLog(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        return method.getAnnotation(AroundLog.class);
    }
}
