package wang.mh.base.collection;

import com.google.common.collect.Sets;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * 有序集合
 * 在对集合遍历时候,每个值都是按照自然排序后的顺序呈现的
 */
public class TreeSetDemo {

    public static void main(String[] args) {
        //需要实现Comparable接口
        TreeSet<Item> parts = Sets.newTreeSet();
        parts.add(new Item("wang",15));
        parts.add(new Item("ming",11));
        parts.add(new Item("hui",90));

        System.out.println(parts);

        //第二种方式
        TreeSet<Item2> parts2 =   Sets.newTreeSet(Comparator.comparing(Item2::getAge));
        parts2.add(new Item2(2));
        parts2.add(new Item2(1));
        parts2.add(new Item2(20));
        System.out.println(parts2);
    }
}
@Getter
@Setter
@ToString
class Item implements Comparable<Item>{

    private String description;

    private int partNumber;


    public Item(String description, int partNumber) {
        this.description = description;
        this.partNumber = partNumber;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (partNumber != item.partNumber) return false;
        return description != null ? description.equals(item.description) : item.description == null;
    }

    @Override
    public int hashCode() {
        int result = description != null ? description.hashCode() : 0;
        result = 31 * result + partNumber;
        return result;
    }

    @Override
    public int compareTo(Item o) {
        int diff = Integer.compare(partNumber,o.getPartNumber());
        return diff != 0 ? diff : description.compareTo(o.getDescription());
    }
}
@Setter
@Getter
@ToString
class Item2{
    private Integer age;

    public Item2(Integer age) {
        this.age = age;
    }
}