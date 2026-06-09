package lab11;

public class Manager {
    private final CPUQueue queue1 = new CPUQueue();
    private final CPUQueue queue2 = new CPUQueue();
    private final CPUQueue queue3 = new CPUQueue();
    private final CPU processor;
    private final Thread processorThread;
    private int interruptedProcesses = 0;

    public Manager() {
        this.processor = new CPU(queue1, queue2, queue3);
        this.processorThread = new Thread(processor);
    }

    public void startProcessor() {
        processorThread.start();
    }

    public void stopProcessor() {
        processor.stopProcessing();
    }

    public boolean hasPendingTasks() {
        return queue1.getSize() > 0 || queue2.getSize() > 0 || queue3.getSize() > 0;
    }

    public void distributeTask(Process task, int streamId) throws InterruptedException {
        if (!processor.isProcessing()) {
            processor.setCurrentTask(task);
        } else {
            if (streamId == 1) {
                if (processor.getCurrentTask() != null && processor.getCurrentTask().getStreamId() == 2) {
                    Process interruptedTask = processor.getCurrentTask();
                    queue3.addLast(new Process(interruptedTask.getTime() - 1, interruptedTask.getTime(), 3, interruptedTask.getName()));
                    interruptedProcesses++;
                    System.out.println("[Менеджер] Процесс второго потока " + interruptedTask.getName() + " приостановлен.");
                    processor.setCurrentTask(task);
                } else {
                    queue1.addLast(task);
                }
            } else {
                if (processor.getCurrentTask() != null && processor.getCurrentTask().getStreamId() == 1) {
                    Process interruptedTask = processor.getCurrentTask();
                    queue3.addLast(new Process(interruptedTask.getTime() - 1, interruptedTask.getTime(), 3, interruptedTask.getName()));
                    interruptedProcesses++;
                    System.out.println("[Менеджер] Процесс первого потока " + interruptedTask.getName() + " приостановлен.");
                    queue2.addLast(task);
                } else {
                    queue2.addLast(task);
                }
            }
        }
    }

    public void printStatistics() {
        System.out.println("\n=== Итоги ===");
        System.out.println("Максимальный размер первой очереди: " + queue1.getMaxLength());
        System.out.println("Максимальный размер второй очереди: " + queue2.getMaxLength());
        System.out.println("Максимальный размер третьей очереди: " + queue3.getMaxLength());
        System.out.println("Количество прерванных процессов: " + interruptedProcesses);
    }
}
