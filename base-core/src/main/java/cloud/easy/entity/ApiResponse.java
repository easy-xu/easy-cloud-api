package cloud.easy.entity;

/**
 * Title: ApiResponse
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
public interface ApiResponse {

    /**
     * 接口结果编码
     *
     * @return code
     */
    int getCode();

    /**
     * 接口结果描述
     *
     * @return message
     */
    String getMessage();
}
