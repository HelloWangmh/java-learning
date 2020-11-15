import lombok.Getter;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by 明辉 on 2017/7/30.
 * 14206
 * 1506  9685
 */
public class Main {
    public static void main(String[] args) throws Exception{
        CountDownLatch latch = new CountDownLatch(1000);
        Monitor monitor = new Monitor();
        for (int i = 0; i < 1000; i++) {
            final int count = i;
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    monitor.visit("www.baidu.com", 2);
                }
                latch.countDown();
            }).start();
        }
        latch.await();

        System.out.println(monitor.getMap().size());

        Map<Monitor.MonitorKey, Monitor.MonitorValue> map = monitor.getMap();
        for (Map.Entry<Monitor.MonitorKey, Monitor.MonitorValue> entry : map.entrySet()) {
            System.out.printf("key : %s, count : %d, avgTime : %f, totalTime : %d\n",
                    entry.getKey().url, entry.getValue().count.longValue(),
                    entry.getValue().avgTime, entry.getValue().totalTime.longValue());
        }
    }
}
@Getter
class Monitor {
    static class MonitorKey {
        String url;

        public MonitorKey(String url) {
            this.url = url;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MonitorKey that = (MonitorKey) o;
            return Objects.equals(url, that.url);
        }

        @Override
        public int hashCode() {
            return Objects.hash(url);
        }
    }

    static class MonitorValue {
        AtomicLong count = new AtomicLong();
        float avgTime;
        AtomicLong totalTime = new AtomicLong();
    }


    private Map<MonitorKey, MonitorValue> map = new ConcurrentHashMap<>();

      public void visit(String url, long timeCost) {
        MonitorKey key = new MonitorKey(url);
        map.putIfAbsent(key, new MonitorValue());
        MonitorValue value = map.get(key);
        long count = value.count.incrementAndGet();
        long totalTime = value.totalTime.addAndGet(timeCost);
        value.avgTime = (float) totalTime / (float) count;
    }

}






