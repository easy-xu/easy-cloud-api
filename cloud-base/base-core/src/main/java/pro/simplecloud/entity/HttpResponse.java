package pro.simplecloud.entity;

import lombok.Data;
import pro.simplecloud.constant.Messages;

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

    private HttpResponse(Messages messages) {
        this.code = messages.getCode();
        this.message = messages.getMessage();
    }

    private HttpResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static HttpResponse ok() {
        return new HttpResponse(Messages.OK);
    }

    public static HttpResponse ok(Object data) {
        HttpResponse response = new HttpResponse(Messages.OK);
        response.setData(data);
        return response;
    }

    public static HttpResponse reject(Messages messages) {
        return new HttpResponse(messages);
    }

    public static HttpResponse reject(String message) {
        return new HttpResponse(400, message);
    }


    public static HttpResponse error(int code, String message) {
        return new HttpResponse(code, message);
    }


    public static HttpResponse error(Messages messages) {
        return new HttpResponse(messages);
    }
}
