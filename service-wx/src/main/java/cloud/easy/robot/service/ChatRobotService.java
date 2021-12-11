package cloud.easy.robot.service;

import cloud.easy.robot.dto.QykResponse;
import cloud.easy.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Title: ChatRobotService
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@Slf4j
@Service
public class ChatRobotService {

    private RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        ChatRobotService robotService = new ChatRobotService();
        String chat = robotService.chat("3333");
        System.out.println(chat);
    }

    public String chat(String message) {
        ResponseEntity<String> entity = restTemplate.getForEntity("http://api.qingyunke.com/api.php?key=free&appid=0&msg=" + message, String.class);
        if (!HttpStatus.OK.equals(entity.getStatusCode())) {
            return "青云客服务不可用";
        }
        QykResponse qykResponse = null;
        try {
            String response = entity.getBody();
            qykResponse = JsonUtils.toObject(response, QykResponse.class);
        } catch (Exception e) {
            log.error("青云客接口异常", e);
        }
        if (qykResponse == null || qykResponse.getResult() != 0) {
            return "青云客返回异常";
        }
        return qykResponse.getContent();
    }
}
