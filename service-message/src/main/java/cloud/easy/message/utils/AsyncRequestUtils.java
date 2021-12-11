package cloud.easy.message.utils;

import cloud.easy.entity.HttpResponse;
import cloud.easy.utils.JsonUtils;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * AsynSeverlet
 *
 * @author xu honglin
 * @date 2021/12/3 17:12
 */
public class AsyncRequestUtils {

    public static void writeResponse(AsyncContext context, HttpResponse res) throws IOException {
        HttpServletResponse response = (HttpServletResponse) context.getResponse();
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-cache,no-store");
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write(JsonUtils.toString(res));
    }
}
