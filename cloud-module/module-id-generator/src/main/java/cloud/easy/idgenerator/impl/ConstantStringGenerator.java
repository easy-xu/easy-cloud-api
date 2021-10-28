package cloud.easy.idgenerator.impl;


import cloud.easy.idgenerator.IDGenerator;

/**
 * Title: ConstantStringGenerator
 * Description: 字符串常量
 *
 * @author Xu Honglin
 * @version 1.0
 */
public class ConstantStringGenerator implements IDGenerator {

    private String value;

    public ConstantStringGenerator(String value) {
        this.value = value;
    }

    @Override
    public String generate() {
        return value;
    }
}
