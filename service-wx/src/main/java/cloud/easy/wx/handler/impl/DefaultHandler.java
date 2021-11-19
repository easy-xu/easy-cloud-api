package cloud.easy.wx.handler.impl;

import cloud.easy.wx.dto.WxMessage;
import cloud.easy.wx.dto.WxMsgType;
import cloud.easy.wx.handler.WxMessageHandler;
import org.springframework.stereotype.Component;

/**
 * Title: DefaultHandler
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@Component
public class DefaultHandler implements WxMessageHandler {
    @Override
    public WxMessage dealMessage(WxMessage message) {
        message.setMsgType(WxMsgType.TEXT);
        message.setContent("未能识别的消息");
        return message;
    }
}
