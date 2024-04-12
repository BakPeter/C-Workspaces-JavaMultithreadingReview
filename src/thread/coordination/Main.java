package thread.coordination;

import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        Thread thread = new Thread((new LongComputationTask(new BigInteger("200000"), new BigInteger("3000000"))));
        thread.start();

        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

//        thread.interrupt();
    }

    private static class LongComputationTask implements Runnable {
        private final BigInteger _base;
        private final BigInteger _power;

        private LongComputationTask(BigInteger base, BigInteger power) {
            this._base = base;
            this._power = power;
        }

        @Override
        public void run() {
            System.out.println(_base + "^" + _power + " = " + pow(_base, _power));
        }

        private BigInteger pow(BigInteger base, BigInteger power) {
            BigInteger result = BigInteger.ONE;
            for (BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Prematurely interrupted computation");
                    return BigInteger.ZERO;
                }
                result = result.multiply(base);
            }
            return result;
        }
    }
}