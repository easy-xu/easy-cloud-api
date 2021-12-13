package cloud.easy.job.jobservice;

import cloud.easy.job.service.IJobService;
import cloud.easy.job.service.JobService;
import lombok.extern.slf4j.Slf4j;

/**
 * TestJob
 *
 * @author xu honglin
 * @date 2021/12/12 21:56
 */
@Slf4j
@JobService("test")
public class TestJobService implements IJobService {

    @Override
    public void run(String... args) {
        for (String arg : args) {
            log.info("{}", arg);
        }
    }

}
