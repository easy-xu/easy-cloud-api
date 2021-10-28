package cloud.easy;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Title: DevApplication
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@Slf4j
@SpringBootApplication(scanBasePackages = "cloud.easy")
@MapperScan("cloud.easy.*.mapper")
public class DevApplication {
    public static void main(String[] args) {
        SpringApplication.run(DevApplication.class, args);
        log.info("-----服务启动成功-----");
    }
}
