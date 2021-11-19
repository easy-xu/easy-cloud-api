package cloud.easy.idgenerator.impl;


import cloud.easy.idgenerator.IDGenerator;

/**
 * Title: FixedStringGenerator
 * Description: 字符补偿字符串
 *
 * @author Xu Honglin
 * @version 1.0
 */
public class FixedStringGenerator implements IDGenerator {

    private Integer length;
    private String fixChar;
    private String value;

    FixedStringGenerator() {
    }

    public FixedStringGenerator(String value, int length, String fixChar) {
        this.length = length;
        this.fixChar = fixChar;
        this.value = value;
    }

    /**
     * 字符串补长
     *
     * @param value    需要补长的字符串
     * @param length   目标长度
     * @param charPart 补偿字符
     */
    public static String generate(String value, int length, String charPart) {
        if (value != null) {
            StringBuilder builder = new StringBuilder(length);
            builder.append(value);
            //长度不够，补充字符
            if (builder.length() < length) {

                while (builder.length() < length) {
                    builder.insert(0, charPart);
                }
            }
            //长度超长，截取前半部分
            if (builder.length() > length) {
                builder.delete(0, builder.length() - length);
            }
            value = builder.toString();
        }
        return value;
    }

    @Override
    public String generate() {
        return generate(value, length, fixChar);
    }

}
