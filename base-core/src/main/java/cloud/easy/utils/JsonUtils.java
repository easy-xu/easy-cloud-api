package cloud.easy.utils;

import com.alibaba.fastjson.JSON;

/**
 * JsonUtils, 使用fastjson, 仅为了统一
 *
 * @author xu honglin
 * @date 2021/12/11 16:12
 */
public class JsonUtils {

    private JsonUtils() {
    }

    public static String toString(Object object) {
        if (object == null) {
            return null;
        }
        return JSON.toJSONString(object);
    }

    public static <T> T toObject(String json, Class<T> clazz) {
        if (json == null) {
            return null;
        }
        return JSON.parseObject(json, clazz);
    }
}
