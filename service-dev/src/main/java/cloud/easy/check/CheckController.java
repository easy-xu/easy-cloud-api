package cloud.easy.check;

import cloud.easy.annotation.NonStandardRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Title: CheckController
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@Slf4j
@NonStandardRequest
@RestController
@RequestMapping("/api")
public class CheckController {

    @RequestMapping("/check")
    public String getCheck() {
        log.info("get check");
        return "service is running";
    }
}
