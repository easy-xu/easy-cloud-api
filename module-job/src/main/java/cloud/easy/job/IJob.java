package cloud.easy.job;

/**
 * IJob
 *
 * @author xu honglin
 * @date 2021/12/12 21:55
 */
public interface IJob {
    /**
     * 执行任务
     * @param args 参数
     */
    void run(String...args);
}
