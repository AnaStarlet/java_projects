package lab11;

public class Process {
    private final int time;         // время выполнения задачи
    private final String name;      // имя задачи
    private final int streamId;     // идентификатор потока
    private static int counter = 1; // счётчик задач

    public Process(int time, int maxTime, int streamId, String name) {
        this.time = time;
        this.name = name != null ? name : "Задача " + counter++;
        this.streamId = streamId;
    }

    public String getName() {
        return name;
    }

    public int getTime() {
        return time;
    }

    public int getStreamId() {
        return streamId;
    }
}
