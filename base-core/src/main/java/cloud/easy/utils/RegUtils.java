package cloud.easy.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * RegUtils
 *
 * @author xu honglin
 * @date 2021/12/6 13:37
 */
public class RegUtils {

    private RegUtils(){}

    public static String findAny(String source, String reg){
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(source);
        if(matcher.find()){
           return matcher.group();
        }
        return null;
    }

    public static List<String> findAll(String source, String reg){
        List<String> marched = new ArrayList<>();
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(source);
        while (matcher.find()){
            marched.add(matcher.group());
        }
        return marched;
    }
}
