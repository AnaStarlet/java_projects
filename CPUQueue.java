package lab11;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class CPUQueue {
    private final BlockingQueue<Process> queue = new LinkedBlockingQueue<>(); // очередь задач
    private int maxLength = 0;

    public synchronized void addLast(Process process) {
        queue.add(process);
        maxLength = Math.max(maxLength, queue.size());
    }

    public Process getFirst() throws InterruptedException {
        return queue.take();
    }

    public int getMaxLength() {
        return maxLength;
    }

    public int getSize() {
        return queue.size();
    }
}
