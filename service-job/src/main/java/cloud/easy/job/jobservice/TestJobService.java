package cloud.easy.job.jobservice;

import cloud.easy.job.handler.SpringJobHandler;
import cloud.easy.job.handler.JobService;
import lombok.extern.slf4j.Slf4j;

/**
 * TestJob
 *
 * @author xu honglin
 * @date 2021/12/12 21:56
 */
@Slf4j
@JobService("test")
public class TestJobService implements SpringJobHandler.Job {

    @Override
    public void run(String... params) {
        for (String param : params) {
            log.info("{}", param);
        }
    }

}
