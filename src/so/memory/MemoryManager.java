package so.memory;

import so.Process;
import so.strategy.AllocationStrategy;

public class MemoryManager {
    private Long[] physicMemory;
    private AllocationStrategy allocationStrategy;

    public MemoryManager(AllocationStrategy allocationStrategy) {
        this.allocationStrategy = allocationStrategy;
        this.physicMemory = new Long[128];
    }

    public void setAllocationStrategy(AllocationStrategy allocationStrategy) {
        this.allocationStrategy = allocationStrategy;
    }
    
    public void write(Process p) {
        allocationStrategy.findFitIndex(physicMemory, p).ifPresentOrElse(startIndex -> {
            for (int i = startIndex; i < startIndex + p.getSizeInMemory(); i++) {
                physicMemory[i] = p.getId();
            }
            printStatusMemory();
        }, () -> System.out.println("Espaço insuficiente na memória para o processo: " + p.getId()));
    }

    public void deleteProcess(Process p, int blocksToDelete) {
        var deletedBlocks = 0;

        for (var i = 0; i < physicMemory.length && deletedBlocks < blocksToDelete; i++) {
            if (physicMemory[i] != null && physicMemory[i] == p.getId()) {
                physicMemory[i] = null;
                deletedBlocks++;
            }
        }

        if (deletedBlocks < blocksToDelete) {
            System.out.println("Aviso: Tentativa de deletar mais blocos do que existentes para o processo: " + p.getId());
            System.out.println("Todos os blocos associados ao processo serão removidos.");
        }
        printStatusMemory();
    }

    public void printStatusMemory() {
        for (var s : physicMemory) {
            System.out.print((s != null ? s.toString() : "null") + " | ");
        }
        System.out.println();
    }
}
