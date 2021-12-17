package cloud.easy.job.service.impl;

import cloud.easy.job.dto.JobData;
import cloud.easy.job.dto.JobDataHelper;
import cloud.easy.job.entity.JobLog;
import cloud.easy.job.enums.ExecCodeEnum;
import cloud.easy.job.enums.LogTypeEnum;
import cloud.easy.job.mapper.JobLogMapper;
import cloud.easy.job.service.IJobLogService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.time.LocalDateTime;

/**
 * 任务日志业务实现类
 *
 * @author generator
 * @since 2021-12-11
 */
@Service
public class JobLogServiceImpl extends ServiceImpl<JobLogMapper, JobLog> implements IJobLogService {

    @Override
    public void insertUserLog(ExecCodeEnum execCode, String content) {
        JobData jobData = JobDataHelper.get();
        JobLog jobLog = new JobLog();
        jobLog.setJobId(jobData.getJobId());
        jobLog.setRequestId(jobData.getRequestId());
        jobLog.setType(LogTypeEnum.USER);
        jobLog.setExecTime(LocalDateTime.now());
        jobLog.setExecCode(execCode);
        jobLog.setExecContent(content);
        baseMapper.insert(jobLog);
    }
}

