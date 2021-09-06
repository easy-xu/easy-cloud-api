package pro.simplecloud.idgenerator.impl;


import pro.simplecloud.idgenerator.IDGenerator;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Title: DateTimeGenerator
 * Description: 日期格式
 *
 * @author Xu Honglin
 * @version 1.0
 */
public class DateTimeGenerator implements IDGenerator {

    private String formatPattern;

    public DateTimeGenerator(String formatPattern) {
        this.formatPattern = formatPattern;
    }

    @Override
    public String generate() {
        return new SimpleDateFormat(formatPattern).format(new Date());
    }

}
