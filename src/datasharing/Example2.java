package datasharing;

import java.util.Random;

public class Example2 {
    public static void run() {
        Metrics metrics = new Metrics();

        BusinessLogic businessLogic1 = new BusinessLogic(metrics);
        BusinessLogic businessLogic2 = new BusinessLogic(metrics);

        MetricsPrinter metricsPrinter = new MetricsPrinter(metrics);

        businessLogic1.start();
        businessLogic2.start();
        metricsPrinter.start();
    }

    public static class MetricsPrinter extends Thread {
        private final Metrics metrics;

        public MetricsPrinter(Metrics metrics) {
            this.metrics = metrics;
        }

        @Override
        public void run() {
            while(true){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Current Average is " + metrics.getAverage());
            }
        }
    }

    public static class BusinessLogic extends Thread {
        private final Metrics metrics;
        private final Random random;

        public BusinessLogic(Metrics metrics) {
            this.metrics = metrics;
            this.random = new Random();
        }

        @Override
        public void run() {
            while (true) {
                long startTme = System.currentTimeMillis();

                try {
                    Thread.sleep(this.random.nextInt(10));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                long endTime = System.currentTimeMillis();

                metrics.addSample(endTime - startTme);
            }
        }
    }

    public static class Metrics {
        private long count = 0;
        private volatile double average = 0.0;

        public synchronized void addSample(long sample) {
            double currentSum = average * count;
            count++;
            average = (currentSum + sample) / count;
        }

        public double getAverage() {
            return average;
        }
    }
}
