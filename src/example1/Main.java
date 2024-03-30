package example1;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("example1.Main Thread Started: " + Thread.currentThread().getName());
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println(i + ": Thread -  " + Thread.currentThread().getName() + ", Priority - " + Thread.currentThread().getPriority());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread1.setName("Worker thread 1");
        thread1.setPriority(Thread.MAX_PRIORITY);

        Thread thread2 = new Thread(new MyRunnable());
        thread2.setName("My Runnable thread");
        thread2.setPriority(Thread.MIN_PRIORITY);

        Thread thread3 = new Thread(() -> {
           throw new RuntimeException("Internal Exception");
        });
        thread3.setName("Misbehaving thread");
        thread3.setUncaughtExceptionHandler((t, e) -> {
            System.out.println("Uncaught Error int thread " + Thread.currentThread().getName());
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        });


        MyThread thread4 = new MyThread();
        thread4.setName("My class thread");

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        Thread.sleep(1000);
        System.out.println("example1.Main Thread Finished: " + Thread.currentThread().getName());
    }
}