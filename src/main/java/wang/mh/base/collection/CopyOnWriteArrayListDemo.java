package wang.mh.base.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by 明辉 on 2017/6/12.
 * 添加元素
 * 1、加锁

 2、拿到原数组，得到新数组的大小（原数组大小+1），实例化出一个新的数组来

 3、把原数组的元素复制到新数组中去

 4、新数组最后一个位置设置为待添加的元素（因为新数组的大小是按照原数组大小+1来的）

 5、把Object array引用指向新数组

 6、解锁
 */
public class CopyOnWriteArrayListDemo {
    public static void main(String[] args) {
        //List<Integer> list = new ArrayList<Integer>();
        List<Integer> list = new Vector<Integer>();
        //以上两个都会报异常java.util.ConcurrentModificationException
        //List<Integer> list = new CopyOnWriteArrayList<Integer>();
        for (int i = 0;i<10000;i++){
            list.add(i);
        }
        Th1 th1 = new Th1(list);
        Th2 th2 = new Th2(list);
        Thread t1 = new Thread(th1);
        Thread t2 = new Thread(th2);
        t1.start();
        t2.start();
        System.out.println();

    }

}
class Th1 implements Runnable{

    private List<Integer> list;

    public  Th1(List list){
        this.list = list;
    }

    public void run() {

        for (int i = 0;i<list.size();i++){

        }

        for (Integer i : list) {

        }
    }
}

class Th2 implements Runnable{

    private List<Integer> list;

    public  Th2(List list){
        this.list = list;
    }

    public void run() {
       for (int i = 0;i<list.size();i++){
           list.remove(i);
       }
    }
}
