package cloud.easy.file.entity;

import cloud.easy.base.entity.AuthDataEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 文件主表
 * </p>
 *
 * @author Generator
 * @since 2021-10-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FileMaster extends AuthDataEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 原文件名
     */
    private String originalName;

    /**
     * 服务器文件名
     */
    private String serverName;

    /**
     * 文件摘要
     */
    private String sha256;

    /**
     * 文件内容Id
     */
    private Long contentId;

    /**
     * 文件状态（0临时文件 1正常文件）
     */
    private Long status;


}
