package cloud.easy;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Title: QuestionnaireApplication
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 * @date 2021/8/30 21:02 首次创建
 * @date 2021/8/30 21:02 最后修改
 */
@Slf4j
@SpringBootApplication
@MapperScan("cloud.easy.*.mapper")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        log.info("-----问卷服务启动成功-----");
    }
}
