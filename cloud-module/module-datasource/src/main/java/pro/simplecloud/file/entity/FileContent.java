package pro.simplecloud.file.entity;

import pro.simplecloud.entity.LogicDeleteEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 文件内容表
 * </p>
 *
 * @author Generator
 * @since 2021-10-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FileContent extends LogicDeleteEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 文件内容
     */
    private String content;


}
