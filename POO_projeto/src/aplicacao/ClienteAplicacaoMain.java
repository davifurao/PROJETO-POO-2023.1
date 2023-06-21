package aplicacao;

import java.util.Scanner;

//import model.ClienteAplicacao;
import model.IConta;
import model.ContaCorrente;
import model.ContaPoupanca;

public class ClienteAplicacaoMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ClienteAplicacao clienteAplicacao = new ClienteAplicacao();

        int opcao = 0;

        while (opcao != 4) {
            System.out.println("Selecione uma opção:");
            System.out.println("1. Cadastrar conta");
            System.out.println("2. Deletar conta");
            System.out.println("3. Realizar operação");
            System.out.println("4. Sair");

            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    cadastrarConta(scanner, clienteAplicacao);
                    break;
                case 2:
                    deletarConta(scanner, clienteAplicacao);
                    break;
                case 3:
                    realizarOperacao(scanner, clienteAplicacao);
                    break;
                case 4:
                    System.out.println("Encerrando o programa.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }

        scanner.close();
    }

    private static void cadastrarConta(Scanner scanner, ClienteAplicacao clienteAplicacao) {
        System.out.println("Digite o CPF do cliente:");
        String cpf = scanner.next();

        System.out.println("Digite o número da conta:");
        String numeroConta = scanner.next();

        System.out.println("Selecione o tipo de conta:");
        System.out.println("1. Conta Corrente");
        System.out.println("2. Conta Poupança");

        int tipoConta = scanner.nextInt();
        IConta conta;

        if (tipoConta == 1) {
            conta = new ContaCorrente(numeroConta);
        } else if (tipoConta == 2) {
            conta = new ContaPoupanca(numeroConta);
        } else {
            System.out.println("Opção inválida. Nenhuma conta foi cadastrada.");
            return;
        }

        clienteAplicacao.cadastrarConta(conta, cpf);

        System.out.println("Conta cadastrada com sucesso.");
    }

    private static void deletarConta(Scanner scanner, ClienteAplicacao clienteAplicacao) {
        System.out.println("Digite o CPF do cliente para deletar a conta:");
        String cpf = scanner.next();
        
        System.out.println("Selecione o tipo de conta:");
        System.out.println("1. Conta Corrente");
        System.out.println("2. Conta Poupança");
        int tipoConta = scanner.nextInt();
        IConta conta;
        
        System.out.println("Digite o número da conta a ser deletada:");
        String numeroConta = scanner.next();

        if (tipoConta == 1) {
            conta = new ContaCorrente(numeroConta);
        } else if (tipoConta == 2) {
            conta = new ContaPoupanca(numeroConta);
        }else {
            System.out.println("Opção inválida. Nenhuma conta foi cadastrada.");
            return;
        }

        clienteAplicacao.deletarConta(conta, cpf);

        System.out.println("Conta deletada com sucesso.");
    }

    private static void realizarOperacao(Scanner scanner, ClienteAplicacao clienteAplicacao) {
        System.out.println("Digite o CPF do cliente:");
        String cpf = scanner.next();

        System.out.println("Digite o número da conta:");
        String numeroConta = scanner.next();

        IConta conta = new ContaCorrente(numeroConta);

        int opcao = 0;

        while (opcao != 6) {
            System.out.println("Selecione uma operação:");
            System.out.println("1. Saque");
            System.out.println("2. Depósito");
            System.out.println("3. Transferência");
            System.out.println("4. Extrato");
            System.out.println("5. Voltar");

            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("Digite o valor do saque:");
                    float valorSaque = scanner.nextFloat();
                    clienteAplicacao.saque(conta, valorSaque);
                    break;
                case 2:
                    System.out.println("Digite o valor do depósito:");
                    float valorDeposito = scanner.nextFloat();
                    clienteAplicacao.deposito(conta, valorDeposito);
                    break;
                case 3:
                    System.out.println("Digite o número da conta de destino:");
                    String numeroContaDestino = scanner.next();
                    IConta contaDestino = new ContaCorrente(numeroContaDestino);
                    System.out.println("Digite o valor da transferência:");
                    float valorTransferencia = scanner.nextFloat();
                    clienteAplicacao.transferir(conta, contaDestino, valorTransferencia);
                    break;
                case 4:
                    System.out.println("Digite o mês do extrato:");
                    int mes = scanner.nextInt();
                    System.out.println("Digite o ano do extrato:");
                    int ano = scanner.nextInt();
                    clienteAplicacao.extrato(conta, mes, ano);
                    break;
                case 5:
                    System.out.println("Voltando para o menu principal.");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }
}
