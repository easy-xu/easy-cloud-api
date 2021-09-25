package pro.simplecloud.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Title: CorsFilter
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 * @date 2021/4/2 10:10 首次创建
 * @date 2021/4/2 10:10 最后修改
 */
public class CorsFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String originHeader = request.getHeader("origin");
        response.setHeader("Access-Control-Allow-Origin", originHeader);
        String requestHeaders = request.getHeader("Access-Control-Request-Headers");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "HEAD,PUT,DELETE,POST,GET");
        response.setHeader("Access-Control-Allow-Headers", "Accept, Origin, XRequestedWith, Content-Type, LastModified," + (requestHeaders == null ? "" : requestHeaders));
        chain.doFilter(request, response);

    }
}
