
package controller;

import controller.ContaCorrenteController;
import dao.ContaCorrenteDAO;
import dao.ContaPoupancaDAO;
import db.ConexaoBancoMySQL;
import db.IConnection;
import model.ContaCorrente;
import model.ContaPoupanca;

public class aplicacao {

    public static void main(String[] args) {
        // Criação da conexão com o banco de dados
        IConnection connection = new ConexaoBancoMySQL();

        // Criação dos objetos DAO
        ContaCorrenteDAO contaCorrenteDAO = new ContaCorrenteDAO(connection);
        ContaPoupancaDAO contaPoupancaDAO = new ContaPoupancaDAO(connection);

        // Criação de uma conta corrente para testar
        ContaCorrente contaCorrente = new ContaCorrente(1, "12345", 1000.0f, true);
        ContaPoupanca contaPoupanca = new ContaPoupanca("5050");

        // Criação do controller da conta corrente
        ContaCorrenteController contaCorrenteController = new ContaCorrenteController(contaCorrente);
        
        ContaPoupancaController contaPoupancaController = new ContaPoupancaController(contaPoupanca);
        
        //contaPoupancaDAO.save(contaPoupanca);
        //contaCorrenteDAO.save(contaCorrente);

        
        
        // Teste dos métodos
        //contaPoupancaController.depositar(500.0f);
        //contaCorrenteController.depositar(500.0f);
        //contaCorrenteController.sacar(200.0f);
        
        System.out.println("valor anterior: "+contaCorrente.getSaldo());
        contaPoupancaController.transferir(contaCorrente, 50);

        // Encerramento da conexão com o banco de dados
        //connection.closeConnection();
    }

}
