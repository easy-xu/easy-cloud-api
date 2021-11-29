package cloud.easy.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static cloud.easy.constant.ApiHeaderTag.DEVICE_NO;
import static cloud.easy.constant.ApiHeaderTag.USER_NO;

/**
 * Jwt token生成
 *
 * @author xuhonglin
 * @date 2020-03-23
 */
public class UserTokenUtils {

    private UserTokenUtils() {
    }

    public static String generateTokenWithExpire(String user) {
        HashMap<String, String> claimMap = new HashMap<>(1);
        claimMap.put(USER_NO, user);
        Date expiresAt = new Date(System.currentTimeMillis() + 2 * 60 * 60 * 1000);
        return JwtTokenUtils.generateToken(claimMap, expiresAt);
    }

    public static String generateToken(String userNo) {
        HashMap<String, String> claimMap = new HashMap<>(1);
        claimMap.put(USER_NO, userNo);
        return JwtTokenUtils.generateToken(claimMap, null);
    }
    public static String generateToken(String userNo, String device) {
        HashMap<String, String> claimMap = new HashMap<>(2);
        claimMap.put(USER_NO, userNo);
        claimMap.put(DEVICE_NO, device);
        return JwtTokenUtils.generateToken(claimMap, null);
    }

    public static void verifyToken(String token) {
        JwtTokenUtils.verifyToken(token);
    }

    public static Map<String, String> decodeToken(String token) {
        return JwtTokenUtils.decodeToken(token);
    }

}
