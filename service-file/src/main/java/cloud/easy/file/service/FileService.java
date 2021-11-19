package cloud.easy.file.service;

import cloud.easy.file.entity.FileMaster;
import org.springframework.web.multipart.MultipartFile;

/**
 * Title: FileService
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
public interface FileService {
    /**
     * 上传文件
     *
     * @param file 文件
     * @return FileMaster
     */
    FileMaster upload(MultipartFile file);
}
