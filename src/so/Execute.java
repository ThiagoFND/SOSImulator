package so;

import java.util.InputMismatchException;
import java.util.Scanner;

import so.exception.InvalidInputException;
import so.memory.MemoryManager;
import so.strategy.AllocationStrategy;
import so.strategy.BestFitStrategy;
import so.strategy.FirstFitStrategy;
import so.strategy.WorstFitStrategy;

public class Execute {
    public static void main(String[] args) throws InvalidInputException {
        Scanner scanner = new Scanner(System.in);
        // Inicializa o MemoryManager com uma estratégia padrão
        AllocationStrategy defaultStrategy = new FirstFitStrategy();
        SystemOperation.setMm(new MemoryManager(defaultStrategy)); // Isso é feito apenas uma vez
        SystemOperation systemOperation = new SystemOperation();
        Process currentProcess = null;

        while (true) {
            System.out.println("Escolha uma opção: \n1. Criar processo \n2. Escrever na memória \n3. Imprimir estado da memória \n4. Deletar \n0. Sair \n");

            int escolha;
            try {
                escolha = readInt(scanner, "Por favor, digite um número: ");
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
                continue;
            }

            switch (escolha) {
            case 1:
                System.out.println("Deseja alterar a estratégia de alocação? (Sim/Não)");
                scanner.nextLine(); // Consume the newline left by nextInt
                String resposta = scanner.nextLine();
                if ("Sim".equalsIgnoreCase(resposta)) {
                    AllocationStrategy strategy = chooseAllocationStrategy(scanner);
                    // Atualiza a estratégia no MemoryManager existente
                    SystemOperation.getMm().setAllocationStrategy(strategy);
                }

                try {
                    System.out.println("Digite o tamanho na memória para o novo processo:");
                    int sizeInMemory = readInt(scanner, "");
                    currentProcess = new Process(sizeInMemory);
                    System.out.println("Processo criado: " + currentProcess.getId());
                } catch (InvalidInputException e) {
                    System.out.println(e.getMessage());
                    // Aqui, dependendo do fluxo desejado, você pode permitir outra tentativa ou continuar o loop.
                }
                break;



                case 2:
                    if (currentProcess != null) {
                        System.out.println("Escrevendo na memória para o processo: " + currentProcess.getId());
                        systemOperation.SystemCall(SystemCallType.WRITE_PROCESS, currentProcess, escolha);
                    } else {
                        System.out.println("Nenhum processo criado. Crie um processo primeiro.");
                    }
                    break;

                case 3:
                    systemOperation.printMemoryStatus();
                    break;

                case 4:
                    System.out.println("Digite o ID do processo para deletar na memória:");
                    scanner.nextLine();
                    String idToDelete = scanner.nextLine();
                    Process processToDelete = new Process();
                    processToDelete.setId(idToDelete);

                    try {
                        System.out.println("Digite o número de blocos a serem deletados:");
                        int blocksToDelete = readInt(scanner, "");
                        systemOperation.deleteProcess(processToDelete, blocksToDelete);
                    } catch (InvalidInputException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 0:
                    System.out.println("Saindo do programa.");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static AllocationStrategy chooseAllocationStrategy(Scanner scanner) {
        System.out.println("Escolha a estratégia de alocação de memória: \n1. First Fit \n2. Best Fit \n3. Worst Fit");
        while (true) {
            try {
                int strategyChoice = readInt(scanner, "Digite o número da estratégia: ");
                switch (strategyChoice) {
                    case 1:
                        return new FirstFitStrategy();
                    case 2:
                        return new BestFitStrategy();
                    case 3:
                        return new WorstFitStrategy();
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static int readInt(Scanner scanner, String message) throws InvalidInputException {
        try {
            if (!message.isEmpty()) System.out.println(message);
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.next(); // Consume the invalid token
            throw new InvalidInputException("Entrada inválida. Por favor, digite um número inteiro.");
        }
    }
}
