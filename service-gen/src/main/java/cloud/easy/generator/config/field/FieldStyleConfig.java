package cloud.easy.generator.config.field;

import com.alibaba.fastjson.JSON;
import lombok.Data;

/**
 * FieldStyleConfig
 *
 * @author xu honglin
 * @date 2021/12/7 16:25
 */
@Data
public class FieldStyleConfig {
    private StyleConfig search;
    private StyleConfig table;
    private StyleConfig add;
    private StyleConfig edit;
    private StyleConfig view;

    public static String idConfig(){
        FieldStyleConfig config = new FieldStyleConfig();
        config.search = StyleConfig.builder().display(false).build();
        config.table = StyleConfig.builder().display(false).build();
        config.add = StyleConfig.builder().hidden(true).build();
        config.edit = StyleConfig.builder().hidden(true).build();
        return config.toString();
    }
    public static String superDataConfig(){
        FieldStyleConfig config = new FieldStyleConfig();
        config.search = StyleConfig.builder().display(false).build();
        config.table = StyleConfig.builder().display(false).build();
        config.add = StyleConfig.builder().disable(true).build();
        config.edit = StyleConfig.builder().disable(true).build();
        return config.toString();
    }
    public static String createPropertyConfig(){
        FieldStyleConfig config = new FieldStyleConfig();
        config.search = StyleConfig.builder().display(false).build();
        config.add = StyleConfig.builder().display(false).build();
        config.edit = StyleConfig.builder().disable(true).build();
        return config.toString();
    }
    public static String updatePropertyConfig(){
        FieldStyleConfig config = new FieldStyleConfig();
        config.search = StyleConfig.builder().display(false).build();
        config.add = StyleConfig.builder().display(false).build();
        config.edit = StyleConfig.builder().display(false).build();
        return config.toString();
    }

    public static String noSearchConfig(){
        FieldStyleConfig config = new FieldStyleConfig();
        config.search = StyleConfig.builder().display(false).build();
        return config.toString();
    }
    public static String searchConfig(){
        FieldStyleConfig config = new FieldStyleConfig();
        config.search = StyleConfig.builder().display(true).build();
        return config.toString();
    }
    public static String detailOnlyConfig(){
        FieldStyleConfig config = new FieldStyleConfig();
        config.search = StyleConfig.builder().display(false).build();
        config.table = StyleConfig.builder().display(false).build();
        return config.toString();
    }

    @Override
    public String toString(){
        return JSON.toJSONString(this).replace("\"", "");
    }

}
