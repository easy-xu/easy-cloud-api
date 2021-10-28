package cloud.easy.constant;

/**
 * Title: ApiHeaderTag
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
public enum ApiHeaderTag {
    /**
     * 请求id
     */
    REQUEST_ID("requestId"),
    /**
     * 用户token
     */
    TOKEN("token"),
    /**
     * 接口签名
     */
    SIGNATURE("signature"),
    /**
     * 接口时间戳
     */
    TIMESTAMP("timestamp");

    private String name;

    ApiHeaderTag(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
