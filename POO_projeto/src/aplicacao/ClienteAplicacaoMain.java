package aplicacao;

import controller.ClienteController;
import controller.ICliente;
import controller.RegistroContaController;
import dao.ClienteDAO;
import dao.RegistroContaDAO;
import db.ConexaoBancoMySQL;
import db.IConnection;
import model.ContaCorrente;
import model.ContaPoupanca;
import model.IConta;
import dao.ClienteDAO;
import client_model.*;

import java.util.Scanner;

public class ClienteAplicacaoMain {

    public static void main(String[] args) {
        IConnection connection = new ConexaoBancoMySQL();
        ClienteDAO clienteDAO = new ClienteDAO(connection);
        RegistroContaDAO registroContaDAO = new RegistroContaDAO();
        RegistroContaController registroContaController = new RegistroContaController();
        ClienteController clienteController = new ClienteController();

        ClienteAplicacao clienteAplicacao = new ClienteAplicacao(clienteDAO, registroContaDAO, registroContaController, clienteController);

        Scanner scanner = new Scanner(System.in);

        int opcao = 0;

        while (opcao != 6) {
            System.out.println("Selecione uma opção:");
            System.out.println("1. Cadastrar cliente");
            System.out.println("2. Deletar cliente");
            System.out.println("3. Atualizar cliente");
            System.out.println("4. Cadastrar conta");
            System.out.println("5. Deletar conta");
            System.out.println("6. Sair");

            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    cadastrarCliente(scanner, clienteAplicacao);
                    break;
                case 2:
                    deletarCliente(scanner, clienteAplicacao);
                    break;
                case 3:
                    atualizarCliente(scanner, clienteAplicacao);
                    break;
                case 4:
                    cadastrarConta(scanner, clienteAplicacao);
                    break;
                case 5:
                    deletarConta(scanner, clienteAplicacao);
                    break;
                case 6:
                    System.out.println("Encerrando o programa.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }

        scanner.close();
    }

    private static void cadastrarCliente(Scanner scanner, ClienteAplicacao clienteAplicacao) {
        System.out.println("Digite o CPF do cliente:");
        String cpf = scanner.next();

        Cliente cliente = new Cliente(cpf);
        clienteAplicacao.cadastrarCliente(cliente);

        System.out.println("Cliente cadastrado com sucesso.");
    }

    private static void deletarCliente(Scanner scanner, ClienteAplicacao clienteAplicacao) {
        System.out.println("Digite o CPF do cliente a ser deletado:");
        String cpf = scanner.next();

        Cliente cliente = new Cliente(cpf);
        clienteAplicacao.deletarCliente(cliente);

        System.out.println("Cliente deletado com sucesso.");
    }

    private static void atualizarCliente(Scanner scanner, ClienteAplicacao clienteAplicacao) {
        System.out.println("Digite o CPF do cliente a ser atualizado:");
        String cpf = scanner.next();

        Cliente cliente = new Cliente(cpf);
        clienteAplicacao.atualizarCliente(cliente);

        System.out.println("Cliente atualizado com sucesso.");
    }

    private static void cadastrarConta(Scanner scanner, ClienteAplicacao clienteAplicacao) {
        System.out.println("Digite o CPF do cliente para cadastrar a conta:");
        String cpf = scanner.next();

        System.out.println("Selecione o tipo de conta:");
        System.out.println("1. Conta Corrente");
        System.out.println("2. Conta Poupança");

        int tipoConta = scanner.nextInt();
        IConta conta;

        if (tipoConta == 1) {
            System.out.println("Digite o número da conta corrente:");
            String numeroConta = scanner.next();
            conta = new ContaCorrente(numeroConta);
        } else if (tipoConta == 2) {
            System.out.println("Digite o número da conta poupança:");
            String numeroConta = scanner.next();
            conta = new ContaPoupanca(numeroConta);
        } else {
            System.out.println("Opção inválida. Nenhuma conta foi cadastrada.");
            return;
        }

        clienteAplicacao.cadastrarConta(conta,cpf);

        System.out.println("Conta cadastrada com sucesso.");
    }

    private static void deletarConta(Scanner scanner, ClienteAplicacao clienteAplicacao) {
        System.out.println("Digite o CPF do cliente para deletar a conta:");
        String cpf = scanner.next();

        System.out.println("Digite o número da conta a ser deletada:");
        String numeroConta = scanner.next();

        IConta conta;

        if (numeroConta.startsWith("CC"))
            conta = new ContaCorrente(numeroConta);
        else if (numeroConta.startsWith("CP"))
            conta = new ContaPoupanca(numeroConta);
        else {
            System.out.println("Número de conta inválido. Nenhuma conta foi deletada.");
            return;
        }

        clienteAplicacao.deletarConta(conta,cpf);

        System.out.println("Conta deletada com sucesso.");
    }
}
