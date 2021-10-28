package cloud.easy.filter;

import cloud.easy.constant.ApiHeaderTag;
import cloud.easy.device.ApiHeaderHelper;
import cloud.easy.entity.ApiHeader;
import cloud.easy.utils.IpAddressUtils;

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
            String requestId = request.getHeader(ApiHeaderTag.REQUEST_ID.getName());
            String signature = request.getHeader(ApiHeaderTag.SIGNATURE.getName());
            String token = request.getHeader(ApiHeaderTag.TOKEN.getName());
            String timestamp = request.getHeader(ApiHeaderTag.TIMESTAMP.getName());
            String ip = IpAddressUtils.getIpAddr(request);
            //自定义Header对象赋值
            ApiHeader header = new ApiHeader();
            header.setRequestId(requestId);
            header.setSignature(signature);
            header.setToken(token);
            header.setRequestIp(ip);
            header.setRequestPath(request.getServletPath());
            header.setRequestMethod(request.getMethod());
            header.setTimestamp(timestamp);
            ApiHeaderHelper.set(header);
            chain.doFilter(request, response);
        } finally {
            ApiHeaderHelper.remove();
        }
    }
}
