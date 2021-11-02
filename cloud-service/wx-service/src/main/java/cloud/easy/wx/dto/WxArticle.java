package cloud.easy.wx.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Title: WxArticle
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@Data
public class WxArticle {

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "Title")
    private String title;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "Description")
    private String description;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "PicUrl")
    private String picUrl;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "Url")
    private String url;
}
