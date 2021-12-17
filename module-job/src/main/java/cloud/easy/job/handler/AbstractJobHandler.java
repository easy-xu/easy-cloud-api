package cloud.easy.job.handler;

import cloud.easy.constant.ApiHeaderTag;
import cloud.easy.idgenerator.IDGeneratorInstance;
import cloud.easy.job.dto.JobData;
import cloud.easy.job.dto.JobDataHelper;
import cloud.easy.job.entity.JobLog;
import cloud.easy.job.enums.ExecCodeEnum;
import cloud.easy.job.enums.LogTypeEnum;
import cloud.easy.job.mapper.JobLogMapper;
import cloud.easy.utils.JsonUtils;
import cloud.easy.utils.SpringUtils;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * JobInvoker
 *
 * @author xu honglin
 * @date 2021/12/12 20:22
 */
@Data
public abstract class AbstractJobHandler<T extends JobData> implements Serializable {

    protected T jobData;

    protected AbstractJobHandler(T jobData) {
        this.jobData = jobData;
    }

    /**
     * 执行任务
     */
    public void runJob() {
        Logger log = LoggerFactory.getLogger(this.getClass());
        //初始化requestId
        String requestId = IDGeneratorInstance.TRANS_NO.generate();
        //设置线程变量
        jobData.setRequestId(requestId);
        MDC.put(ApiHeaderTag.REQUEST_ID, requestId);
        MDC.put(ApiHeaderTag.REQUEST_NAME, jobData.getJobName());
        JobDataHelper.set(jobData);
        //日志记录
        JobLog jobLog = new JobLog();
        jobLog.setRequestId(requestId);
        jobLog.setType(LogTypeEnum.SYSTEM);
        try {
            jobLog.setJobId(jobData.getJobId());
            //具体任务调用
            this.invoke();
            jobLog.setExecCode(ExecCodeEnum.SUCCESS);
            jobLog.setExecContent("JobData: " +JsonUtils.toString(jobData));
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            jobLog.setExecCode(ExecCodeEnum.ERROR);
            jobLog.setExecContent(e.getLocalizedMessage());
        } finally {
            jobLog.setExecTime(LocalDateTime.now());
            JobLogMapper jobLogMapper = SpringUtils.getBean(JobLogMapper.class);
            jobLogMapper.insert(jobLog);
            //清除线程变量
            JobDataHelper.remove();
            MDC.clear();
        }
    }

    /**
     * 具体任务调用
     */
    protected abstract void invoke();

}
