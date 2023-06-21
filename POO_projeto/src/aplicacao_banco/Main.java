package aplicacao_banco;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import model.ContaCorrente;
import model.ContaPoupanca;
import model.IConta;

public class Main {

    private static AplicacaoBanco aplicacaoBanco;
    private static Map<String, IConta> contas;

    public static void main(String[] args) {
        aplicacaoBanco = new AplicacaoBanco();
        contas = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            exibirMenu();
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    // Cadastrar Conta
                    System.out.println("Digite o tipo de conta (CC para Conta Corrente, CP para Conta Poupança):");
                    String tipoConta = scanner.next().toUpperCase();
                    System.out.println("Digite o número da conta:");
                    String numeroConta = scanner.next();
                    IConta conta = criarConta(tipoConta, numeroConta);
                    if (conta != null) {
                        contas.put(numeroConta, conta);
                        System.out.println("Conta cadastrada com sucesso!");
                    } else {
                        System.out.println("Tipo de conta inválido.");
                    }
                    break;

                case 2:
                    // Deletar Conta
                    System.out.println("Digite o número da conta:");
                    String numeroContaDeletar = scanner.next();
                    IConta contaDeletar = contas.get(numeroContaDeletar);
                    if (contaDeletar != null) {
                        contas.remove(numeroContaDeletar);
                        System.out.println("Conta deletada com sucesso!");
                    } else {
                        System.out.println("Conta não encontrada.");
                    }
                    break;

                case 3:
                    // Encontrar Conta por Número de Conta
                    System.out.println("Digite o número da conta:");
                    String numeroContaBuscar = scanner.next();
                    IConta contaBuscar = contas.get(numeroContaBuscar);
                    if (contaBuscar != null) {
                        aplicacaoBanco.acharContaPorNumeroConta(contaBuscar);
                    } else {
                        System.out.println("Conta não encontrada.");
                    }
                    break;

                case 4:
                    // Saque
                    System.out.println("Digite o número da conta:");
                    String numeroContaSaque = scanner.next();
                    IConta contaSaque = contas.get(numeroContaSaque);
                    if (contaSaque != null) {
                        System.out.println("Digite o valor do saque:");
                        float valorSaque = scanner.nextFloat();
                        aplicacaoBanco.saque(contaSaque, valorSaque);
                    } else {
                        System.out.println("Conta não encontrada.");
                    }
                    break;

                case 5:
                    // Depósito
                    System.out.println("Digite o número da conta:");
                    String numeroContaDeposito = scanner.next();
                    IConta contaDeposito = contas.get(numeroContaDeposito);
                    if (contaDeposito != null) {
                        System.out.println("Digite o valor do depósito:");
                        float valorDeposito = scanner.nextFloat();
                        aplicacaoBanco.deposito(contaDeposito, valorDeposito);
                    } else {
                        System.out.println("Conta não encontrada.");
                    }
                    break;


                case 6:
                    // Transferência
                    System.out.println("Digite o número da conta de origem:");
                    String numeroContaOrigem = scanner.next();
                    IConta contaOrigem = contas.get(numeroContaOrigem);
                    if (contaOrigem != null) {
                        System.out.println("Digite o número da conta de destino:");
                        String numeroContaDestino = scanner.next();
                        IConta contaDestino = contas.get(numeroContaDestino);
                        if (contaDestino != null) {
                            System.out.println("Digite o valor da transferência:");
                            float valorTransferencia = scanner.nextFloat();
                            aplicacaoBanco.transferir(contaOrigem, contaDestino, valorTransferencia);
                        } else {
                            System.out.println("Conta de destino não encontrada.");
                        }
                    } else {
                        System.out.println("Conta de origem não encontrada.");
                    }
                    break;

                case 7:
                    // Extrato
                    System.out.println("Digite o número da conta:");
                    String numeroContaExtrato = scanner.next();
                    IConta contaExtrato = contas.get(numeroContaExtrato);
                    if (contaExtrato != null) {
                        System.out.println("Digite o mês:");
                        int mes = scanner.nextInt();
                        System.out.println("Digite o ano:");
                        int ano = scanner.nextInt();
                        aplicacaoBanco.extrato(contaExtrato, mes, ano);
                    } else {
                        System.out.println("Conta não encontrada.");
                    }
                    break;

                case 0:
                    // Sair
                    System.out.println("Obrigado por utilizar o aplicativo de banco. Até mais!");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opção inválida. Digite novamente.");
                    break;
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("----- Menu -----");
        System.out.println("1. Cadastrar Conta");
        System.out.println("2. Deletar Conta");
        System.out.println("3. Encontrar Conta por Número de Conta");
        System.out.println("4. Saque");
        System.out.println("5. Depósito");
        System.out.println("6. Transferência");
        System.out.println("7. Extrato");
        System.out.println("0. Sair");
        System.out.println("Digite a opção desejada:");
    }

    private static IConta criarConta(String tipoConta, String numeroConta) {
        if (tipoConta.equals("CC")) {
            return new ContaCorrente(numeroConta);
        } else if (tipoConta.equals("CP")) {
            return new ContaPoupanca(numeroConta);
        } else {
            return null;
        }
    }
}
