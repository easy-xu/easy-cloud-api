package pro.simplecloud.web.filter;


import org.slf4j.MDC;
import pro.simplecloud.entity.ApiHeader;
import pro.simplecloud.device.ApiHeaderHelper;

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
            MDC.put("user", header.getUsername());
            chain.doFilter(servletRequest, servletResponse);
        } finally {
            MDC.clear();
        }
    }
}
