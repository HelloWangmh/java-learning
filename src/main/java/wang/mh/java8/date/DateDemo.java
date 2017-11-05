package wang.mh.java8.date;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

/**
 * java8 的时间处理
 * Instant.now()  标准时间
 */
public class DateDemo {
    public static void main(String[] args) throws InterruptedException {
        testConvert();

    }

    /**
     * 新旧 date api 转换
     */
    public static void testConvert(){
        Date date = new Date();
        //这个是标准时间 子午线
        Instant instant = date.toInstant();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        System.out.println(localDateTime);
    }


    /**
     * DateFormatter
     */
    public static void testFormatter(){
        LocalDateTime now = LocalDateTime.now();
        //1 Predefined standard
        System.out.println(DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(now));
        //2 Local-specific
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
        //默认系统local
        System.out.println(formatter.format(now));
        System.out.println(formatter.withLocale(Locale.ENGLISH).format(now));
        //3 customer patterns
        formatter = DateTimeFormatter.ofPattern("E yyyyMMdd");
        System.out.println(formatter.format(now));
        formatter = DateTimeFormatter.ofPattern("E yyyyMMMdd", Locale.ENGLISH);
        System.out.println(formatter.format(now));
    }


    /**
     * 带有时区的时间
     */
    public static void testZoneId(){
        ZonedDateTime time = ZonedDateTime.of(LocalDate.now(), LocalTime.now(), ZoneId.systemDefault());
        System.out.println(time);
    }


    public static void testDateAdjuster(){
        LocalDate firstTuesday = LocalDate.of(2016, 10, 5).with(
                TemporalAdjusters.nextOrSame(DayOfWeek.TUESDAY));
        System.out.println(firstTuesday);

        TemporalAdjuster myOwnTemporalAdjuster = x -> {
            LocalDate date = (LocalDate) x;
            do
            {
                date = date.plusDays(1);
            }while (date.getDayOfWeek().getValue() <= 6);
            return date;
        };

        System.out.println(LocalDate.of(2016,10, 31).with(myOwnTemporalAdjuster));

    }


    /**
     * 时间的比较
     * Duration,Instant
     * @throws InterruptedException
     */
    public static void testDuration() throws InterruptedException {
        Instant start = Instant.now();
        Thread.sleep(2000);
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        //多个方法用来进行比较,计算
        System.out.println(timeElapsed.toMillis());


        LocalDate startDate = LocalDate.of(2016, 10, 1);
        LocalDate endDate = LocalDate.now();
        //Period
        System.out.println(endDate.until(startDate).getDays());


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
