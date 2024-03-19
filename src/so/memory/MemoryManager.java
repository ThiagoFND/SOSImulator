package so.memory;

import so.Process;
import so.strategy.AllocationStrategy;

public class MemoryManager {
    private String[] physicMemory;
    private AllocationStrategy allocationStrategy;

    public MemoryManager(AllocationStrategy allocationStrategy) {
        this.allocationStrategy = allocationStrategy;
        this.physicMemory = new String[128];
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
            if (p.getId().equals(physicMemory[i])) {
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
            System.out.print((s != null ? s : "null") + " | ");
        }
        System.out.println();
    }
}
