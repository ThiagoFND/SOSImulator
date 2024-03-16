package so.strategy;

import java.util.Optional;

import so.Process;

class WorstFitStrategy implements AllocationStrategy {
    @Override
    public Optional<Integer> findFitIndex(String[] memory, Process p) {
        var maxSize = 0;
        var startIndex = -1;
        var currentSize = 0;

        for (var i = 0; i < memory.length; i++) {
            if (memory[i] == null) {
                currentSize = 1;
                while (i + currentSize < memory.length && memory[i + currentSize] == null) {
                    currentSize++;
                }
                if (currentSize > maxSize) {
                    maxSize = currentSize;
                    startIndex = i;
                    i += currentSize - 1; // Skip to the end of the free block
                }
            }
        }

        return startIndex == -1 ? Optional.empty() : Optional.of(startIndex);
    }
}