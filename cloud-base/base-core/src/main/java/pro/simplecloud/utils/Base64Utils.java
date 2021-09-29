package pro.simplecloud.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;


/**
 * Title:
 * Description: 文件上传文件流解析工具类
 *
 * @author Xu Honglin
 * @version 1.0
 * @date 2018/11/16 17:23  首次创建
 * @date 2018/11/16 17:23  最后修改
 * @copyright 中科软科技股份有限公司
 */
public class Base64Utils {

    /**
     * 将报文中的base64字符串解析成字节码流
     */
    public static byte[] getByte(String baseStr) {
        return Base64.getDecoder().decode(baseStr);
    }

    /**
     * 将字节流转换成base64编码
     */
    public static String getBaseStr(InputStream inputStream) throws IOException {
        byte[] byteArray = readInputStream(inputStream);
        return getBaseStr(byteArray);
    }

    /**
     * 将字节流转换成base64编码
     */
    public static String getBaseStr(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * 从输入流中获取字节数组
     */
    public static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }

}
