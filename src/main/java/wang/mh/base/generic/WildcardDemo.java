package wang.mh.base.generic;

import wang.mh.common.Father;
import wang.mh.common.Son;

/**
 * 通配符类型
 * ? extends    可以使用get方法即使用返回值不可以set
 * 因为 编译器只知道需要某个Father的子类型 但是不知道具体的类型,所有拒绝传递任何具体类型
 *
 * ? super 带有超类型限定符的可以为方法提供参数,但是不能使用返回值
 * 因为提供参数很明确,只要是其父类就可以  但是取出来的类型不确定,是Object
 *
 *直观的讲,超类型限定符的通配符可以向限定符对象写入,带有子类型通配符的可以从泛型对象中取数据
 *
 * 无限定通配符只能get  不能set
 *
 */
public class WildcardDemo {

    public static void main(String[] args) {
        //Pair<Son> 是Pair<? extends Father>的子类型
        printPair(new Pair<Son>());

        Pair<Son> sons = new Pair<>(new Son(),new Son());
        Pair<? extends Father> fathers = sons;//true
        Father father = fathers.getT1();//true
        //wrong  fathers.setT1(new Son());


        Pair<? super Father> sonSuper = new Pair<>(new Son(),new Father());
        sonSuper.setT1(new Father());
        sonSuper.setT2(new Father());
        Father t1 = (Father) sonSuper.getT1();


        Pair<?> pair = new Pair<>();
        //pair.setT1(new Object()); //error
        Object t11 = pair.getT1();


    }


    public static void printPair(Pair<? extends Father> pair){
        System.out.println(pair.getT1());
    }

    public static boolean isNUll(Pair<?> pair){
        swapHelper(pair);
        return pair.getT1() == null;
    }


    public static void swap(Pair<?> pair){
        swapHelper(pair);
    }

    public static <T> void swapHelper(Pair<T> p){
        T t1 = p.getT1();
        p.setT1(p.getT2());
        p.setT1(p.getT2());
    }

}

