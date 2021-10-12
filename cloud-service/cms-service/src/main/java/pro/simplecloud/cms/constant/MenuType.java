package pro.simplecloud.cms.constant;

/**
 * Title: MenuType
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
public enum MenuType {
    //菜单类型（F目录 M菜单）
    FOLDER("F"),
    MENU("M");

    public final String code;

    MenuType(String code) {
        this.code = code;
    }
}
