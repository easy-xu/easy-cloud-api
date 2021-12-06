package cloud.easy.message.service;

import cloud.easy.entity.HttpResponse;
import cloud.easy.entity.PollMessage;
import cloud.easy.message.entity.PollMessageContext;
import cloud.easy.message.entity.MessageTimeOut;
import cloud.easy.message.event.PushMessageEvent;
import cloud.easy.message.utils.AsyncRequestUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.AsyncContext;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static cloud.easy.executor.ExecutorServiceInstance.MESSAGE_POLL;

/**
 * MessageContextService
 *
 * @author xu honglin
 * @date 2021/12/2 22:01
 */
@Slf4j
@Component
public class MessageContextService implements ApplicationListener<PushMessageEvent> {

    @Resource
    private ApplicationContext appContent;

    final Queue<PollMessageContext> pollQueue = new ConcurrentLinkedQueue<>();

    public void addPollClient(PollMessage pullMessage, AsyncContext context, int timeout, TimeUnit unit) {
        PollMessageContext messageContext = new PollMessageContext(context);
        //超时任务
        ScheduledFuture<?> future = MESSAGE_POLL.schedule(new MessageTimeOut(messageContext), timeout, unit);
        messageContext.setFuture(future);
        messageContext.setPullMessage(pullMessage);
        //长轮询客户端队列
        pollQueue.add(messageContext);
        log.info("pollQueue size:{}", pollQueue.size());
    }

    public void push(PollMessage pullMessage) {
        for (PollMessageContext messageContext : pollQueue) {
            if (pullMessage.getUserNo().equals(messageContext.getPullMessage().getUserNo())) {
                messageContext.setPushMessage(pullMessage);
                appContent.publishEvent(new PushMessageEvent(messageContext));
            }
        }
    }

    @SneakyThrows
    @Override
    public void onApplicationEvent(PushMessageEvent event) {
        Object source = event.getSource();
        if (source instanceof PollMessageContext) {
            PollMessageContext messageContext = (PollMessageContext) source;
            //发送消息
            AsyncContext context = messageContext.getContext();
            PollMessage message = messageContext.getPushMessage();
            AsyncRequestUtils.writeResponse(context, HttpResponse.ok(message));
            context.complete();
            //移除队列
            pollQueue.remove(messageContext);
            //移除定时任务
            messageContext.getFuture().cancel(false);
        }
    }
}
