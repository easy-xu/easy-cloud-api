package pro.simplecloud.web.filter;

import pro.simplecloud.utils.IpAddressUtils;
import pro.simplecloud.utils.UserTokenUtils;
import pro.simplecloud.web.api.ApiHeader;
import pro.simplecloud.web.constant.ApiHeaderTag;
import pro.simplecloud.web.device.ApiHeaderHelper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Title: ApiHeaderFilter
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 * @date 2021/4/2 10:10 首次创建
 * @date 2021/4/2 10:10 最后修改
 */
public class ApiHeaderFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        try {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            String requestId = request.getHeader(ApiHeaderTag.REQUEST_ID.name());
            String signature = request.getHeader(ApiHeaderTag.SIGNATURE.name());
            String token = request.getHeader(ApiHeaderTag.TOKEN.name());
            String timestamp = request.getHeader(ApiHeaderTag.TIMESTAMP.name());
            String ip = IpAddressUtils.getIpAddr(request);
            //自定义Header对象赋值
            ApiHeader header = new ApiHeader();
            header.setRequestId(requestId);
            header.setSignature(signature);
            header.setToken(token);
            header.setIp(ip);
            header.setMethodPath(request.getServletPath());
            header.setMethod(request.getMethod());
            header.setUsername(token == null ? null : UserTokenUtils.decodeToken(token));
            header.setTimestamp(timestamp);
            ApiHeaderHelper.set(header);
            chain.doFilter(request, response);
        } finally {
            ApiHeaderHelper.remove();
        }
    }
}
