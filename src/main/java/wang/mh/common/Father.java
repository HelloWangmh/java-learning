package wang.mh.common;

import lombok.Data;

/**
 * Created by 明辉 on 2017/9/6.
 */
@Data

public class Father {
    private String name;
    private Integer age;



    public Father(Integer age){
       this.age = age;
    }

    public Father(){

    }
}

