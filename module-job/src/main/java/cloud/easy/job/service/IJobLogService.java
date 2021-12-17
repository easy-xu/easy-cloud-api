package cloud.easy.job.service;

import cloud.easy.job.entity.JobLog;
import cloud.easy.job.enums.ExecCodeEnum;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 任务日志业务接口
 *
 * @author generator
 * @since 2021-12-11
 */
public interface IJobLogService extends IService<JobLog> {

    /**
     * 插入用户日志
     * @param execCode 编码
     * @param content 内容
     */
    void insertUserLog(ExecCodeEnum execCode, String content);
}

