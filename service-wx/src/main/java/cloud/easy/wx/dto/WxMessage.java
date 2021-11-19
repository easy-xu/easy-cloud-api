package cloud.easy.wx.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.util.List;

/**
 * Title: WxMessage
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@Data
@JacksonXmlRootElement(localName = "xml")
public class WxMessage {

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "ToUserName")
    private String toUserName;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "FromUserName")
    private String fromUserName;

    @JacksonXmlProperty(localName = "CreateTime")
    private String createTime;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "MsgType")
    private WxMsgType msgType;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "Event")
    private String event;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "Content")
    private String content;

    @JacksonXmlProperty(localName = "MsgId")
    private String msgId;

    @JacksonXmlProperty(localName = "ArticleCount")
    private Integer articleCount;

    @JacksonXmlElementWrapper(localName = "Articles")
    @JacksonXmlProperty(localName = "item")
    private List<WxArticle> articles;
}
