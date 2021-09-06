package pro.simplecloud.idgenerator.impl;

import pro.simplecloud.idgenerator.IDGenerator;

import java.util.Random;

/**
 * Title: RandomStringGenerator
 * Description: 随机字符串生成器
 *
 * @author Xu Honglin
 * @version 1.0
 */
public class RandomStringGenerator implements IDGenerator {

    private static Random random = new Random();

    private char[] source = "ABCDEFGHJKLMNPRSTUVWXYZabcdefghijkmnpqrstuvwxyz23456789".toCharArray();

    private Integer length;

    public RandomStringGenerator(Integer length) {
        this.length = length;
    }

    public RandomStringGenerator(char[] source, Integer length) {
        this.source = source;
        this.length = length;
    }

    @Override
    public String generate() {
        StringBuilder builder = new StringBuilder(length);
        while (builder.length() < length) {
            builder.append(source[random.nextInt(source.length)]);
        }
        return builder.toString();
    }

}
