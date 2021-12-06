package cloud.easy.job.mapper;

import cloud.easy.job.entity.JobConfig;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 任务查询类
 *
 * @author xu honglin
 * @since 2021-12-06
 */
@Mapper
public interface JobConfigMapper extends BaseMapper<JobConfig> {

}
