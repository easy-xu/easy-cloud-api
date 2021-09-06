package pro.simplecloud.idgenerator;

import pro.simplecloud.idgenerator.impl.*;

/**
 * Title: IDGeneratorInstance
 * Description: 常用序列号配置生成规则
 *
 * @author Xu Honglin
 * @version 1.0
 * @date 2019/5/24 17:16 首次创建
 * @date 2019/5/24 17:16 最后修改
 */
public enum IDGeneratorInstance implements IDGenerator {

    /**
     * 交易流水号
     */
    TRANS_NO(new IDGeneratorBuilder()
            .append(new DateTimeGenerator("yyyyMMddHHmmss"))
            .append(new RandomNumGenerator(6))),

    /**
     * 保单号
     */
    POLICY_NO(new IDGeneratorBuilder().append(new ConstantStringGenerator("BE"))
            .append(new DateTimeGenerator("yyyyMMdd"))
            .append(new ConstantStringGenerator("9"))
            .append(new FixedStringPlusGenerator(new SequenceNumPlusGenerator("policy_no", new DateTimeGenerator("yyyyMMdd"), 100), 6, "0"))),
    ;


    private IDGenerator idGenerator;

    IDGeneratorInstance(IDGenerator generator) {
        this.idGenerator = generator;
    }

    @Override
    public String generate() {
        return idGenerator.generate();
    }


}
