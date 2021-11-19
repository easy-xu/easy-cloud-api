package cloud.easy.wx.dto;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Title: WxMsgType
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
public enum WxMsgType {

    /**
     * 微信消息类型
     */
    TEXT("text", "文本消息"),
    EVENT("event", "事件消息"),
    NEWS("news", "图文消息");

    @JsonValue
    private final String code;
    private final String name;

    WxMsgType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
