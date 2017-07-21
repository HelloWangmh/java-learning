package wang.mh.base.grammer;

/**
 * Created by 明辉 on 2017/7/4.
 */
public class StaticDemo {
    public static void main(String[] args) {
        String s1 = StaticTest.getString();
        String s2 = StaticTest.getString();
        System.out.println(s1==s2);//false
        System.out.println("========");
        System.out.println(StaticTest.s == StaticTest.s); //true
    }
}
class StaticTest{

    public static  String s  = new String();

    static {
        System.out.println(s.getClass());
    }

    public static String getString(){
        System.out.println("getString");
        return  new String();
    }
}
