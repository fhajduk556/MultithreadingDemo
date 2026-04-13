package Zadania;

import java.util.concurrent.Semaphore;

public class Zad4 {
    /*
     * ZADANIE 4:
     * Wyobraź sobie, że piszesz system pobierania plików, ale serwer pozwala na
     * maksymalnie 3 jednoczesne pobierania z jednego adresu IP.
     * * Mamy 10 wątków, które chcą rozpocząć pobieranie w tym samym czasie.
     * * NAPISZ KOD:
     * Użyj klasy java.util.concurrent.Semaphore, aby upewnić się, że w sekcji
     * "POBIERANIE TRWA" znajdują się maksymalnie 3 wątki naraz.
     * Pamiętaj o obsłudze wyjątków i ZWOLNIENIU semafora (release),
     * nawet jeśli pobieranie się nie uda!
     */

    // TODO: Zadeklaruj semafor tutaj

    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            final int taskId = i;
            new Thread(() -> {
                System.out.println("Zadanie " + taskId + " czeka na miejsce w kolejce...");

                // TODO: Pobierz pozwolenie z semafora

                try {
                    System.out.println(">>> Zadanie " + taskId + " POBIERANIE TRWA");
                    Thread.sleep(2000); // Symulacja pracy
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("<<< Zadanie " + taskId + " zakończyło pobieranie.");
                    // TODO: Zwolnij pozwolenie!
                }
            }).start();
        }
    }
}
