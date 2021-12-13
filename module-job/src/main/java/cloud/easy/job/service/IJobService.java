package cloud.easy.job.service;

/**
 * IJob
 *
 * @author xu honglin
 * @date 2021/12/12 21:55
 */
public interface IJobService {
    /**
     * 执行任务
     * @param args 参数
     */
    void run(String...args);
}
