

/**
 * Created by 明辉 on 2017/7/30.
 */
public class Main {

    static char a = 65535;

    public static void main(String[] args) {
        String s1 = "abc1";
        int a = 1;
        String s2 = "abc"  + a;
        System.out.println(s1 == s2);
        s2 = s2.intern();
        System.out.println(s1 == s2);
        System.out.println(s2);

    }

}

class p {

}
class q {

}


