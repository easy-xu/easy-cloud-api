package cloud.easy.wx.controller;

import cloud.easy.annotation.NonStandardRequest;
import cloud.easy.utils.Base64Utils;
import cloud.easy.wx.dto.WxMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Title: AccessController
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@Slf4j
@NonStandardRequest
@RestController
@RequestMapping("/api")
public class WxController {

    @GetMapping("/wx")
    public String get(String echostr){
        log.info("echostr:{}", echostr);
        return echostr;
    }
    @PostMapping( value = "/wx", consumes = "text/xml", produces = "text/xml")
    public WxMessage post(@RequestBody WxMessage message) {
        log.info("message:{}", message);
        String fromUserName = message.getFromUserName();
        String toUserName = message.getToUserName();
        message.setFromUserName(toUserName);
        message.setToUserName(fromUserName);
        return message;
    }
}
