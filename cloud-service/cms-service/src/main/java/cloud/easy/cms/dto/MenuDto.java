package cloud.easy.cms.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import cloud.easy.cms.entity.CmsMenu;

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
