package wang.mh.java8;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

/**
 * java8 的时间处理
 */
public class DateDemo {
    public static void main(String[] args) {
        System.out.println(LocalDate.now().toString());
        System.out.println(LocalDate.now().plusDays(30).toString());
    }

    /**
     * LocalDate
     */
    public static void LocalDateDemo(){
        //获取当前时间
        LocalDate now = LocalDate.now();
        System.out.println(now);
        //获取指定时间
        LocalDate ofDate = LocalDate.of(2017, 2, 3);
        System.out.println(ofDate);

        //解析ISO yyyy-MM-dd  格式
        LocalDate parseDefault = LocalDate.parse("2017-09-30");
        System.out.println(parseDefault);

        //根据模板解析
        //LocalDate.parse("2017/09/30", DateTimeFormatter.BASIC_ISO_DATE);

        //日期变更
        System.out.println(String.format("月第一天:%s",now.withDayOfMonth(30)));
        System.out.println(String.format("这一年最后一天:%s",now.with(TemporalAdjusters.lastDayOfYear())));
        System.out.println(String.format("年第一天:%s",now.withDayOfYear(1)));
        System.out.println(String.format("替换年份:%s",now.withYear(1)));
        System.out.println(String.format("替换月份:%s",now.withMonth(1)));
        System.out.println(String.format("15天后:%s",now.plusDays(15)));
        System.out.println(String.format("这个月第一个周一:%s",now.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY))));
    }


    /**
     * LocalTime
     */

    public static void LocalTimeDemo(){
        LocalTime now = LocalTime.now();
        System.out.println(String.format("当前时间:%s",now));
        System.out.println(String.format("当前时间,清楚毫秒数:%s",now.withNano(0)));
        //同样可以构造时间,解析时间,操作时间
    }

    /**
     *
     */
    public static void LocalDateTimeDemo(){
        LocalDateTime now = LocalDateTime.now();
        System.out.println(String.format("当前日期时间:%s",now));
        System.out.println(String.format("当前日期时间:%s",now.withNano(0)));

    }
}
