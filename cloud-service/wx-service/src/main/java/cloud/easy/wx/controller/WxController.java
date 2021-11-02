package cloud.easy.wx.controller;

import cloud.easy.annotation.NonStandardRequest;
import cloud.easy.utils.SpringUtils;
import cloud.easy.wx.dto.WxMessage;
import cloud.easy.wx.dto.WxMsgType;
import cloud.easy.wx.handler.WxMessageHandler;
import cloud.easy.wx.handler.impl.DefaultHandler;
import cloud.easy.wx.handler.impl.EventMessageHandler;
import cloud.easy.wx.handler.impl.TextMessageHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * Title: AccessController
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@Slf4j
@NonStandardRequest
@RestController
@RequestMapping("/api")
public class WxController {

    @GetMapping("/wx")
    public String get(String echostr) {
        log.info("echostr:{}", echostr);
        return echostr;
    }

    @PostMapping(value = "/wx", consumes = {"text/xml", "application/xml"}, produces = "text/xml")
    public WxMessage post(@RequestBody WxMessage message) {
        log.info("message:{}", message);

        //根据消息类型处理消息
        WxMsgType msgType = message.getMsgType();
        WxMessageHandler handler = getMessageHandler(msgType);
        message = handler.dealMessage(message);

        //交换发送-接收方
        String fromUserName = message.getFromUserName();
        String toUserName = message.getToUserName();
        message.setFromUserName(toUserName);
        message.setToUserName(fromUserName);
        return message;
    }

    private WxMessageHandler getMessageHandler(WxMsgType msgType) {
        WxMessageHandler handler;
        switch (msgType) {
            case EVENT:
                handler = SpringUtils.getBean(EventMessageHandler.class);
                break;
            case TEXT:
                handler = SpringUtils.getBean(TextMessageHandler.class);
                break;
            default:
                handler = SpringUtils.getBean(DefaultHandler.class);
        }
        return handler;
    }
}
