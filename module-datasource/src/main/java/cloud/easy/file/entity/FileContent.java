package cloud.easy.file.entity;

import cloud.easy.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 文件内容表
 * </p>
 *
 * @author Generator
 * @since 2021-10-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FileContent extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 文件内容
     */
    private String content;


}
