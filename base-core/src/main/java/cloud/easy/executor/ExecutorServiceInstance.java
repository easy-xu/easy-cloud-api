package cloud.easy.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * ExecutorServiceInstance
 *
 * @author xu honglin
 * @date 2021/12/3 16:26
 */
public final class ExecutorServiceInstance {

    public static final ScheduledExecutorService MESSAGE_POLL = Executors.newScheduledThreadPool(5);

}
