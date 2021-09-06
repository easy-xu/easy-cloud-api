package pro.simplecloud.web.api;

import lombok.Data;

/**
 * Title: ApiHeader
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@Data
public class ApiHeader {
    /**
     * 请求id
     */
    private String requestId;
    /**
     * 用户token
     */
    private String token;
    /**
     * 用户名
     */
    private String username;
    /**
     * 请求签名
     */
    private String signature;
    /**
     * 请求时间戳
     */
    private String timestamp;


    private String ip;

    private String methodPath;

    private String method;
}
