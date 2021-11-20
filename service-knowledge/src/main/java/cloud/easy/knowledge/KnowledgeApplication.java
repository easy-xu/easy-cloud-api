package cloud.easy.knowledge;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
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
@MapperScan("cloud.easy.*.mapper")
public class KnowledgeApplication {
    public static void main(String[] args) {
        SpringApplication.run(KnowledgeApplication.class, args);
        log.info("-----问卷服务启动成功-----");
    }
}
