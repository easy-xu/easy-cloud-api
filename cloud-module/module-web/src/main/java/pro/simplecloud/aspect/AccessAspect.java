package pro.simplecloud.aspect;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import pro.simplecloud.api.entity.ApiLog;
import pro.simplecloud.api.service.IApiLogService;
import pro.simplecloud.base.mapper.BaseMapperCust;
import pro.simplecloud.constant.Messages;
import pro.simplecloud.device.ApiHeaderHelper;
import pro.simplecloud.entity.ApiHeader;
import pro.simplecloud.entity.HttpResponse;
import pro.simplecloud.exception.BaseException;
import pro.simplecloud.exception.RequestException;
import pro.simplecloud.utils.BeanUtils;
import pro.simplecloud.utils.Timer;
import pro.simplecloud.utils.UserTokenUtils;

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
    private IApiLogService logService;

    @Resource
    private BaseMapperCust baseMapperCust;

    @Around("execution(* pro.simplecloud..*.controller.*.*(..)))")
    public Object controllerAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Logger log = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        //未初始化BaseInfo直接放行
        ApiHeader header = ApiHeaderHelper.get();
        if (header == null) {
            return HttpResponse.reject(Messages.NOT_STANDARD);
        }
        //插入交易记录表
        Object result = null;
        String requestId = header.getRequestId();
        //记录起止日期
        Timer timer = new Timer();
        timer.start();
        //保存初始日志
        ApiLog apiLog = new ApiLog();
        BeanUtils.copy(header, apiLog);
        logService.save(apiLog);
        try {
            //校验流水号
            if (!StringUtils.hasLength(requestId)) {
                throw new RequestException("交易流水号不能为空");
            }
            apiLog.setRequestId(requestId);
            int count = logService.count(Wrappers.query(apiLog));
            if (count > 1) {
                throw new RequestException("交易流水号重复：" + requestId);
            }
            String path = header.getPath();
            if (!path.startsWith("/api/open")) {
                //校验Token
                String token = header.getToken();
                UserTokenUtils.verifyToken(token);
                String userNo = UserTokenUtils.decodeToken(token);
                header.setUserNo(userNo);
                //查询用户默认分组
                Long groupId = baseMapperCust.getDefaultGroup(userNo);
                header.setDefaultGroup(groupId);
            }
            result = joinPoint.proceed(joinPoint.getArgs());
        } catch (Throwable ex) {
            result = handelException(ex, log);
        } finally {
            //更新日志
            if (result instanceof HttpResponse) {
                HttpResponse responseDto = (HttpResponse) result;
                int code = responseDto.getCode();
                apiLog.setResponseCode((long) code);
                apiLog.setResponseMessage(responseDto.getMessage());
            }
            apiLog.setUsedTime(timer.end());
            logService.saveOrUpdate(apiLog);
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
