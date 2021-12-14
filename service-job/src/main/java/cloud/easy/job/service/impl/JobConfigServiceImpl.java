package cloud.easy.job.service.impl;

import cloud.easy.job.data.TriggerDto;
import cloud.easy.job.entity.JobConfig;
import cloud.easy.job.mapper.JobConfigMapper;
import cloud.easy.job.quartz.SchedulerService;
import cloud.easy.job.service.IJobConfigService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * 任务业务实现类
 *
 * @author generator
 * @since 2021-12-11
 */
@Service
public class JobConfigServiceImpl extends ServiceImpl<JobConfigMapper, JobConfig> implements IJobConfigService {

    @Resource
    private SchedulerService schedulerService;

    @Override
    public JobConfig getById(Serializable id) {
        JobConfig jobConfig = super.getById(id);
        TriggerDto triggerDto = schedulerService.getJobTriggerDto(String.valueOf(id));
        jobConfig.setTrigger(triggerDto);
        return jobConfig;
    }

    @Override
    public <E extends IPage<JobConfig>> E page(E page, Wrapper<JobConfig> queryWrapper) {
        page = super.page(page, queryWrapper);
        page.getRecords().forEach(jobConfig->{
            TriggerDto triggerDto = schedulerService.getJobTriggerDto(String.valueOf(jobConfig.getId()));
            jobConfig.setTrigger(triggerDto);
        });
        return page;
    }
}

