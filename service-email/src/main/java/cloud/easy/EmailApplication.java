package cloud.easy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Title: Application
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@Slf4j
@SpringBootApplication
public class EmailApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmailApplication.class, args);
        log.info("-----邮件服务启动成功-----");
    }
}
