package cloud.easy.base.controller;

import cloud.easy.annotation.NonStandardRequest;
import cloud.easy.utils.DateTimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HealthController
 *
 * @author xu honglin
 * @date 2021/11/26 21:02
 */
@Slf4j
@RestController
@NonStandardRequest
public class HealthController {

    @Value("${spring.application.name}")
    private String name;

    @GetMapping("/api/health")
    public String get() {
        String timeNow = DateTimeUtils.getDateTime();
        log.info("health:{}", timeNow);
        return name + " is running," + timeNow;
    }
}
