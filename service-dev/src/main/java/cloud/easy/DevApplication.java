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
@MapperScan("cloud.easy.*.mapper")
@SpringBootApplication(scanBasePackages = "cloud.easy")
public class DevApplication {
    public static void main(String[] args) {
        SpringApplication.run(DevApplication.class, args);
        log.info("-----服务启动成功！-----");
    }
}
