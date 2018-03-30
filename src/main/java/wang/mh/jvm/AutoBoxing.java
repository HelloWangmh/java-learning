package wang.mh.jvm;

public class AutoBoxing {
    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;
        System.out.println(c == d); //true
        System.out.println(e == f); //false
        System.out.println(c == (a+b)); //true
        System.out.println(c.equals(a + b)); //true
        System.out.println(g == (a + b)); //true   包装类在遇到算数运算的时候会自动拆箱
        System.out.println(g.equals(a + b)); //false


    }
}
