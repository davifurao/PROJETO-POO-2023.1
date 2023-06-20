package dao;

import db.ConexaoBancoMySQL;
import dao.ContaCorrenteDAO;
import model.ContaCorrente;

public class Main {
    public static void main(String[] args) {
        // Criar conexão com o banco de dados
        ConexaoBancoMySQL connection = new ConexaoBancoMySQL();
        // Criar instância do DAO
        ContaCorrenteDAO contaCorrenteDAO = new ContaCorrenteDAO(connection);

        // Criar objetos de ContaCorrente para testar
        ContaCorrente conta1 = new ContaCorrente(1, "123456", 1000.0f, true);
        ContaCorrente conta2 = new ContaCorrente(2, "789012", 2000.0f, true);

//        // Testar método save
//        contaCorrenteDAO.save(conta1);
//        contaCorrenteDAO.save(conta2);
//
//        // Testar método findAll
//        System.out.println("Contas correntes:");
//        for (ContaCorrente conta : contaCorrenteDAO.findAll()) {
//            System.out.println(conta);
//        }
//
//        // Atualizar uma conta corrente
//        conta1.setSaldo(1500.0f);
//        contaCorrenteDAO.update(conta1);
//        
//        
//
//        // Testar método findAll novamente após a atualização
//        System.out.println("\nContas correntes após a atualização:");
//        for (ContaCorrente conta : contaCorrenteDAO.findAll()) {
//            System.out.println(conta);
//        }
        
        
        contaCorrenteDAO.delete(conta1);

        // Fechar a conexão com o banco de dados
        connection.closeConnection();;
    }
}
