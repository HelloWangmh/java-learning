package wang.mh.base.collection;

import java.util.BitSet;

public class BitSetDemo {
    public static void main(String[] args) {
        BitSet bs1 = new BitSet();
        bs1.set(1);

        BitSet bs2 = new BitSet();
        bs2.set(2);

        System.out.println(bs1.get(1));
        bs1.and(bs2);

        System.out.println(bs1.get(1));

    }
}
