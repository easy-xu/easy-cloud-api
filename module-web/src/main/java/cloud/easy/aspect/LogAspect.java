package cloud.easy.aspect;


import cloud.easy.annotation.AroundLog;
import cloud.easy.utils.JsonUtils;
import cloud.easy.utils.Timer;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

/**
 * Title: AroundLogAspect
 * Description: 日志环绕切面, 第二优先级
 *
 * @author Xu Honglin
 * @version 1.0
 * @date 2021/7/21 13:33 首次创建
 * @date 2021/7/21 13:33 最后修改
 */
@Order(0)
@Aspect
@Component
public class LogAspect {

    @Around("@annotation(cloud.easy.annotation.AroundLog) || execution(* cloud.easy..*.controller.*.*(..))")
    public Object controllerAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Logger log = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        Timer timer = new Timer();
        timer.start();
        String apiName;
        AroundLog aroundLog = getAnnotationLog(joinPoint);
        if (aroundLog != null) {
            apiName = aroundLog.apiName();
        } else {
            apiName = joinPoint.getSignature().getName();
        }
        if (!StringUtils.hasLength(apiName)) {
            apiName = joinPoint.getSignature().getName();
        }
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            String argString = JsonUtils.toString(arg);
            log.info("[{}] 入参: {}", apiName, argString);
        }
        Object response = null;
        try {
            response = joinPoint.proceed(args);
            String resString = JsonUtils.toString(response);
            log.info("[{}] 出参: {}", apiName, resString);

        } finally {
            long useTime = timer.end();
            log.info("[{}] 结束, 耗时 {}...", apiName, useTime);
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
