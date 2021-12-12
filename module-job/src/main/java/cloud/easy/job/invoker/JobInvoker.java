package cloud.easy.job.invoker;

/**
 * JobInvoker
 *
 * @author xu honglin
 * @date 2021/12/12 20:22
 */
public interface JobInvoker {
    /**
     * 调用任务
     * @param name 任务名
     * @param args 任务参数
     */
    void invoke(String name, String...args);
}
