package wang.mh.thread.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 明辉 on 2017/7/23.
 */
public class CountDownLatchDemo {
    public static void main(String[] args) {
        boolean result = false;
        try {
            result = ApplicationStartupUtil.checkExternalServices();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("External services validation completed !! Result was :: "+ result);
    }

}

class ApplicationStartupUtil{
    private static List<BaseHealthChecker> _services;

    //This latch will be used to wait on
    private static CountDownLatch _latch;

    private ApplicationStartupUtil()
    {
    }

    private final static ApplicationStartupUtil INSTANCE = new ApplicationStartupUtil();

    public static ApplicationStartupUtil getInstance()
    {
        return INSTANCE;
    }

    public static boolean checkExternalServices() throws Exception
    {
        //Initialize the latch with number of service checkers
        _latch = new CountDownLatch(3);

        //All add checker in lists
        _services = new ArrayList<BaseHealthChecker>();
        _services.add(new NetworkHealthChecker(_latch));
        _services.add(new CacheHealthChecker(_latch));
        _services.add(new DatabaseHealthChecker(_latch));

        //Start service checkers using executor framework
        ExecutorService executor = Executors.newFixedThreadPool(_services.size());

        for(final BaseHealthChecker v : _services)
        {
            executor.execute(v);
        }

        //Now wait till all services are checked
        _latch.await();
        executor.shutdown();
        //Services are file and now proceed startup
        for(final BaseHealthChecker v : _services)
        {
            if( ! v.isServiceUp())
            {
                System.out.println(v.getServiceName());
                return false;
            }
        }
        return true;
    }
}

abstract class BaseHealthChecker  implements Runnable{
    private CountDownLatch _latch;
    private String _serviceName;
    private boolean _serviceUp;

    //Get latch object in constructor so that after completing the task, thread can countDown() the latch
    public BaseHealthChecker(String serviceName, CountDownLatch latch)
    {
        super();
        this._latch = latch;
        this._serviceName = serviceName;
        this._serviceUp = false;
    }

    @Override
    public void run() {
        try {
            verifyService();
            _serviceUp = true;
        } catch (Throwable t) {
            t.printStackTrace(System.err);
            _serviceUp = false;
        } finally {
            if(_latch != null) {
                _latch.countDown();
            }
        }
    }

    public String getServiceName() {
        return _serviceName;
    }

    public boolean isServiceUp() {
        return _serviceUp;
    }
    public CountDownLatch getLatch(){
        return _latch;
    }
    //This methos needs to be implemented by all specific service checker
    public abstract void verifyService();
}

class CacheHealthChecker   extends BaseHealthChecker{
    public CacheHealthChecker (CountDownLatch latch)  {
        super("CacheHealthChecker", latch);
    }

    @Override
    public void verifyService()
    {
        System.out.println("Checking " + this.getServiceName());
        try
        {
            Thread.sleep(2000);
            this.getLatch().countDown();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println(this.getServiceName() + " is UP");
    }
}
class DatabaseHealthChecker   extends BaseHealthChecker{
    public DatabaseHealthChecker (CountDownLatch latch)  {
        super("DatabaseHealthChecker ", latch);
    }

    @Override
    public void verifyService()
    {
        System.out.println("Checking " + this.getServiceName());
        try
        {
            Thread.sleep(3000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println(this.getServiceName() + " is UP");
    }
}
class NetworkHealthChecker   extends BaseHealthChecker{
    public NetworkHealthChecker (CountDownLatch latch)  {
        super("Network Service", latch);
    }

    @Override
    public void verifyService()
    {
        System.out.println("Checking " + this.getServiceName());
        try
        {
            Thread.sleep(4000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println(this.getServiceName() + " is UP");
    }
}
