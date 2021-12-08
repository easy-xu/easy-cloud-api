package cloud.easy.job.service.impl;

import cloud.easy.job.entity.JobLog;
import cloud.easy.job.mapper.JobLogMapper;
import cloud.easy.job.service.IJobLogService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * 任务日志业务实现类
 *
 * @author xu honglin
 * @since 2021-12-08
 */
@Service
public class JobLogServiceImpl extends ServiceImpl<JobLogMapper, JobLog> implements IJobLogService {

}

