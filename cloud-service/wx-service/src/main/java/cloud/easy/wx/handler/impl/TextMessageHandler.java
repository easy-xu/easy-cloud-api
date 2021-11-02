package cloud.easy.wx.handler.impl;

import cloud.easy.kl.entity.KlKnowledgeNode;
import cloud.easy.kl.service.IKlKnowledgeNodeService;
import cloud.easy.robot.service.ChatRobotService;
import cloud.easy.wx.dto.WxArticle;
import cloud.easy.wx.dto.WxMessage;
import cloud.easy.wx.dto.WxMsgType;
import cloud.easy.wx.handler.WxMessageHandler;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Title: TextMessageHandler
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@Slf4j
@Component
public class TextMessageHandler implements WxMessageHandler {

    @Resource
    private IKlKnowledgeNodeService knowledgeNodeService;

    @Resource
    private ChatRobotService chatRobotService;

    @Override
    public WxMessage dealMessage(WxMessage message) {

        String content = message.getContent();
        log.info("content:{}", content);
        //查询知识点
        KlKnowledgeNode klKnowledgeNode = new KlKnowledgeNode();
        klKnowledgeNode.setName(content);
        List<KlKnowledgeNode> knowledgeNodes = knowledgeNodeService.list(Wrappers.query(klKnowledgeNode));
        //查询不到，转聊天机器人
        if (knowledgeNodes.isEmpty()) {
            String chatResponse = chatRobotService.chat(content);
            message.setContent(chatResponse);
            message.setMsgType(WxMsgType.TEXT);
        } else {
            List<WxArticle> articles = knowledgeNodes.stream().map((knowledgeNode -> {
                //图文消息
                WxArticle article = new WxArticle();
                article.setTitle(knowledgeNode.getName());
                article.setDescription("http://110.40.249.62/knowledge/detail?id=" + knowledgeNode.getId());
                article.setPicUrl("https://mmbiz.qpic.cn/mmbiz_png/CM8Zt6lXnuHohBrIXGEgeib1iaZeicOpphrLKoMkgZxG0CKe54NwKlPbLUECKm8z5YTQZY0V29icNAtHJCibyN7Fyaw/0?wx_fmt=png");
                article.setUrl("http://110.40.249.62/knowledge/detail?id=" + knowledgeNode.getId());
                return article;
            })).collect(Collectors.toList());
            message.setMsgType(WxMsgType.NEWS);
            message.setArticleCount(articles.size());
            message.setArticles(articles);
        }
        return message;
    }
}
