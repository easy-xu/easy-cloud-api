package cloud.easy.aspect;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import cloud.easy.api.entity.ApiLog;
import cloud.easy.api.service.IApiLogService;
import cloud.easy.base.mapper.BaseMapperCust;
import cloud.easy.constant.Messages;
import cloud.easy.device.ApiHeaderHelper;
import cloud.easy.entity.ApiHeader;
import cloud.easy.entity.HttpResponse;
import cloud.easy.exception.BaseException;
import cloud.easy.exception.RequestException;
import cloud.easy.utils.BeanUtils;
import cloud.easy.utils.Timer;
import cloud.easy.utils.UserTokenUtils;

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

    @Around("execution(* cloud.easy..*.controller.*.*(..)))")
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
        try {
            String path = header.getRequestPath();
            if (!path.startsWith("/api/open")) {
                //校验Token
                String token = header.getToken();
                UserTokenUtils.verifyToken(token);
                String userNo = UserTokenUtils.decodeToken(token);
                header.setUserNo(userNo);
                MDC.put("userNo", header.getUserNo());
                //查询用户默认分组
                Long groupId = baseMapperCust.getDefaultGroup(userNo);
                header.setDefaultGroup(groupId);
            }
            //校验流水号
            if (!StringUtils.hasLength(requestId)) {
                throw new RequestException("交易流水号不能为空");
            }
            ApiLog apiLog = new ApiLog();
            apiLog.setRequestId(requestId);
            int count = logService.count(Wrappers.query(apiLog));
            if (count > 0) {
                throw new RequestException("交易流水号重复：" + requestId);
            }
            result = joinPoint.proceed(joinPoint.getArgs());
        } catch (Throwable ex) {
            result = handelException(ex, log);
        } finally {
            //保存日志
            ApiLog apiLog = new ApiLog();
            BeanUtils.copy(header, apiLog);
            if (result instanceof HttpResponse) {
                HttpResponse responseDto = (HttpResponse) result;
                int code = responseDto.getCode();
                apiLog.setResponseCode((long) code);
                apiLog.setResponseMessage(responseDto.getMessage());
            }
            apiLog.setUsedTime(timer.end());
            logService.save(apiLog);
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
