package cloud.easy.cms.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import cloud.easy.cms.entity.CmsAuth;

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
