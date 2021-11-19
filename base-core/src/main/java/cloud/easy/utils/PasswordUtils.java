package cloud.easy.utils;

/**
 * Title: PasswordUtils
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 * @date 2021/7/4 10:59 首次创建
 * @date 2021/7/4 10:59 最后修改
 */
public class PasswordUtils {

    private PasswordUtils() {
    }

    public static String encrypt(String password) {
        return SHA256Utils.digest(password);
    }

    public static void main(String[] args) {
        String encrypt = PasswordUtils.encrypt("admin123");
        System.out.println(encrypt);
    }
}
