package Zadania;

public class Zad3 {
    /*
     * ZADANIE 3:
     * Mamy system transferu pieniędzy między dwoma kontami. Wątek 1 przelewa z A do B,
     * a Wątek 2 w tym samym czasie przelewa z B do A.
     * Każdy przelew wymaga zablokowania obu kont (żeby nikt inny ich nie modyfikował).
     * * PROBLEM: Kod powoduje klasyczne zakleszczenie (Deadlock). Program zawiesza się.
     * * NAPRAW KOD:
     * Sposób 1 (Klasyczny): Wymuś stałą kolejność blokowania obiektów (np. zawsze
     * blokuj konto z mniejszym ID jako pierwsze).
     * Sposób 2 (Nowoczesny): Zmień Object na ReentrantLock i użyj metody tryLock()
     * w pętli.
     */

    static class Account {
        final int id;
        int balance = 1000;
        public Account(int id) { this.id = id; }
    }

    public static void transfer(Account from, Account to, int amount) {
        synchronized (from) {
            System.out.println(Thread.currentThread().getName() + " zablokował konto: " + from.id);
            try { Thread.sleep(50); } catch (InterruptedException e) {} // Wymuszenie deadlocka

            synchronized (to) {
                from.balance -= amount;
                to.balance += amount;
                System.out.println(Thread.currentThread().getName() + " wykonał przelew.");
            }
        }
    }

    public static void main(String[] args) {
        Account acc1 = new Account(1);
        Account acc2 = new Account(2);

        Thread t1 = new Thread(() -> transfer(acc1, acc2, 100), "Wątek-A");
        Thread t2 = new Thread(() -> transfer(acc2, acc1, 50), "Wątek-B");

        t1.start();
        t2.start();
    }
}
