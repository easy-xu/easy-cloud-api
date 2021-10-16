package pro.simplecloud.cms.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pro.simplecloud.cms.entity.CmsUser;

import java.util.List;

/**
 * Title: UserDto
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserDto extends CmsUser {

    private List<Long> roleIds;

    private List<Long> groupIds;
}
