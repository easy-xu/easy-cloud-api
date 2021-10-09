package pro.simplecloud.file.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pro.simplecloud.exception.RequestException;
import pro.simplecloud.file.constant.FileStatus;
import pro.simplecloud.file.entity.FileContent;
import pro.simplecloud.file.entity.FileMaster;
import pro.simplecloud.file.service.FileService;
import pro.simplecloud.file.service.IFileContentService;
import pro.simplecloud.file.service.IFileMasterService;
import pro.simplecloud.utils.Base64Utils;
import pro.simplecloud.utils.SHA256Utils;

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
