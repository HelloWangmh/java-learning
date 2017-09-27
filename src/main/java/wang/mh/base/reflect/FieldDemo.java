package wang.mh.base.reflect;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 明辉 on 2017/7/4.
 */
public class FieldDemo {


    public static void main(String[] args) throws IllegalAccessException {
        MyField field = new MyField();
        field.setAge(10);
        field.setName("嘿嘿嘿");
        field.setList(Lists.newArrayList());
        Map<String, Object> map = field.getMap();
        for (String s : map.keySet()) {
            System.out.println(s + "--->" + map.get(s));
        }
    }
}
@Data
class MyField{
    private Integer age;

    private String name;

    private List<Integer> list;




    public Map<String,Object> getMap() throws IllegalAccessException {
        //getDeclaredFields可以获得所有属性   getFields只能获得私有属性
        Field[] fields = this.getClass().getDeclaredFields();
        HashMap<String, Object> map = Maps.newHashMap();

        for (Field field : fields) {
            map.put(field.getName(),field.get(this));
        }

        return map;
    }

}
