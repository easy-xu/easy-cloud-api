package pro.simplecloud.utils;

import pro.simplecloud.entity.ApiHeader;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
        claimMap.put("user", user);
        Date expiresAt = new Date(System.currentTimeMillis() + 2 * 60 * 60 * 1000);
        return JwtTokenUtils.generateToken(claimMap, expiresAt);
    }

    public static String generateToken(String user) {
        HashMap<String, String> claimMap = new HashMap<>(1);
        claimMap.put("user", user);
        return JwtTokenUtils.generateToken(claimMap, null);
    }

    public static void verifyToken(String token) {
        JwtTokenUtils.verifyToken(token);
    }

    public static String decodeToken(String token) {
        Map<String, String> decodeToken = JwtTokenUtils.decodeToken(token);
        return decodeToken.get("user");
    }

}
