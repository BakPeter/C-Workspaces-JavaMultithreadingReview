package example1;

public class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 1200; i++) {
            System.out.println(i + ": Thread -  " + this.getName() + ", Priority - " + this.getPriority());
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
