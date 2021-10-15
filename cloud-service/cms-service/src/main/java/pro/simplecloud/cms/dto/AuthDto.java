package pro.simplecloud.cms.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pro.simplecloud.cms.entity.CmsAuth;
import pro.simplecloud.cms.entity.CmsOption;
import pro.simplecloud.cms.entity.CmsRole;

import java.util.List;

/**
 * Title: AuthDto
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AuthDto extends CmsAuth {

    private List<Long> menuIds;
    private List<Long> optionIds;

    private String menuCode;
}
