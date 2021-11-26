package cloud.easy.utils;

import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 *
 * @author xuhonglin
 */
public class DateTimeUtils extends DateUtils {

    public final static String DATE = "yyyy-MM-dd";

    public final static String DATE_SHORT = "yyyyMMdd";

    public final static String TIME = "HH:mm:ss";

    public final static String TIME_SHORT = "HHmmss";

    public final static String DATETIME = "yyyy-MM-dd HH:mm:ss";

    public final static String DATETIME_SHORT = "yyyyMMddHHmmss";


    private static final String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    public static String getDate() {
        return formatDate(new Date(), DATE);
    }

    public static String getDateShort() {
        return formatDate(new Date(), DATE_SHORT);
    }

    public static String getTime() {
        return formatDate(new Date(), TIME);
    }

    public static String getTimeShort() {
        return formatDate(new Date(), TIME_SHORT);
    }

    public static String getDateTime() {
        return formatDate(new Date(), DATETIME);
    }

    public static String getDateTimeShort() {
        return formatDate(new Date(), DATETIME_SHORT);
    }

   public static  String formatDate(Date date, String format){
       SimpleDateFormat dateFormat = new SimpleDateFormat(format);
       return dateFormat.format(date);
   }

    public static Date parseDate(String str) {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str, parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

}
