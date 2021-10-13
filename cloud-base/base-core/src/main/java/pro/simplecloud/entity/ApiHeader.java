package pro.simplecloud.entity;

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
     * 用户编号
     */
    private String userNo;
    /**
     * 请求签名
     */
    private String signature;
    /**
     * 请求时间戳
     */
    private String timestamp;


    private String ip;

    private String path;

    private String method;
}
