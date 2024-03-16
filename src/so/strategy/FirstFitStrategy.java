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
					// Encontrou espaço suficiente, retorna o índice inicial do espaço
					return Optional.of(i - sizeNeeded + 1);
				}
			} else {
				// Se encontrar um bloco ocupado, reseta a contagem de espaço livre
				freeSpace = 0;
			}
		}

		// Se não encontrar espaço suficiente, retorna um Optional vazio
		return Optional.empty();
	}
}
