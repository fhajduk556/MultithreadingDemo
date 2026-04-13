package Demo;

class Counter {
    public int count = 0;
    public void increment() {
        int temp = count;       // Odczyt
        // Symulacja opóźnienia, aby wymusić przełączenie kontekstu (Race Condition)
        try { Thread.sleep(1); } catch (InterruptedException e) {}
        count = temp + 1;       // Modyfikacja i Zapis

    }


}               // Metoda increment() nie jest atomowa

public class RaceConditionDemo {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        // Wyrażenie lambda generujące anonimową klasę implementującą interfejs Runnable w locie
        Runnable task = () -> {                    // można użyć wyrażenia lambda, ponieważ
            for (int i = 0; i < 100; i++) {        // Runnable to interfejs funkcyjny
                counter.increment();
            }
        };
        Thread threadA = new Thread(task, "Wątek-A");
        Thread threadB = new Thread(task, "Wątek-B");
        threadA.start();
        threadB.start();
        threadA.join();           // Czekamy na zakończenie obu wątków
        threadB.join();
        System.out.println("Oczekiwany wynik: 200");
        System.out.println("Faktyczny wynik:  " + counter.count);
    }
}




