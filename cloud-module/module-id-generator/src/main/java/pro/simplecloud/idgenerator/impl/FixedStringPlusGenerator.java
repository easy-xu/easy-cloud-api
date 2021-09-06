package pro.simplecloud.idgenerator.impl;

import pro.simplecloud.idgenerator.IDGenerator;

/**
 * Title: FixedStringPlusGenerator
 * Description: 字符补偿字符串
 *
 * @author Xu Honglin
 * @version 1.0
 */
public class FixedStringPlusGenerator extends FixedStringGenerator {

    private Integer length;
    private String fixChar;
    private IDGenerator value;

    public FixedStringPlusGenerator(IDGenerator value, int length, String fixChar) {
        this.length = length;
        this.fixChar = fixChar;
        this.value = value;
    }

    @Override
    public String generate() {
        return generate(value.generate(), length, fixChar);
    }

}
