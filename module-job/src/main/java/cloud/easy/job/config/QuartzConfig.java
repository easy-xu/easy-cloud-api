package cloud.easy.job.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * 定时任务配置
 */
@Configuration
public class QuartzConfig
{
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(DataSource dataSource)
    {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setDataSource(dataSource);
        //Configure Main Scheduler Properties
        Properties prop = new Properties();
        prop.put("org.quartz.scheduler.instanceName", "QuartzInstance");
        prop.put("org.quartz.scheduler.instanceId", "QuartzInstance-" + System.currentTimeMillis());
        //Configure ThreadPool
        prop.put("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
        prop.put("org.quartz.threadPool.threadCount", "20");
        prop.put("org.quartz.threadPool.threadPriority", "5");
        //Configure JobStore
        prop.put("org.quartz.jobStore.misfireThreshold", "60000");
        prop.put("org.quartz.jobStore.class", "org.quartz.impl.jdbcjobstore.JobStoreTX");
        prop.put("org.quartz.jobStore.tablePrefix", "qrtz_");
        prop.put("org.quartz.jobStore.isClustered", "true");
        factory.setQuartzProperties(prop);
        factory.setSchedulerName("QuartzScheduler");
        factory.setApplicationContextSchedulerContextKey("QuartzSchedulerApplicationContext");
        factory.setOverwriteExistingJobs(true);
        return factory;
    }
}
