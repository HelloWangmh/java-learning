package wang.mh.base.grammer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Regular Expression
 */
public class RegExpDemo {

    public static void main(String[] args) {
        testReplaceAll("123456789", "[0-5]");
        //testGroupSubString("abcabc", "abc");
    }

    /**
     * data中符合exp的都replace为特定字符串
     * @param data
     * @param exp
     */
    public static void testReplaceAll(String data, String exp){
        Pattern pattern = Pattern.compile(exp);
        Matcher matcher = pattern.matcher(data);
        String newData = matcher.replaceAll("*");
        System.out.println(newData);
    }

    /**
     * find one or more matching substrings in the input
     * @param data
     * @param exp
     */
    public static void testGroupSubString(String data, String exp){
        Pattern pattern = Pattern.compile(exp);
        Matcher matcher = pattern.matcher(data);
        while (matcher.find()){
            System.out.println(matcher.group());
            System.out.println("start : " + matcher.start() + "end : " + matcher.end());
        }
    }

    public static boolean testMatch(String data, String exp){
        Pattern pattern = Pattern.compile(exp);
        return pattern.matcher(data).matches();
    }

    /**
     * 通过()进行分组  如"(([1-9]|1[0-2]):([0-5][0-9]))([1,2]?)[ap]m"  共分四组
     * @param data
     * @param exp
     */
    public static void testGroup(String data, String exp){
        Pattern pattern = Pattern.compile(exp);
        Matcher matcher = pattern.matcher(data);
        System.out.println("groupCount : " + matcher.groupCount());
        if (matcher.matches()){
            int num = matcher.groupCount();
            for (int i = 0; i <= num; i++) {
                //0  is entire input
                System.out.println(matcher.group(i));
            }
        }

        //group boundary  第n组的开始,结束位置
        System.out.println("start : " + matcher.start(1));
        System.out.println("end : " + matcher.end(1));
    }
}
