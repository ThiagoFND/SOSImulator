package so.strategy;

import so.Process;
import java.util.Optional;

public interface AllocationStrategy {
    Optional<Integer> findFitIndex(Long[] memory, Process process);
}
