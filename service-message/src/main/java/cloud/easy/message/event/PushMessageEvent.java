package cloud.easy.message.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * SendMessageEvent
 *
 * @author xu honglin
 * @date 2021/12/3 17:52
 */
@Getter
public class PushMessageEvent extends ApplicationEvent {

    public PushMessageEvent(Object source) {
        super(source);
    }

}
