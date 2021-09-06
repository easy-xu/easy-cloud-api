package pro.simplecloud.idgenerator.impl;

import pro.simplecloud.idgenerator.IDGenerator;

import java.util.Random;

/**
 * Title: RandomNumGenerator
 * Description: 随机数字
 *
 * @author Xu Honglin
 * @version 1.0
 */
public class RandomNumGenerator implements IDGenerator {

    private static Random random = new Random();

    private Integer length;

    public RandomNumGenerator(int length) {
        this.length = length;
    }

    @Override
    public String generate() {
        StringBuilder builder = new StringBuilder(length);
        while (builder.length() < length) {
            builder.append(random.nextInt(10));
        }
        return builder.toString();
    }
}
