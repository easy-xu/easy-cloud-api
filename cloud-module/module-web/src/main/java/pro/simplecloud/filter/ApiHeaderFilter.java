package pro.simplecloud.filter;

import org.springframework.util.StringUtils;
import pro.simplecloud.constant.ApiHeaderTag;
import pro.simplecloud.device.ApiHeaderHelper;
import pro.simplecloud.entity.ApiHeader;
import pro.simplecloud.utils.IpAddressUtils;
import pro.simplecloud.utils.UserTokenUtils;

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
            header.setIp(ip);
            header.setPath(request.getServletPath());
            header.setMethod(request.getMethod());
            header.setUserNo(StringUtils.hasLength(token) ? UserTokenUtils.decodeToken(token) : null);
            header.setTimestamp(timestamp);
            ApiHeaderHelper.set(header);
            chain.doFilter(request, response);
        } finally {
            ApiHeaderHelper.remove();
        }
    }
}
