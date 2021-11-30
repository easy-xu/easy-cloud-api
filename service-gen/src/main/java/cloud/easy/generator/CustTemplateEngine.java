package cloud.easy.generator;

import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.util.Map;

/**
 * CustTemplateEngine
 *
 * @author xu honglin
 * @date 2021/11/30 16:08
 */
public class CustTemplateEngine extends FreemarkerTemplateEngine {

    @Override
    protected void outputCustomFile(@NotNull Map<String, String> customFile, @NotNull TableInfo tableInfo, @NotNull Map<String, Object> objectMap) {
        String otherPath = getPathInfo(OutputFile.other);
        customFile.forEach((key, value) -> {
            String name = tableInfo.getName();
            String parent = name.indexOf('_') > 1 ? name.substring(0, name.indexOf("_")) : name;
            String fileName = String.format((otherPath + File.separator + "pages" + File.separator  + "cms" + File.separator + parent + File.separator + "%s"), key);
            outputFile(new File(fileName), objectMap, value);
        });
    }
}
