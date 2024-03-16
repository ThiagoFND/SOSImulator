package so;

import so.cpu.CpuManager;
import so.memory.MemoryManager;
import so.schedule.Schedule;
import so.strategy.AllocationStrategy;
import so.strategy.FirstFitStrategy;

public class SystemOperation {

    private static MemoryManager mm;
    private static CpuManager cm;
    private static Schedule schedule;

    public static MemoryManager getMm() {
        return mm;
    }

    public static void setMm(MemoryManager mm) {
        SystemOperation.mm = mm;
    }

    public static CpuManager getCm() {
        return cm;
    }

    public static void setCm(CpuManager cm) {
        SystemOperation.cm = cm;
    }

    public static Schedule getSchedule() {
        return schedule;
    }

    public static void setSchedule(Schedule schedule) {
        SystemOperation.schedule = schedule;
    }

    public static Process SystemCall(SystemCallType type, Process p, int blocksToDelete) {
        if (type.equals(SystemCallType.WRITE_PROCESS)) {
            initializeMemoryManager();
            mm.write(p);
        } else if (type.equals(SystemCallType.DELETE_PROCESS)) {
            initializeMemoryManager();
            mm.deleteProcess(p, blocksToDelete);
        } else if (type.equals(SystemCallType.CREATE_PROCESS)) {
            if (cm == null) {
                cm = new CpuManager();
            }
            initializeMemoryManager();
            Process newProcess = new Process();
            mm.write(newProcess);
            return newProcess;
        }
        return null;
    }

    private static void initializeMemoryManager() {
        if (mm == null) {
            System.out.println("MemoryManager not initialized. Creating a new instance with a specific strategy.");
            AllocationStrategy strategy = new FirstFitStrategy();
            mm = new MemoryManager(strategy);
        }
    }

    public static void printMemoryStatus() {
        if (mm != null) {
            mm.printStatusMemory();
        } else {
            System.out.println("MemoryManager not initialized.");
        }
    }

    public static void createProcess() {
        if (mm == null) {
            initializeMemoryManager();
        }
        if (cm == null) {
            cm = new CpuManager();
        }
    }

    public static void deleteProcess(Process p, int blocksToDelete) {
        if (mm != null) {
            mm.deleteProcess(p, blocksToDelete);
        } else {
            System.out.println("MemoryManager not initialized.");
        }
    }
}
