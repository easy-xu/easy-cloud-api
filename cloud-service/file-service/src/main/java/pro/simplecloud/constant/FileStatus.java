package pro.simplecloud.constant;

/**
 * Title: FileStatus
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
public enum FileStatus {
    //文件状态（0临时文件 1正常文件）
    TEMP(0L),
    USED(1L);

    public final Long code;

    FileStatus(Long code) {
        this.code = code;
    }
}
