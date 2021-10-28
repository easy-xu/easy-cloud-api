package cloud.easy.utils;

import cloud.easy.exception.SystemErrorException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Title: SHA256Utils
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 * @date 2020/7/17 21:14 首次创建
 * @date 2020/7/17 21:14 最后修改
 */
public class SHA256Utils {

    private static char[] ch = "0123456789abcdef".toCharArray();

    /**
     * sha1加密
     *
     * @param data
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String digest(String data) {
        try {
            //加盐
            data += "wo shi yan";
            //信息摘要器                                算法名称
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            //把字符串转为字节数组
            byte[] b = data.getBytes();
            //使用指定的字节来更新我们的摘要
            md.update(b);
            //获取密文  （完成摘要计算）
            byte[] b2 = md.digest();
            //字符数组转为字符串
            return Base58Utils.encode(b2);
        } catch (NoSuchAlgorithmException e) {
            throw new SystemErrorException("digest error", e);
        }
    }

    public static void main(String[] args) {
        String test = digest("test");
        System.out.println(test);
    }
}
