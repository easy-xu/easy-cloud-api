package cloud.easy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Application
 *
 * @author xu honglin
 * @date 2021/11/22 16:46
 */
@Slf4j
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "easy.cloud", exclude = {DataSourceAutoConfiguration.class})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        log.info("-----网关服务启动成功-----");
    }
}

