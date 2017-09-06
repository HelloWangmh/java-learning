package wang.mh.base.grammer;

/**
 * Created by 明辉 on 2017/8/13.
 */
public class CommonDemo {
    public static final Dog RecommendContentClassification = new Dog() {
        public Integer getId() { return -1; };
        public String getName() { return "热门推荐"; };
    };

    public static void main(String[] args) {
        Dog dog = CommonDemo.RecommendContentClassification;
        System.out.println(dog.getId());
    }
}

class Dog{
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
