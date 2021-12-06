package cloud.easy.message.entity;

import cloud.easy.message.event.PushMessageEvent;
import cloud.easy.utils.SpringUtils;
import lombok.SneakyThrows;
import org.springframework.context.ApplicationContext;

/**
 * MessageTimeOut
 *
 * @author xu honglin
 * @date 2021/12/3 20:37
 */
public class MessageTimeOut implements Runnable{

    private final PollMessageContext messageContext;

    public MessageTimeOut(PollMessageContext messageContext) {
        this.messageContext = messageContext;
    }

    @SneakyThrows
    @Override
    public void run() {
        ApplicationContext appContent = SpringUtils.getApplicationContext();
        appContent.publishEvent(new PushMessageEvent(messageContext));
    }
}
