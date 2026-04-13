package Demo;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
    // Tworzymy semafor z 3 pozwoleniami (3 miejsca w czytelni)
    private static final Semaphore readingRoom = new Semaphore(3);

    public static void main(String[] args) {
        for (int i = 1; i <= 6; i++) {
            final int readerId = i;
            new Thread(() -> {
                try {
                    System.out.println("Czytelnik " + readerId + " czeka przed czytelnią...");

                    readingRoom.acquire(); // Pobranie pozwolenia

                    System.out.println(">>> Czytelnik " + readerId + " wszedł i czyta.");
                    Thread.sleep((long) (Math.random() * 3000)); // Symulacja czytania

                    System.out.println("<<< Czytelnik " + readerId + " wychodzi i zwalnia miejsce.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    readingRoom.release(); // ZWOLNIENIE pozwolenia - kluczowe
                }
            }).start();
        }
    }
}

