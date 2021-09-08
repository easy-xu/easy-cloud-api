package pro.simplecloud.user.dto;

import lombok.Data;
import pro.simplecloud.web.entity.ApiRequest;

/**
 * Title: UserInfo
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@Data
public class UserInfo implements ApiRequest {
    private String username;
    private String password;
    private String token;
}
