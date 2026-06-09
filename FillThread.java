package lab11;

public class FillThread implements Runnable {
    private final int max;
    private final int min;
    private final Manager manager;
    private final int totalTasks;
    private final int streamId;

    public FillThread(int max, int min, Manager manager, int totalTasks, int streamId) {
        this.max = max;
        this.min = min;
        this.manager = manager;
        this.totalTasks = totalTasks;
        this.streamId = streamId;
    }

    @Override
    public void run() {
        for (int i = 0; i < totalTasks; i++) {
            int taskTime = (int) (Math.random() * (max - min + 1)) + min;
            String taskName = "Задача " + (i + 1);
            System.out.println("Поток " + streamId + ": создана " + taskName);

            Process task = new Process(taskTime, taskTime, streamId, taskName);

            try {
                manager.distributeTask(task, streamId);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Поток " + streamId + " завершил создание задач.");
    }
}
