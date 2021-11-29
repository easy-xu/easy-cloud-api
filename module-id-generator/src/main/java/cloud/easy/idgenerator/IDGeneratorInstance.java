package cloud.easy.idgenerator;

import cloud.easy.idgenerator.impl.*;

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
     * 用户唯一编号
     */
    USER_NO(new IDGeneratorBuilder().append(new ConstantStringGenerator("U"))
            .append(new DateTimeGenerator("yyyyMMdd"))
            .append(new FixedStringPlusGenerator(new SequenceNumPlusGenerator("user_no", new DateTimeGenerator("yyyyMMdd"), 100), 6, "0"))),
    /**
     * 设备唯一编号
     */
    DEVICE_NO(new IDGeneratorBuilder().append(new ConstantStringGenerator("D"))
            .append(new DateTimeGenerator("yyyyMMdd"))
            .append(new FixedStringPlusGenerator(new SequenceNumPlusGenerator("device_no", new DateTimeGenerator("yyyyMMdd"), 100), 6, "0"))),
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
