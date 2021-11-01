package cloud.easy.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.TreeMap;

import static cloud.easy.constant.ApiHeaderTag.REQUEST_ID;
import static cloud.easy.constant.ApiHeaderTag.USER_NO;

/**
 * Title: ApiSignHashUtil
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 * @date 2021/4/7 15:07 首次创建
 * @date 2021/4/7 15:07 最后修改
 */
@Slf4j
public class ApiSignHashUtil {

    private TreeMap<String, String> params = new TreeMap<>();

    public static ApiSignHashUtil builder() {
        return new ApiSignHashUtil();
    }

    private void addParams(String name, String value) {
        this.params.put(name, value);
    }

    public ApiSignHashUtil setRequestId(String requestId) {
        this.addParams(REQUEST_ID, requestId);
        return this;
    }

    public ApiSignHashUtil setUser(String user) {
        this.addParams(USER_NO, user);
        return this;
    }

    public ApiSignHashUtil setBody(String json) {
        this.addParams("body", json);
        return this;
    }

    public String generate() {
        StringBuilder data = new StringBuilder();
        if (params.size() > 0) {
            params.forEach((name, value) -> {
                data.append(name).append("=").append(value == null ? "" : value).append("&");
            });
            data.deleteCharAt(data.length() - 1);
        }
        return SHA256Utils.digest(data.toString());
    }

    public boolean
    check(String signHash) {
        return this.generate().equals(signHash);
    }
}

