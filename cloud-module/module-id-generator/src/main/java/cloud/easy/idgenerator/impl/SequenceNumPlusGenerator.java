package cloud.easy.idgenerator.impl;

import cloud.easy.idgenerator.IDGenerator;

/**
 * Title: SequenceNumPlusGenerator
 * Description:有序数字
 *
 * @author Xu Honglin
 * @version 1.0
 * @date 2019/5/21 10:24 首次创建
 * @date 2019/5/21 10:24 最后修改
 */
public class SequenceNumPlusGenerator extends SequenceNumGenerator {

    private String noType;
    private IDGenerator noLimit;
    private Integer noStep;

    public SequenceNumPlusGenerator(String cNoType, IDGenerator cNoLimit, int cNoStep) {
        this.noType = cNoType;
        this.noLimit = cNoLimit;
        this.noStep = cNoStep;
    }

    @Override
    public String generate() {
        return String.valueOf(getSequenceEngine().nextId(noType, noLimit.generate(), noStep));
    }

}
