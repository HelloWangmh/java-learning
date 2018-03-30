package wang.mh.tool;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;

import java.util.concurrent.TimeUnit;

public class MetricsDemo {
    private static final MetricRegistry metrics = new MetricRegistry();
    private static final Meter requests = metrics.meter("requests");

    static {
        ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();
        reporter.start(1, TimeUnit.SECONDS);
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            TimeUnit.MILLISECONDS.sleep(100);
            genHistogram(i);
        }
    }

    static void genHistogram(int i) {
        metrics.histogram("myHistogram").update(i);
    }

    public static void handleRequest() {
        requests.mark();
    }
}
