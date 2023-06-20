package dao;

import db.ConexaoBancoMySQL;
import dao.ContaPoupancaDAO;
import dao.IEntityDAO;
import dao.RegistroTransacaoDAO;
import model.ContaPoupanca;
import model.RegistroTransacao;
import model.TipoConta;
import model.TipoTransacao;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        // Criação da conexão com o banco de dados (ajuste os parâmetros de acordo com a sua implementação)
        ConexaoBancoMySQL connection = new ConexaoBancoMySQL();

        // Teste do ContaPoupancaDAO
        IEntityDAO<ContaPoupanca> contaPoupancaDAO = new ContaPoupancaDAO(connection);

        // Criação de uma nova conta poupança
        ContaPoupanca contaPoupanca = new ContaPoupanca(1, "12345678", 1000.0f, true);

        // Salvar a conta poupança no banco de dados
        contaPoupancaDAO.save(contaPoupanca);

        // Buscar todas as contas poupança do banco de dados
        System.out.println("Contas Poupança:");
        for (ContaPoupanca conta : contaPoupancaDAO.findAll()) {
            System.out.println(conta);
        }

        // Atualizar o saldo da conta poupança
        contaPoupanca.setSaldo(2000.0f);
        contaPoupancaDAO.update(contaPoupanca);

        // Deletar a conta poupança do banco de dados
        contaPoupancaDAO.delete(contaPoupanca);

        // Teste do RegistroTransacaoDAO
        IEntityDAO<RegistroTransacao> registroTransacaoDAO = new RegistroTransacaoDAO(connection);

        // Criação de um novo registro de transação
        RegistroTransacao registroTransacao = new RegistroTransacao(1, 500.0f, TipoConta.CONTA_CORRENTE, TipoTransacao.SAQUE, LocalDateTime.now());

        // Salvar o registro de transação no banco de dados
        //registroTransacaoDAO.save(registroTransacao);

        // Buscar todos os registros de transação do banco de dados
        System.out.println("Registros de Transação:");
        for (RegistroTransacao registro : registroTransacaoDAO.findAll()) {
            System.out.println(registro);
        }

        // Atualizar o valor do registro de transação
        registroTransacao.setValor(1000.0f);
        registroTransacaoDAO.update(registroTransacao);

        // Deletar o registro de transação do banco de dados
        registroTransacaoDAO.delete(registroTransacao);

        // Fechar a conexão com o banco de dados
        connection.closeConnection();
    }
}
