package cloud.easy.cms.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import cloud.easy.cms.entity.CmsRole;

import java.util.List;

/**
 * Title: RoleDto
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleDto extends CmsRole {

    private List<Long> authIds;
}
