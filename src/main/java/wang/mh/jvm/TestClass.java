package wang.mh.jvm;

public class TestClass {

    public static void main(String[] args) {
        TestClass test = new TestClass();
        System.out.println(test.incException());
    }

    private double m;

    public double inc() {
        return m + 1;
    }

    public static double incStatic() {
        return 2 + 1;
    }

    public int incException() {
        int x;
        try {
            x = 1;
            return x;
        } catch (Exception e) {
            x = 2;
            return x;
        } finally {
            x = 3;
            return x;
        }
    }
}
