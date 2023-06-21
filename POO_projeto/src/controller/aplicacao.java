package controller;

import java.time.LocalDateTime;
import java.util.List;
import client_model.RegistroConta;
import controller.RegistroContaController;
import model.ContaCorrente;
import model.ContaPoupanca;
import model.IConta;
import model.TipoConta;

public class aplicacao {
    public static void main(String[] args) {
        // Criação do controller
        RegistroContaController controller = new RegistroContaController();

        // Criação de registros de conta
        ContaCorrente conta1 = new ContaCorrente("123", 1000.0f,true);
        IConta conta2 = new ContaPoupanca("456", 2000.0f,true);
        RegistroConta registro1 = new RegistroConta("12345678901", TipoConta.CONTA_CORRENTE, conta1);
        RegistroConta registro2 = new RegistroConta("98765432109", TipoConta.CONTA_POUPANCA, conta2);

        // Adicionar registros de conta
        controller.adicionarRegistro(registro1);
        controller.adicionarRegistro(registro2);

        // Listar registros de conta
        List<RegistroConta> registros = controller.listarRegistros();
        System.out.println("Registros de Conta:");
        for (RegistroConta registro : registros) {
            System.out.println(registro);
        }

        // Atualizar um registro de conta
        conta1.setSaldo(150f);
        RegistroConta registroAtualizado = new RegistroConta("12345678901", TipoConta.CONTA_CORRENTE, conta1);
        controller.atualizarRegistro(registroAtualizado);

        // Excluir um registro de conta
        controller.excluirRegistro(registro2);

        // Buscar registro de conta por número da conta
        String numeroConta = "123";
        RegistroConta registroEncontrado = controller.buscarRegistroPorNumeroConta(numeroConta);
        if (registroEncontrado != null) {
            System.out.println("Registro de Conta encontrado (número da conta: " + numeroConta + "):");
            System.out.println(registroEncontrado);
        } else {
            System.out.println("Nenhum registro de conta encontrado para o número da conta: " + numeroConta);
        }

        // Buscar registros de conta por CPF
        String cpf = "12345678901";
        List<RegistroConta> registrosCPF = controller.buscarRegistrosPorCPF(cpf);
        System.out.println("Registros de Conta associados ao CPF " + cpf + ":");
        for (RegistroConta registro : registrosCPF) {
            System.out.println(registro);
        }
    }
}
