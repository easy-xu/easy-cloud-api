package pro.simplecloud.web.entity;

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
    private int code;
    /**
     * 提示信息
     */
    private String message;

    private Object data;

    public HttpResponse(int code, String message) {
        this.code = code;
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

    public static HttpResponse error(String message) {
        return new HttpResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
    }

    public static HttpResponse error(int code, String message) {
        return new HttpResponse(code, message);
    }
}
