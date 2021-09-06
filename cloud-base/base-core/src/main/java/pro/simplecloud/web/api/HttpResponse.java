package pro.simplecloud.web.api;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * Title: HttpResponse
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@Data
public class HttpResponse implements ApiResponse {

    /**
     * 请求状态码
     */
    private int status;
    /**
     * 提示信息
     */
    private String message;

    private Object data;

    public HttpResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public static HttpResponse ok() {
        return new HttpResponse(HttpStatus.OK.value(), "success");
    }

    public static HttpResponse ok(Object data) {
        HttpResponse response = new HttpResponse(HttpStatus.OK.value(), "success");
        response.setData(data);
        return response;
    }
}
