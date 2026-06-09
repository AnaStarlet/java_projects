package lab11;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int totalTasks1 = 20;
        int totalTasks2 = 30;
        int minTaskTime1 = 1;
        int maxTaskTime1 = 3;
        int minTaskTime2 = 2;
        int maxTaskTime2 = 5;

        Manager manager = new Manager();
        manager.startProcessor();

        Thread thread1 = new Thread(new FillThread(maxTaskTime1, minTaskTime1, manager, totalTasks1, 1));
        Thread thread2 = new Thread(new FillThread(maxTaskTime2, minTaskTime2, manager, totalTasks2, 2));

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        while (manager.hasPendingTasks()) {
            Thread.sleep(500);
        }

        manager.stopProcessor();
        manager.printStatistics();
    }
}
