package cloud.easy.message.entity;

import cloud.easy.entity.PollMessage;
import lombok.Data;

import javax.servlet.AsyncContext;
import java.util.concurrent.ScheduledFuture;

/**
 * MessagePull
 *
 * @author xu honglin
 * @date 2021/12/3 17:10
 */
@Data
public class PollMessageContext {

    private PollMessage pullMessage;

    private PollMessage pushMessage;

    private AsyncContext context;

    private ScheduledFuture<?> future;

    public PollMessageContext(AsyncContext context) {
        this.context = context;
    }

}
