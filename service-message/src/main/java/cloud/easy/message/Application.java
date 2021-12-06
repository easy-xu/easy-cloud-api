package cloud.easy.message;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application
 *
 * @author xu honglin
 * @date 2021/12/1 21:54
 */
@Slf4j
@SpringBootApplication(scanBasePackages = "cloud.easy")
@MapperScan("cloud.easy.*.mapper")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        log.info("-----消息服务启动成功-----");
    }
}
