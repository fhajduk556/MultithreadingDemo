package Zadania;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Zad6 {
    /*
     * ZADANIE 6
     * Zbuduj symulację kuchni w restauracji wykorzystując wzorzec Producent-Konsument.
     * * KELNERZY (Producenci) zbierają zamówienia i kładą je na "ladzie".
     * KUCHARZE (Konsumenci) biorą zamówienia z "lady" i je przygotowują.
     * * WYMAGANIA:
     * 1. Użyj ArrayBlockingQueue<String> o pojemności maksymalnie 5 zamówień (to nasza lada).
     * Jeśli lada jest pełna, Kelner musi czekać. Jeśli jest pusta, Kucharz musi czekać.
     * 2. Zaimplementuj klasę Waiter (implementuje Runnable). Kelner ma wygenerować
     * 10 zamówień (np. w pętli string "Pizza nr 1", "Pizza nr 2") i wrzucić je na
     * kolejkę używając metody .put().
     * 3. Zaimplementuj klasę Chef (implementuje Runnable). Kucharz działa w nieskończonej
     * pętli while(true). Pobiera zamówienie używając metody .take() i symuluje
     * gotowanie (Thread.sleep(500)).
     * 4. W metodzie main stwórz pulę wątków (ExecutorService). Uruchom w niej
     * 2 Kelnerów i 3 Kucharzy.
     * 5. Spróbuj wymyślić, jak zakończyć program po wydaniu 20 zamówień
     * (wskazówka: użyj executor.shutdownNow() po czasie).
     */

    // TODO: Zadeklaruj statyczną kolejkę BlockingQueue tutaj

    // TODO: Zaimplementuj klasę Waiter
    static class Waiter implements Runnable {
        private String name;
        public Waiter(String name) { this.name = name; }

        @Override
        public void run() {
            // Twój kod tutaj...
        }
    }

    // TODO: Zaimplementuj klasę Chef
    static class Chef implements Runnable {
        private String name;
        public Chef(String name) { this.name = name; }

        @Override
        public void run() {
            // Twój kod tutaj...
        }
    }

    public static void main(String[] args) {
        System.out.println("Restauracja otwarta!");

        // TODO: Zainicjalizuj kolejkę (pojemność 5)

        // TODO: Stwórz pulę wątków dla 5 osób (2 kelnerów, 3 kucharzy) i ich uruchom

        // TODO: Zaplanuj zamknięcie restauracji
    }
}
