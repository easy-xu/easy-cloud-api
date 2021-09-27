package pro.simplecloud.controller;

import pro.simplecloud.service.FileService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pro.simplecloud.constant.Messages;
import pro.simplecloud.entity.ApiResponse;
import pro.simplecloud.entity.HttpResponse;
import pro.simplecloud.file.entity.FileMaster;

import javax.annotation.Resource;

/**
 * Title: FileController
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@RestController
@RequestMapping("/api/file")
public class FileController {

    @Resource
    private FileService fileService;

    @RequestMapping("/upload")
    public ApiResponse upload(@RequestParam("file") MultipartFile file) {
        if (file == null) {
            return HttpResponse.reject(Messages.REQUEST_EMPTY);
        }
        FileMaster fileMaster= fileService.upload(file);
        return HttpResponse.ok(fileMaster);
    }
}
