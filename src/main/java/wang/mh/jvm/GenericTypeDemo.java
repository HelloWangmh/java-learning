package wang.mh.jvm;

import java.util.List;

/**
 * Java中的泛型是伪泛型,它只在程序源码中存在,编译后的字节码文件中,就已经替换为原来的
 *  原生类型了(裸类型),并在相应的地方插入了强制转型代码
 */
public class GenericTypeDemo {
    public static void main(String[] args) {

    }


//    public void method(List<String> list) {  编译报错
//    }
//

    public void method(List<Integer> list) {
    }
}
