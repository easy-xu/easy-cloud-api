package pro.simplecloud.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import pro.simplecloud.exception.TokenErrorException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Jwt token生成
 *
 * @author xuhonglin
 * @date 2020-03-23
 */
@Slf4j
public class JwtTokenUtils {

    private static final String SECRET = "this is a secret";

    public static String generateToken(Map<String, String> claimMap, Date expiresAt) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        JWTCreator.Builder builder = JWT.create().withHeader(map);
        claimMap.forEach(builder::withClaim);
        return builder
                .withIssuedAt(new Date())
                .withExpiresAt(expiresAt)
                .sign(Algorithm.HMAC256(SECRET));
    }

    public static void verifyToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            verifier.verify(token);
        } catch (Exception e) {
            throw new TokenErrorException("verify token error", e);
        }
    }

    public static Map<String, String> decodeToken(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            Map<String, Claim> claims = jwt.getClaims();
            HashMap<String, String> map = new HashMap<>();
            claims.forEach((key, claim) -> map.put(key, claim.asString()));
            return map;
        } catch (Exception e) {
            throw new TokenErrorException("decode token error", e);
        }
    }


}