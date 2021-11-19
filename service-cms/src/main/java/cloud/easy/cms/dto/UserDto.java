package cloud.easy.cms.dto;

import cloud.easy.cms.entity.CmsUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
