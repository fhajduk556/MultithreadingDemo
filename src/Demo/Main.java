package Demo;

class MyTask implements Runnable {
    // Overriding the run method
    @Override
    public void run() {
        System.out.println("This code is running in a thread");
    }
}

public class Main {
    public static void main(String[] args) {
        MyTask task1 = new MyTask();
        Thread thread1 = new Thread(task1);
        thread1.start();
        System.out.println("This code is outside of the thread");
    }
}



