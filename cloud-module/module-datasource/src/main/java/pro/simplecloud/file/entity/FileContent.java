package pro.simplecloud.file.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pro.simplecloud.entity.BaseEntity;

/**
 * <p>
 * 文件内容表
 * </p>
 *
 * @author Generator
 * @since 2021-10-09
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
