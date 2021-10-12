package pro.simplecloud.cms.dto;

import lombok.Data;
import pro.simplecloud.entity.ApiRequest;

/**
 * Title: UserDto
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@Data
public class UserDto implements ApiRequest {
    private String username;
    private String nickname;
    private String password;
    private String token;
    private String type;
}
