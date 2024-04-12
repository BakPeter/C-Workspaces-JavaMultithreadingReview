package exapmle3;

public class Main {
    public static void main(String[] args) {
        Thread thread = new Thread(new BlockingTask());
//        thread.setDaemon(true);
        thread.start();

        System.out.println(thread.isAlive());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        thread.interrupt();
        try {
            Thread.sleep(2000);
            System.out.println(thread.isAlive());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static class BlockingTask implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println("Starting blocking thread");
                Thread.sleep(500000);
            } catch (InterruptedException e) {
                System.out.println("Exiting blocking thread");
            }
        }

    }
}