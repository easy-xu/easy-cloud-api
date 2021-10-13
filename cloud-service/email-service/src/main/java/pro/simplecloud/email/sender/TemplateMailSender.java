package pro.simplecloud.email.sender;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Map;

/**
 * Title: TemplateMailSender
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 * @date 2021/8/3 15:13 首次创建
 * @date 2021/8/3 15:13 最后修改
 */
@Component
public class TemplateMailSender {

    @Resource
    private JavaMailSender mailSender;

    @Resource
    private TemplateEngine templateEngine;

    @Value("${spring.mail.userNo}")
    private String sysMail;

    public void send(String template, Map<String, Object> params, String subject, String to, String... cc) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);

        //获取thymeleaf的html模板
        Context context = new Context();
        context.setVariables(params);
        //指定模板路径
        String emailContent = templateEngine.process(template, context);
        messageHelper.setText(emailContent, true);
        //发件人，和认证账号相同
        messageHelper.setFrom(sysMail);
        //主题
        messageHelper.setSubject(subject);
        //收件人
        messageHelper.setTo(to);
        //抄送人员
        messageHelper.setCc(cc);
        mailSender.send(messageHelper.getMimeMessage());
    }
}
