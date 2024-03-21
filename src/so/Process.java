package so;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class Process {

    private static final AtomicLong ID_GENERATOR = new AtomicLong(0);

    private long id;
    private int sizeInMemory;
    private int timeToExecute;
    private Priority priority;

    public Process() {
        Random rand = new Random();
        this.id = ID_GENERATOR.incrementAndGet();
        List<Integer> givenList = Arrays.asList(1, 2, 4, 5, 8, 10, 20, 50, 100);
        this.sizeInMemory = givenList.get(rand.nextInt(givenList.size()));
        this.timeToExecute = 0;
        this.priority = Priority.MEDIA;
    }

    public Process(int sizeInMemory) {
        this();
        this.sizeInMemory = sizeInMemory;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getSizeInMemory() {
        return sizeInMemory;
    }

    public void setSizeInMemory(int sizeInMemory) {
        this.sizeInMemory = sizeInMemory;
    }

    public int getTimeToExecute() {
        return timeToExecute;
    }

    public void setTimeToExecute(int timeToExecute) {
        this.timeToExecute = timeToExecute;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

}
