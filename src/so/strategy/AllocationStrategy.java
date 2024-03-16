package so.strategy;

import java.util.Optional;

public interface AllocationStrategy {
    Optional<Integer> findFitIndex(String[] memory, so.Process p);
}