package cloud.easy.wx.handler;

import cloud.easy.wx.dto.WxMessage;

/**
 * Title: WxMessageHandler
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
public interface WxMessageHandler {

    /**
     * 处理微信消息
     *
     * @param message 消息实体
     * @return 返回消息
     */
    WxMessage dealMessage(WxMessage message);
}
