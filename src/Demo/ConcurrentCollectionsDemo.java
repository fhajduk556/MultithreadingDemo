package Demo;

import java.util.*;
import java.util.concurrent.*;

public class ConcurrentCollectionsDemo {
    public static void main(String[] args) throws InterruptedException {
        //Map<String, Integer> map = new ConcurrentHashMap<>();
        Map<String, Integer> map = new HashMap<>();


        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            executor.execute(() -> {
                map.put("Klucz-" + finalI, finalI);
            });
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        System.out.println("Rozmiar mapy: " + map.size()); // Zawsze 1000 przy ConcurrentHashMap
    }
}

