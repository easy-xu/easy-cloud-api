package pro.simplecloud.filter;


import org.slf4j.MDC;
import pro.simplecloud.device.ApiHeaderHelper;
import pro.simplecloud.entity.ApiHeader;

import javax.servlet.*;
import java.io.IOException;


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
            MDC.put("requestId", header.getRequestId());
            MDC.put("requestPath", header.getRequestPath());
            MDC.put("requestIp", header.getRequestIp());
            chain.doFilter(servletRequest, servletResponse);
        } finally {
            MDC.clear();
        }
    }
}
