package wang.mh.thread;

/**
 * Created by 明辉 on 2017/5/9.
 * 通过命令行查看java线程
 * jps  查看当前java所有进程
 * jstack pid 查看该进程下各个线程
 */
public class ThreadAnalyst {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++)
        {
            new Thread() {
                public void run()
                {
                    try
                    {
                        Thread.sleep(100000);
                    }
                    catch (Exception e)
                    {
                    }
                }
            }.start();
        }
        Thread t = new Thread() {
            public void run()
            {
                int i = 0;
                while (true)
                {
                    i = (i++) / 100;
                }
            }
        };
        t.setName("Busiest Thread");
        t.start();
    }
}
