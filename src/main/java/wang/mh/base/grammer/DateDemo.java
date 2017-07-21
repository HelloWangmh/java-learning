package wang.mh.base.grammer;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 明辉 on 2017/7/6.
 */
public class DateDemo {

    public static void main(String[] args) {
        System.out.println( new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(new Date()));
    }
}
