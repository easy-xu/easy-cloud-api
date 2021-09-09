package pro.simplecloud.constant;

/**
 * Title: Status
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
public enum  Status {
    /**
     * 启用
     */
    ACTIVE(0),
    /**
     * 停用
     */
    DIS_ACTIVE(1);

    private int code;

    Status(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
