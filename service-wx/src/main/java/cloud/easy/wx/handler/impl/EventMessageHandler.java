package cloud.easy.wx.handler.impl;

import cloud.easy.wx.dto.WxMessage;
import cloud.easy.wx.dto.WxMsgType;
import cloud.easy.wx.handler.WxMessageHandler;
import org.springframework.stereotype.Component;

/**
 * Title: EventMessageHandler
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@Component
public class EventMessageHandler implements WxMessageHandler {
    @Override
    public WxMessage dealMessage(WxMessage message) {

        message.setMsgType(WxMsgType.TEXT);
        message.setContent("");

        //订阅消息
        if ("subscribe".equals(message.getEvent())) {
            message.setMsgType(WxMsgType.TEXT);
            message.setContent("欢迎关注安逸的打工人\n回复知识点查询链接，例如：java\n回复[图谱]查看全部\n快试试吧");
        }

        return message;
    }
}
