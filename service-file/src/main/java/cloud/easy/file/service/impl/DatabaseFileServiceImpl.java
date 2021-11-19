package cloud.easy.file.service.impl;

import cloud.easy.exception.RequestException;
import cloud.easy.file.constant.FileStatus;
import cloud.easy.file.entity.FileContent;
import cloud.easy.file.entity.FileMaster;
import cloud.easy.file.service.FileService;
import cloud.easy.file.service.IFileContentService;
import cloud.easy.file.service.IFileMasterService;
import cloud.easy.utils.Base64Utils;
import cloud.easy.utils.SHA256Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;

/**
 * Title: DatabaseFileServiceImpl
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class DatabaseFileServiceImpl implements FileService {

    @Resource
    private IFileContentService fileContentService;

    @Resource
    private IFileMasterService fileMasterService;

    @Override
    public FileMaster upload(MultipartFile file) {
        String originalName = file.getOriginalFilename();
        String baseStr = null;
        try (InputStream inputStream = file.getInputStream()) {
            baseStr = Base64Utils.getBaseStr(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("文件处理异常", e);
            throw new RequestException("文件处理异常," + e.getMessage());
        }
        String sha256 = SHA256Utils.digest(baseStr);
        FileContent fileContent = new FileContent();
        fileContent.setContent(baseStr);
        fileContentService.save(fileContent);
        FileMaster fileMaster = new FileMaster();
        fileMaster.setOriginalName(originalName);
        fileMaster.setContentId(fileContent.getId());
        fileMaster.setSha256(sha256);
        fileMaster.setStatus(FileStatus.TEMP.code);
        fileMasterService.save(fileMaster);
        return fileMaster;
    }
}
