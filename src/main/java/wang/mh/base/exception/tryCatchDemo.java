package wang.mh.base.exception;

/**
 * Created by 明辉 on 2017/5/9.
 * 测试执行try catch的开销
 */
public class tryCatchDemo {

    public static void main(String[] args) {
        String s = "hello,world";
        Integer max = Integer.MAX_VALUE/10;
        long start = System.currentTimeMillis();
        for(int i = 0;i<max;i++){
            if(s!=null){
                i++;
            }
        }

        long end = System.currentTimeMillis();
        System.out.println("提前判断"+(end-start));
        for(int i = 0;i<max;i++){
          try {
              i++;
          }catch (Exception e){
              e.printStackTrace();
          }
        }

        long end2 = System.currentTimeMillis();
        System.out.println("try catch"+(end2-end));

    }
}
