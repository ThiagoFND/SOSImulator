package so;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import so.Process;

public class Process {

	private String id;
	private int sizeInMemory;
	private int timeToExecute;
	private Priority priority;

    public Process() {
        Random rand = new Random();
        this.id = UUID.randomUUID().toString();
        List<Integer> givenList = Arrays.asList(1, 2, 4, 5, 8, 10, 20, 50, 100);
        this.sizeInMemory = givenList.get(rand.nextInt(givenList.size()));
        this.timeToExecute = 0;  // Initialize timeToExecute to a default value (you can change this as needed)
        this.priority = Priority.MEDIA;  // Set a default priority
    }

    // Parameterized constructor
    public Process(int sizeInMemory) {
        this();  // Call the default constructor to initialize other fields
        this.sizeInMemory = sizeInMemory;
        this.priority = priority;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
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
