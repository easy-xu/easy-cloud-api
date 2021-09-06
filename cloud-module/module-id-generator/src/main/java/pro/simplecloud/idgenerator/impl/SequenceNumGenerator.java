package pro.simplecloud.idgenerator.impl;

import pro.simplecloud.idgenerator.IDGenerator;
import pro.simplecloud.idgenerator.engine.DbSequenceEngine;
import pro.simplecloud.utils.SpringUtils;

import javax.sql.DataSource;

/**
 * Title: SequenceNumGenerator
 * Description:有序数字
 *
 * @author Xu Honglin
 * @version 1.0
 */
public class SequenceNumGenerator implements IDGenerator {


    private String noType;
    private String noLimit;
    private Integer noStep;

    SequenceNumGenerator() {
    }

    public SequenceNumGenerator(String cNoType, String cNoLimit, int cNoStep) {
        this.noType = cNoType;
        this.noLimit = cNoLimit;
        this.noStep = cNoStep;
    }

    @Override
    public String generate() {
        return String.valueOf(getSequenceEngine().nextId(noType, noLimit, noStep));
    }

    DbSequenceEngine getSequenceEngine() {
        return SequenceNumGenerator.DbSequenceEngineHolder.getSequenceEngine();
    }


    private static class DbSequenceEngineHolder {
        private static DbSequenceEngine sequenceEngine = new DbSequenceEngine(SpringUtils.getBean(DataSource.class));

        static DbSequenceEngine getSequenceEngine() {
            return sequenceEngine;
        }
    }

}
