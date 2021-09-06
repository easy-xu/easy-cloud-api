package pro.simplecloud.idgenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Title: IDGeneratorBuilder
 * Description: 生成器构建器
 *
 * @author Xu Honglin
 * @version 1.0
 * @date 2019/5/20 18:04 首次创建
 * @date 2019/5/20 18:04 最后修改
 */
public class IDGeneratorBuilder implements IDGenerator {

    /**
     * 生成器集合
     */
    private List<IDGenerator> generatorList = new ArrayList<>();

    public IDGeneratorBuilder append(IDGenerator generator) {
        generatorList.add(generator);
        return this;
    }

    /**
     * 使用构建的序列生成字符串序列
     *
     * @return
     */
    @Override
    public String generate() {
        StringBuilder builder = new StringBuilder();
        for (IDGenerator generator : generatorList) {
            builder.append(generator.generate());
        }
        return builder.toString();
    }
}
