package pro.simplecloud.cms.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pro.simplecloud.cms.entity.CmsMenu;

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
