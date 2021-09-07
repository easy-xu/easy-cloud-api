package pro.simplecloud.user.dto;

import lombok.Data;

/**
 * Title: UserInfo
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@Data
public class UserInfo {
    private String username;
    private String password;
    private String token;
}
