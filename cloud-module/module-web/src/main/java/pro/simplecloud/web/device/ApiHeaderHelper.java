package pro.simplecloud.web.device;


import pro.simplecloud.web.api.ApiHeader;

/**
 * Title: ApiHeaderHelper
 * Description: ApiHeader ThreadLocal变量
 *
 * @author Xu Honglin
 * @version 1.0
 */
public class ApiHeaderHelper {

    /**
     * 维护多线程间局部变量
     */
    private static final ThreadLocal<ApiHeader> HOLDER = new ThreadLocal<>();

    private ApiHeaderHelper() {
    }

    public static ApiHeader get() {
        return HOLDER.get();
    }


    public static void set(ApiHeader apiHeader) {
        HOLDER.set(apiHeader);
    }

    public static void remove() {
        HOLDER.remove();
    }
}
