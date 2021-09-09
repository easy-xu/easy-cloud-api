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
import pro.simplecloud.utils.Timer;
import pro.simplecloud.web.annotation.AroundLog;

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

        Timer timer = new Timer();
        timer.start();
        AroundLog aroundLog = getAnnotationLog(joinPoint);
        String apiName = aroundLog.apiName();
        if (!StringUtils.hasLength(apiName)) {
            apiName = joinPoint.getSignature().getName();
        }
        log.info("---- {} start ----", apiName);
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            log.info("{} input: {}", apiName, arg);
        }
        Object response = null;
        try {
            response = joinPoint.proceed(args);
            log.info("{} output: {}", apiName, response);

        } finally {
            int useTime = timer.end();
            log.info("---- {} end, user time {}----", apiName, useTime);
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
