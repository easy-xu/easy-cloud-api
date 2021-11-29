package cloud.easy.entity;

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
     * 设备编号
     */
    private String deviceNo;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户分组id
     */
    private Long defaultGroup;
    /**
     * 请求签名
     */
    private String signature;
    /**
     * 请求时间戳
     */
    private String timestamp;
    /**
     * 请求ip
     */
    private String requestIp;
    /**
     * 接口路径
     */
    private String requestPath;
    /**
     * 接口方法
     */
    private String requestMethod;
}
