package wang.mh.thread.core_learn;

/**
 * 测试操作的原子性
 */
public class AtomicDemo {


    public static void main(String[] args) {

    }


    private static void testAtomicBoolean(){
        AtomicBoolean atomicBoolean = new AtomicBoolean();

        for (int i = 0; i < 100; i++) {

        }
    }
}


class AtomicBoolean{

    private Boolean flag = false;

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }
}
