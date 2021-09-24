package pro.simplecloud.quna.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


/**
 * Title: PageDto
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PageDto<T extends Serializable> extends Page<T> {
    private T query;
}
