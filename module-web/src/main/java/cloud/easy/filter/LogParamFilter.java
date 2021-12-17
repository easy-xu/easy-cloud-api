package cloud.easy.filter;


import cloud.easy.device.ApiHeaderHelper;
import cloud.easy.entity.ApiHeader;
import org.slf4j.MDC;

import javax.servlet.*;
import java.io.IOException;

import static cloud.easy.constant.ApiHeaderTag.*;


/**
 * Title: LogParamFilter
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
public class LogParamFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        try {
            ApiHeader header = ApiHeaderHelper.get();
            //Slf4j 参数赋值
            MDC.put(REQUEST_ID, header.getRequestId());
            MDC.put(REQUEST_NAME, header.getRequestPath());
            MDC.put(REQUEST_IP, header.getRequestIp());
            chain.doFilter(servletRequest, servletResponse);
        } finally {
            MDC.clear();
        }
    }
}
