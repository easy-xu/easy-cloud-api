package cloud.easy.aspect;


import cloud.easy.annotation.NonStandardRequest;
import cloud.easy.sys.entity.SysApiLog;
import cloud.easy.sys.service.ISysApiLogService;
import cloud.easy.base.mapper.BaseMapperCust;
import cloud.easy.device.ApiHeaderHelper;
import cloud.easy.entity.ApiHeader;
import cloud.easy.entity.HttpResponse;
import cloud.easy.exception.BaseException;
import cloud.easy.exception.RequestException;
import cloud.easy.idgenerator.IDGeneratorInstance;
import cloud.easy.utils.BeanUtils;
import cloud.easy.utils.Timer;
import cloud.easy.utils.UserTokenUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Map;

import static cloud.easy.constant.ApiHeaderTag.*;

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

    @Resource
    private BaseMapperCust baseMapperCust;

    @Around("execution(* cloud.easy..*.controller.*.*(..)))")
    public Object controllerAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Class<?> targetClass = joinPoint.getTarget().getClass();
        MethodSignature methodSignature = (MethodSignature) (joinPoint.getSignature());
        Method targetMethod = targetClass.getDeclaredMethod(methodSignature.getName(), methodSignature.getParameterTypes());
        Logger log = LoggerFactory.getLogger(targetClass);
        Object result = null;
        ApiHeader header = ApiHeaderHelper.get();
        //记录起止日期
        Timer timer = new Timer();
        timer.start();
        try {
            //非标准请求，流水号为空则补充流水号
            if (targetClass.isAnnotationPresent(NonStandardRequest.class) || targetMethod.isAnnotationPresent(NonStandardRequest.class)) {
                //初始化requestId
                if (!StringUtils.hasLength(header.getRequestId())) {
                    String requestId = IDGeneratorInstance.TRANS_NO.generate();
                    header.setRequestId(requestId);
                    MDC.put(REQUEST_ID, requestId);
                }
            } else {
                String requestId = header.getRequestId();
                //流水号不能为空
                if (!StringUtils.hasLength(requestId)) {
                    throw new RequestException("交易流水号不能为空");
                }
                //流水号不能重复
                SysApiLog apiLog = new SysApiLog();
                apiLog.setRequestId(requestId);
                long count = logService.count(Wrappers.query(apiLog));
                if (count > 0) {
                    throw new RequestException("交易流水号重复");
                }
                //校验Token
                String token = header.getToken();
                UserTokenUtils.verifyToken(token);
                Map<String, String> decodeToken = UserTokenUtils.decodeToken(token);
                String userNo = decodeToken.get(USER_NO);
                String deviceNo = decodeToken.get(DEVICE_NO);
                header.setDeviceNo(deviceNo);
                header.setUserNo(userNo);
                MDC.put(USER_NO, header.getUserNo());
                //查询用户默认分组
                Long groupId = baseMapperCust.getDefaultGroup(userNo);
                header.setDefaultGroup(groupId);
            }
            result = joinPoint.proceed(joinPoint.getArgs());
        } catch (Throwable ex) {
            result = handleException(ex, log);
        } finally {
            //保存日志
            SysApiLog apiLog = new SysApiLog();
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

    private HttpResponse handleException(Throwable ex, Logger log) {
        //自定义异常
        if (ex instanceof BaseException) {
            log.error(ex.getMessage());
            return HttpResponse.error(((BaseException) ex).getCode(), ex.getMessage());
        }
        log.error(ex.getMessage(), ex);
        return HttpResponse.error(500, ex.getMessage());
    }

}
