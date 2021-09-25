package pro.simplecloud.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import pro.simplecloud.entity.BaseEntity;


/**
 * Title: PageQueryDto
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@Data
public class PageQueryDto<T extends BaseEntity> {
    private T query;
    private Page<T> page;
}
