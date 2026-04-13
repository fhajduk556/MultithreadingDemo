package Zadania;

import java.util.HashMap;
import java.util.Map;

public class Zad5 {
    /*
     * ZADANIE 5:
     * Ten program zlicza wejścia na różne podstrony serwisu.
     * Został napisany przez początkującego programistę. Kod ma dwie wady:
     * 1. Tworzy 5000 ręcznych wątków (new Thread) - to zabija wydajność
     * 2. Używa zwykłej HashMapy - przez Race Conditions gubimy część wejść.
     * * NAPRAW KOD:
     * 1. Zamień pętlę generującą "new Thread" na ExecutorService (FixedThreadPool)
     * o wielkości 10 wątków. Pamiętaj o zamknięciu puli (shutdown i awaitTermination).
     * 2. Zamień HashMap na strukturę z pakietu java.util.concurrent, która
     * zagwarantuje poprawne zliczenie wszystkich 5000 wejść (wyniki na końcu
     * powinny sumować się do równo 5000).
     */

    public static void main(String[] args) throws InterruptedException {
        // TODO: Zmień strukturę danych
        Map<String, Integer> pageVisits = new HashMap<>();
        pageVisits.put("Home", 0);
        pageVisits.put("Kontakt", 0);

        long startTime = System.currentTimeMillis();

        // TODO: Usuń tablicę wątków i użyj ExecutorService
        Thread[] threads = new Thread[5000];

        for (int i = 0; i < 5000; i++) {
            final int index = i;
            threads[i] = new Thread(() -> {
                String page = (index % 2 == 0) ? "Home" : "Kontakt";

                // UWAGA: Tradycyjne put(get+1) w ConcurrentHashMapie też może zgubić dane!
                // Wskazówka: Zbadaj metodę map.merge() albo użyj bloku synchronized.
                Integer current = pageVisits.get(page);
                pageVisits.put(page, current + 1);
            });
            threads[i].start();
        }

        for (Thread t : threads) {
            t.join();
        }

        long endTime = System.currentTimeMillis();

        System.out.println("Wejścia Home: " + pageVisits.get("Home"));
        System.out.println("Wejścia Kontakt: " + pageVisits.get("Kontakt"));
        System.out.println("Suma całkowita: " + (pageVisits.get("Home") + pageVisits.get("Kontakt")) + " (Oczekiwano: 5000)");
        System.out.println("Czas wykonania: " + (endTime - startTime) + " ms");
    }
}
