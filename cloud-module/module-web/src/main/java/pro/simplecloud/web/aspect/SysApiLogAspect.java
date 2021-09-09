package pro.simplecloud.web.aspect;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import pro.simplecloud.exception.BaseException;
import pro.simplecloud.exception.RequestException;
import pro.simplecloud.system.entity.SysApiLog;
import pro.simplecloud.system.service.ISysApiLogService;
import pro.simplecloud.utils.BeanUtils;
import pro.simplecloud.web.device.ApiHeaderHelper;
import pro.simplecloud.entity.ApiHeader;
import pro.simplecloud.entity.HttpResponse;

import javax.annotation.Resource;

/**
 * Title: TransLogAspect
 * Description: 交易日志切面
 *
 * @author Xu Honglin
 * @version 1.0
 * @date 2021/7/21 13:33 首次创建
 * @date 2021/7/21 13:33 最后修改
 */
@Slf4j
@Order(100)
@Aspect
@Component
public class SysApiLogAspect {


    @Resource
    private ISysApiLogService logService;

    @Around("execution(* pro.simplecloud..*.controller.*.*(..)))")
    public Object controllerAround(ProceedingJoinPoint joinPoint) throws Throwable {
        //未初始化BaseInfo直接放行
        ApiHeader apiHeader = ApiHeaderHelper.get();
        if (apiHeader == null) {
            return joinPoint.proceed(joinPoint.getArgs());
        }
        //插入交易记录表
        Object result = null;
        String requestId = apiHeader.getRequestId();

        SysApiLog sysApiLog = new SysApiLog();
        long start = System.currentTimeMillis();
        try {
            if (!StringUtils.hasLength(requestId)) {
                throw new RequestException("交易流水号不能为空");
            }
            QueryWrapper<SysApiLog> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("request_id", requestId);
            int count = logService.count(queryWrapper);
            if (count > 0) {
                throw new RequestException("交易流水号重复：" + requestId);
            }
            BeanUtils.copy(apiHeader, sysApiLog);
            logService.save(sysApiLog);
            result = joinPoint.proceed(joinPoint.getArgs());
        } catch (Throwable ex) {
            result = handelException(ex);
        } finally {
            long end = System.currentTimeMillis();
            if (result instanceof HttpResponse) {
                HttpResponse responseDto = (HttpResponse) result;
                int code = responseDto.getCode();
                sysApiLog.setResponseCode((long) code);
                sysApiLog.setResponseMessage(responseDto.getMessage());
            }
            sysApiLog.setUsedTime(end - start);
            logService.saveOrUpdate(sysApiLog);
        }
        return result;
    }

    private HttpResponse handelException(Throwable ex) {
        //注解校验参数异常
        if (ex instanceof IllegalArgumentException) {
            log.error(ex.getMessage());
            return HttpResponse.reject(ex.getMessage());
        }
        //自定义异常
        else if (ex instanceof BaseException) {
            log.error(ex.getMessage());
            return HttpResponse.error(((BaseException) ex).getCode(), ex.getMessage());
        }
        log.error(ex.getMessage(), ex);
        return HttpResponse.error(500, ex.getMessage());
    }

}
