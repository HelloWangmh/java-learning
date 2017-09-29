package wang.mh.base.generic;

import wang.mh.common.Father;
import wang.mh.common.Son;

/**
 * 无论S与T什么关系   pair<S>与Pair<T>是没什么关系的
 */
public class ExtendsRule {

    public static void main(String[] args) {
        Pair<Son> sons = new Pair<>(new Son(),new Son());
        //error
        //Pair<Father> fathers = sons;
        //以上换成数组是可以的


        Pair pair = sons;//OK
    }
}
