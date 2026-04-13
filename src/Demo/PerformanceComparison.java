package Demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PerformanceComparison {
    private static final int TASKS_COUNT = 1000;

    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            double result = Math.tan(Math.atan(Math.tan(Math.atan(1.0))));      // Symulacja krótkiej pracy
        };

        long startManual = System.currentTimeMillis();             // TEST 1: ręczne tworzenie wątków (new Thread)
        Thread[] threads = new Thread[TASKS_COUNT];

        for (int i = 0; i < TASKS_COUNT; i++) {
            threads[i] = new Thread(task);
            threads[i].start();
        }
        for (Thread t : threads) {
            t.join(); // Czekamy na każdy wątek
        }
        long endManual = System.currentTimeMillis();
        System.out.println("Czas (new Thread): " + (endManual - startManual) + " ms");

        long startPool = System.currentTimeMillis();                // TEST 2: użycie Puli Wątków (ThreadPool)
        ExecutorService executor = Executors.newFixedThreadPool(10);    //pula wątków do obsłużenia 1000 zadań

        for (int i = 0; i < TASKS_COUNT; i++) {
            executor.execute(task);
        }
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        long endPool = System.currentTimeMillis();
        System.out.println("Czas (ThreadPool): " + (endPool - startPool) + " ms");
    }
}

