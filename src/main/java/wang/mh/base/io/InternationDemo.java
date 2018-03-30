package wang.mh.base.io;

import com.google.common.collect.Lists;

import java.nio.charset.Charset;
import java.text.Collator;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;
import java.util.ResourceBundle;

public class InternationDemo {

    public static void main(String[] args) throws ParseException {
        testResourceBundle();
    }


    public static void testResourceBundle(){
        ResourceBundle bundle = ResourceBundle.getBundle("MyProgramStrings", Locale.getDefault());
        System.out.println(bundle.getString("name"));
    }


    /**
     * MessageFormat
     */
    public static void testMessageFormat(){
        String s = "On {2,date,long}, a {0} destroyed {1} houses and caused {3,number,currency} of damage.";
        System.out.println(MessageFormat.format(s, "hurricane", 100,
                System.currentTimeMillis(), 10000));
    }

    /**
     *compare
     */
    public static void testCollation(){
        Collator coll = Collator.getInstance(Locale.CHINA);
        coll.setStrength(Collator.TERTIARY);
        ArrayList<String> list = Lists.newArrayList("abc", "aaa", "AAA", "bbb");
        list.sort(coll);
        System.out.println(list);
    }


    /**
     * Number Formatter    number,currency,percentage
     */
    public static void testNumber() throws ParseException {
        Locale loc = Locale.GERMAN;
        NumberFormat currFmt = NumberFormat.getCurrencyInstance(loc);
        double amt = 123456.78;
        System.out.println(currFmt.format(amt));
        currFmt.setCurrency(Currency.getInstance("CNY"));
        System.out.println(currFmt.format(amt));
        System.out.println(currFmt.parse("CNY 123,456.78"));

    }


    /**
     * Local
     */
    public static void testLocal(){
        Locale china = Locale.CHINA;
        System.out.println(china);
        System.out.println(china.getDisplayName());
        System.out.println(china.getDisplayName(Locale.ENGLISH));
    }
}
