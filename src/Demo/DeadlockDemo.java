package Demo;

public class DeadlockDemo {
    // Dwa zasoby, o które będą "walczyć" wątki
    public static final Object LockA = new Object();
    public static final Object LockB = new Object();

    public static void main(String[] args) {

        // Wątek 1: Chce najpierw LockA, potem LockB
        Thread thread1 = new Thread(() -> {
            synchronized (LockA) {
                System.out.println("Wątek 1: Trzymam LockA...");

                try { Thread.sleep(50); } catch (InterruptedException e) {}

                System.out.println("Wątek 1: Czekam na LockB...");
                synchronized (LockB) {
                    System.out.println("Wątek 1: Mam oba locki!");
                }
            }
        }, "Wątek-1");

        // Wątek 2: Chce najpierw LockB, potem LockA (odwrotna kolejność!)
        Thread thread2 = new Thread(() -> {
            synchronized (LockB) {
                System.out.println("Wątek 2: Trzymam LockB...");

                try { Thread.sleep(50); } catch (InterruptedException e) {}

                System.out.println("Wątek 2: Czekam na LockA...");
                synchronized (LockA) {
                    System.out.println("Wątek 2: Mam oba locki!");
                }
            }
        }, "Wątek-2");

        thread1.start();
        thread2.start();
    }
}