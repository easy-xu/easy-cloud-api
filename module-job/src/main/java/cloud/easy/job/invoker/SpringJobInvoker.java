package cloud.easy.job.invoker;

import cloud.easy.job.IJob;
import cloud.easy.utils.SpringUtils;
import org.springframework.stereotype.Component;

/**
 * SpringJobTarget
 *
 * @author xu honglin
 * @date 2021/12/12 20:28
 */
@Component
public class SpringJobInvoker implements JobInvoker{

    @Override
    public void invoke(String name, String... args) {
        IJob job = SpringUtils.getBean(name, IJob.class);
        job.run(args);
    }
}
