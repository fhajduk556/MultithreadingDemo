package Zadania;

public class Zad2 {
    /*
     * ZADANIE 2:
     * Poniższy kod symuluje konto bankowe. 100 wątków wpłaca po 10 zł, a kolejne
     * 100 wątków wypłaca po 10 zł. Oczekiwane saldo końcowe to 0.
     * * PROBLEM: Gdy uruchomisz ten kod, saldo końcowe prawie nigdy nie wynosi 0.
     * Występuje tu tzw. Wyścig (Race Condition).
     * * NAPRAW KOD: Użyj odpowiedniego mechanizmu synchronizacji w klasie BankAccount,
     * aby upewnić się, że operacje wpłaty i wypłaty są bezpieczne wątkowo.
     * Spróbuj zsynchronizować tylko niezbędny blok kodu lub całe metody.
     */

    static class BankAccount {
        private int balance = 0;

        public void deposit(int amount) {
            int current = balance;
            try { Thread.sleep(1); } catch (InterruptedException e) {} // Symulacja opóźnienia bazy danych
            balance = current + amount;
        }

        public void withdraw(int amount) {
            int current = balance;
            try { Thread.sleep(1); } catch (InterruptedException e) {}
            balance = current - amount;
        }

        public int getBalance() {
            return balance;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BankAccount account = new BankAccount();
        Thread[] threads = new Thread[200];

        for (int i = 0; i < 100; i++) {
            threads[i] = new Thread(() -> account.deposit(10));
            threads[i+100] = new Thread(() -> account.withdraw(10));
        }

        for (Thread t : threads) t.start();
        for (Thread t : threads) t.join();

        System.out.println("Oczekiwane saldo: 0");
        System.out.println("Faktyczne saldo: " + account.getBalance());
    }
}