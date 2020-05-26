

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 明辉 on 2017/7/30.
 * 14206
 * 1506  9685
 */
public class Main {

    private static final Integer[] EMPTY_ELEMENTDATA = {};

    private static int value = 0;

    //122
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    value++;
                }
            }).start();
        }

    }

    private void xx(int i) {

    }

    private String xx(int i, String j) {
        return "";
    }
}






