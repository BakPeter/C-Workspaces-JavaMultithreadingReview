package thread.joining;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Long> inputNumbers = Arrays.asList(100000000L, 3435L, 35435L, 2324L, 4656L, 23L, 5556L);

        List<FactorialThread> threads = new ArrayList<>();
        for (Long inputNumber : inputNumbers) {
            threads.add(new FactorialThread(inputNumber));
        }

        for (FactorialThread thread : threads) {
            thread.setDaemon(true);
            thread.start();

            try {
                thread.join(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            for (int i = 0; i < inputNumbers.size(); i++) {
                FactorialThread factorialThread = threads.get(i);
                if (factorialThread.isFinished()) {
                    System.out.println("Factorial of " + inputNumbers.get(i) + " is " + factorialThread.getResult());
                } else {
                    System.out.println("The calculation for " + inputNumbers.get(i) + " is still in progress");
                }
            }
        }
    }
}
