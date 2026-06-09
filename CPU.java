package lab11;

import java.util.concurrent.atomic.AtomicBoolean;

public class CPU implements Runnable {
    private final CPUQueue queue1;
    private final CPUQueue queue2;
    private final CPUQueue queue3;
    private final AtomicBoolean isRunning = new AtomicBoolean(true);
    private final AtomicBoolean isProcessing = new AtomicBoolean(false);
    private volatile Process currentTask = null;

    public CPU(CPUQueue queue1, CPUQueue queue2, CPUQueue queue3) {
        this.queue1 = queue1;
        this.queue2 = queue2;
        this.queue3 = queue3;
    }

    public boolean isProcessing() {
        return isProcessing.get();
    }

    public synchronized void setCurrentTask(Process task) {
        if (!isProcessing.get()) {
            currentTask = task;
        }
    }

    public synchronized Process getCurrentTask() {
        return currentTask;
    }

    public void stopProcessing() {
        isRunning.set(false);
    }

    @Override
    public void run() {
        try {
            while (isRunning.get()) {
                if (currentTask != null) {
                    isProcessing.set(true);
                    System.out.println("[Процессор] Обработка " + currentTask.getName());
                    Thread.sleep(currentTask.getTime() * 200L);
                    System.out.println("[Процессор] " + currentTask.getName() + " завершена.");
                    currentTask = null;
                    isProcessing.set(false);
                } else {
                    processFromQueue(queue1, "Очередь 1");
                    processFromQueue(queue3, "Очередь 3");
                    processFromQueue(queue2, "Очередь 2");
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("[Процессор] Завершение работы.");
    }

    private void processFromQueue(CPUQueue queue, String queueName) throws InterruptedException {
        if (queue.getSize() > 0) {
            Process task = queue.getFirst();
            isProcessing.set(true);
            System.out.println("[" + queueName + "] Обработка " + task.getName());
            Thread.sleep(task.getTime() * 200L);
            System.out.println("[Процессор] " + task.getName() + " завершена.");
            isProcessing.set(false);
        }
    }
}
