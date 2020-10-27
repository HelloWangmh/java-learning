import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * Created by 明辉 on 2017/7/30.
 * 14206
 * 1506  9685
 */
public class Main {
    public static void main(String[] args) throws Exception{

    }

    private static int concert(String s) {
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int num = Character.getNumericValue(c);
            System.out.println(1 <<(num - 1));
            result = result + (1 <<(num - 1));
        }
        return result;
    }

}


interface Per {
    static void xx() {
        System.out.println("hello");
    }
}



