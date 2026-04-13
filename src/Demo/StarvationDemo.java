package Demo;

public class StarvationDemo {
    private static final Object lock = new Object();

    public static void main(String[] args) {
        // Wątek "Chciwy" - ma wysoki priorytet i trzyma blokadę bardzo długo
        Thread greedyThread = new Thread(() -> {
            int workCycles = 0;
            while (true) {
                synchronized (lock) {
                    workCycles++;
                    System.out.println("Wątek VIP: Wykonuję cykl pracy nr " + workCycles);

                    // Symulacja ciężkiej pracy wewnątrz sekcji krytycznej
                    try { Thread.sleep(100); } catch (InterruptedException e) {}
                }
            }
        }, "VIP-Wątek");

        // Wątek "Głodny" - ma niski priorytet i próbuje uzyskać dostęp do tego samego locka
        Thread starvingThread = new Thread(() -> {
            long startTime = System.currentTimeMillis();
            while (true) {
                synchronized (lock) {
                    long waitTime = System.currentTimeMillis() - startTime;
                    System.out.println(">>> GŁODNY WĄTEK:  dostęp po " + waitTime + "ms");

                    startTime = System.currentTimeMillis();
                }
            }
        }, "Głodny-Wątek");

        // Ustawienie ekstremalnych priorytetów
        greedyThread.setPriority(Thread.MAX_PRIORITY); // 10
        starvingThread.setPriority(Thread.MIN_PRIORITY); // 1

        System.out.println("symulacja zagłodzenia...");
        starvingThread.start();
        greedyThread.start();
    }
}

