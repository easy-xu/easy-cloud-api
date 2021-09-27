package pro.simplecloud.file.entity;

import pro.simplecloud.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 文件内容表
 * </p>
 *
 * @author Generator
 * @since 2021-09-27
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
