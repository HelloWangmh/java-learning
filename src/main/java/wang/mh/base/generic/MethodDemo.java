package wang.mh.base.generic;


import lombok.Data;

import java.time.LocalDate;
import java.util.function.Supplier;

public class MethodDemo {

    public static void main(String[] args) {
        LocalDate[] birthdays = new LocalDate[]{
                LocalDate.of(2008,8,8),
                LocalDate.of(1993,10,15),
                LocalDate.of(1994,12,31)
        };


        Pair<LocalDate> pair = ArrayFlag.minmax(birthdays);
        System.out.println(pair.getT1());
        System.out.println(pair.getT2());
    }
}
class ArrayFlag{
    public static <T extends Comparable>Pair<T> minmax(T[] a){
        T min = a[0];
        T max = a[0];

        for (int i = 0; i < a.length; i++) {
            if(min.compareTo(a[i])>0) min = a[i];
            if(max.compareTo(a[i])<0) max = a[i];
        }

        return new Pair<>(min,max);
    }
}
@Data
class Pair<T>{
    T t1;
    T t2;


    public Pair() {
    }

    public Pair(T t1, T t2) {
        this.t1 = t1;
        this.t2 = t2;
    }

    /**
     * java8写法
     * @param supplier
     * @param <T>
     * @return
     */
    public static <T> Pair<T> makePair(Supplier<T> supplier){
        return new Pair<T>(supplier.get(),supplier.get());
    }

    /**
     * 反射写法
     */
    public static <T> Pair<T> makePair(Class<T> tClass){
        try {
            return new Pair<T>(tClass.newInstance(),tClass.newInstance());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

class DataInterval extends Pair<LocalDate>{

    @Override
    public LocalDate getT1() {
        return super.getT1();
    }

    @Override
    public LocalDate getT2() {
        return super.getT2();
    }

    @Override
    public void setT1(LocalDate t1) {
        super.setT1(t1);
    }

    @Override
    public void setT2(LocalDate t2) {
        super.setT2(t2);
    }
}
