package pro.simplecloud.aspect;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import pro.simplecloud.constant.Messages;
import pro.simplecloud.entity.ApiHeader;
import pro.simplecloud.entity.HttpResponse;
import pro.simplecloud.exception.BaseException;
import pro.simplecloud.exception.RequestException;
import pro.simplecloud.system.entity.SysApiLog;
import pro.simplecloud.system.service.ISysApiLogService;
import pro.simplecloud.utils.BeanUtils;
import pro.simplecloud.utils.Timer;
import pro.simplecloud.utils.UserTokenUtils;
import pro.simplecloud.device.ApiHeaderHelper;

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
@Order(100)
@Aspect
@Component
public class AccessAspect {

    @Resource
    private ISysApiLogService logService;

    @Around("execution(* pro.simplecloud..*.controller.*.*(..)))")
    public Object controllerAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Logger log = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        //未初始化BaseInfo直接放行
        ApiHeader apiHeader = ApiHeaderHelper.get();
        if (apiHeader == null) {
            return HttpResponse.reject(Messages.NOT_STANDARD);
        }
        //插入交易记录表
        Object result = null;
        String requestId = apiHeader.getRequestId();
        //记录起止日期
        Timer timer = new Timer();
        timer.start();
        //保存初始日志
        SysApiLog sysApiLog = new SysApiLog();
        BeanUtils.copy(apiHeader, sysApiLog);
        logService.save(sysApiLog);
        try {
            //校验流水号
            if (!StringUtils.hasLength(requestId)) {
                throw new RequestException("交易流水号不能为空");
            }
            sysApiLog.setRequestId(requestId);
            int count = logService.count(Wrappers.query(sysApiLog));
            if (count > 1) {
                throw new RequestException("交易流水号重复：" + requestId);
            }
            String path = apiHeader.getPath();
            if(!path.startsWith("/api/open")){
                //校验Token
                String token = apiHeader.getToken();
                UserTokenUtils.verifyToken(token);
            }
            result = joinPoint.proceed(joinPoint.getArgs());
        } catch (Throwable ex) {
            result = handelException(ex, log);
        } finally {
            //更新日志
            if (result instanceof HttpResponse) {
                HttpResponse responseDto = (HttpResponse) result;
                int code = responseDto.getCode();
                sysApiLog.setResponseCode((long) code);
                sysApiLog.setResponseMessage(responseDto.getMessage());
            }
            sysApiLog.setUsedTime(timer.end());
            logService.saveOrUpdate(sysApiLog);
        }
        return result;
    }

    private HttpResponse handelException(Throwable ex, Logger log) {
        //自定义异常
        if (ex instanceof BaseException) {
            log.error(ex.getMessage());
            return HttpResponse.error(((BaseException) ex).getCode(), ex.getMessage());
        }
        log.error(ex.getMessage(), ex);
        return HttpResponse.error(500, ex.getMessage());
    }

}
