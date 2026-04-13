package Zadania;

public class Zad1 {
    /*
     * ZADANIE 1:
     * Poniższy program powinien wypisać "Początek", następnie w dwóch OSOBNYCH
     * wątkach wykonać ciężkie obliczenia (symulowane przez sleep), a na samym końcu
     * wypisać "Koniec".
     * * PROBLEM: Obecnie kod wykonuje się sekwencyjnie lub nie czeka na zakończenie.
     * NAPRAW KOD:
     * 1. Stwórz dwa wątki używając przygotowanych obiektów Runnable.
     * 2. Uruchom je poprawnie (pamiętaj, nie wywołuj bezpośrednio metody run()).
     * 3. Spraw, aby główny wątek poczekał na zakończenie obu zadań,
     * zanim wypisze "Koniec".
     */

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Początek");

        Runnable heavyTask1 = () -> {
            try { Thread.sleep(2000); } catch (InterruptedException e) {}
            System.out.println("Zadanie 1 zakończone.");
        };

        Runnable heavyTask2 = () -> {
            try { Thread.sleep(2000); } catch (InterruptedException e) {}
            System.out.println("Zadanie 2 zakończone.");
        };

        // TODO: Tutaj wpisz swój kod (uruchomienie i czekanie na wątki)


        System.out.println("Koniec - wszystkie obliczenia gotowe.");
    }
}
