package wang.mh.thread.core_learn;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 竞争条件的一个例子
 *  accounts[to] += amount;  这个语句可能被处理如下:
 *  1.将accounts[to]加载到寄存器
 *  2.增加amount
 *  3.将结果协会accounts[to]
 *  加入1,2执行完,其他线程开始执行,等再次执行3的时候可能金额就不正确了
 */

public class AccountTransferDemo {


    private static final int NACCOUNTS = 100;
    private static final double INITIAL_BALANCE0 = 100;
    private static final double MAX_AMOUNT = 100;
    private static final int DELAY = 10;

    private static  AtomicInteger atomicInteger = new AtomicInteger();

    public static void main(String[] args) {
        SynBank bank = new SynBank(NACCOUNTS,INITIAL_BALANCE0);  //8850 ms
        //Bank bank = new Bank(NACCOUNTS,INITIAL_BALANCE0);   //5782 ms
        //LockBank bank = new LockBank(NACCOUNTS,INITIAL_BALANCE0);   //8752 ms
        long start = System.currentTimeMillis();
        for (int i = 0; i < NACCOUNTS; i++) {
            int fromAccount = i;
            Runnable r = () ->{
                try {
                    while (true){
                        int toAccount = (int)(bank.size() * Math.random());
                        double amount = MAX_AMOUNT * Math.random();
                        bank.transfer(fromAccount,toAccount,amount);
                        Thread.sleep((int)(DELAY * Math.random()));
                        if(atomicInteger.incrementAndGet()==100000){
                            long end = System.currentTimeMillis();
                            System.out.printf("耗时: %d s",(end - start));
                            System.exit(0);
                        }
                    }
                }catch (InterruptedException e){

                }
            };
            new Thread(r).start();
        }
    }
}

class Bank{

    private final double[] accounts;

    /**
     * 初始化账户
     * @param n
     * @param initialBalacnce
     */
    public Bank(int n,double initialBalacnce){
        accounts = new double[n];
        Arrays.fill(accounts,initialBalacnce);
    }

    /**
     * 转账
     *
     */
    public void transfer(int from,int to,double amount){
        if(accounts[from] < amount) return;
        System.out.print(Thread.currentThread());
        accounts[from] -= amount;
        System.out.printf(" %10.2f from %d to %d",amount,from,to);
        accounts[to] += amount;
        System.out.printf("Total Balance : %10.2f%n",getTotal());

    }

    /**
     * 总金额
     * @return
     */
    public double getTotal(){
        double sum = 0;
        for (double account : accounts) {
            sum += account;
        }
        return sum;
    }


    public int size(){
        return accounts.length;
    }


}


/**
 *ReentrantLock
 */
class LockBank{

    private final double[] accounts;

    private Lock banLock;

    private Condition condition;

    /**
     * 初始化账户
     * @param n
     * @param initialBalacnce
     */
    public LockBank(int n,double initialBalacnce){
        accounts = new double[n];
        Arrays.fill(accounts,initialBalacnce);
        banLock = new ReentrantLock();
        condition  = banLock.newCondition();
    }

    /**
     * 转账
     *
     */
    public void transfer(int from,int to,double amount) throws InterruptedException {
        banLock.lock();

        try {
            while(accounts[from] < amount) condition.await();
            System.out.print(Thread.currentThread());
            accounts[from] -= amount;
            System.out.printf(" %10.2f from %d to %d",amount,from,to);
            accounts[to] += amount;
            System.out.printf("Total Balance : %10.2f%n",getTotal());
            condition.signalAll();
        }
        finally {
            banLock.unlock();
        }

    }

    /**
     * 总金额
     * @return
     */
    public double getTotal(){
        banLock.lock();
        try{

            double sum = 0;
            for (double account : accounts) {
                sum += account;
            }
            return sum;
        }finally {
            banLock.unlock();
        }
    }


    public int size(){
        return accounts.length;
    }


}


/**
 *synchronized
 */
class SynBank{

    private final double[] accounts;



    /**
     * 初始化账户
     * @param n
     * @param initialBalacnce
     */
    public SynBank(int n,double initialBalacnce){
        accounts = new double[n];
        Arrays.fill(accounts,initialBalacnce);

    }

    /**
     * 转账
     *
     */
    public synchronized void transfer(int from,int to,double amount) throws InterruptedException {

            while(accounts[from] < amount) wait();
            System.out.print(Thread.currentThread());
            accounts[from] -= amount;
            System.out.printf(" %10.2f from %d to %d",amount,from,to);
            accounts[to] += amount;
            System.out.printf("Total Balance : %10.2f%n",getTotal());
            notifyAll();
    }

    /**
     * 总金额
     * @return
     */
    public synchronized double getTotal(){

            double sum = 0;
            for (double account : accounts) {
                sum += account;
            }
            return sum;
    }


    public int size(){
        return accounts.length;
    }


}

