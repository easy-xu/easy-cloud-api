package cloud.easy.cms.dto;

import cloud.easy.cms.entity.CmsMenu;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Title: MenuDto
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MenuDto extends CmsMenu {

    private List<MenuDto> children;

}
