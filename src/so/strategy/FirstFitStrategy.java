package so.strategy;

import so.Process;

import java.util.Optional;

public class FirstFitStrategy implements AllocationStrategy {

	@Override
	public Optional<Integer> findFitIndex(String[] memory, Process process) {
		int sizeNeeded = process.getSizeInMemory();
		int freeSpace = 0;

		for (int i = 0; i < memory.length; i++) {
			if (memory[i] == null) {
				freeSpace++;
				if (freeSpace == sizeNeeded) {
					return Optional.of(i - sizeNeeded + 1);
				}
			} else {
				freeSpace = 0;
			}
		}
		return Optional.empty();
	}
}
