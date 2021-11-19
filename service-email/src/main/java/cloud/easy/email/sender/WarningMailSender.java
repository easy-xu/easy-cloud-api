package cloud.easy.email.sender;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Title: WarningMailSender
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 * @date 2021/8/3 15:13 首次创建
 * @date 2021/8/3 15:13 最后修改
 */
@Slf4j
@Component
public class WarningMailSender {

    @Resource
    private TemplateMailSender templateMailSender;

    //@Resource
    //private WarningConfig config;

    @Async("warningMailThreadPool")
    public void send(String message) {
        HashMap<String, Object> params = new HashMap<>(1);
        params.put("message", message);
        try {
            //templateMailSender.send(config.getTemplate(), params, config.getSubject(), config.getTo(), config.getCc().toArray(new String[0]));
            log.info("预警邮件发送成功");
        } catch (Exception e) {
            log.error("预警邮件发送失败", e);
        }
    }

    @Bean
    public ThreadPoolTaskExecutor warningMailThreadPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //核心线程数目
        executor.setCorePoolSize(10);
        //指定最大线程数
        executor.setMaxPoolSize(15);
        //队列中最大的数目
        executor.setQueueCapacity(30);
        //线程名称前缀
        executor.setThreadNamePrefix("warningMailThreadPool_");
        //rejection-policy：当pool已经达到max size的时候，如何处理新任务
        //CALLER_RUNS：不在新线程中执行任务，而是由调用者所在的线程来执行
        //对拒绝task的处理策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //线程空闲后的最大存活时间
        executor.setKeepAliveSeconds(60);
        //加载
        executor.initialize();
        return executor;
    }

}
