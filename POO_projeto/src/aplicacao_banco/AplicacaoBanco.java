package aplicacao_banco;

import controller.ContaCorrenteController;
import controller.ContaPoupancaController;
import dao.ContaCorrenteDAO;
import dao.ContaPoupancaDAO;
import db.ConexaoBancoMySQL;
import db.IConnection;
import model.ContaCorrente;
import model.ContaPoupanca;
import model.IConta;

public class AplicacaoBanco {

	IConnection connection = new ConexaoBancoMySQL();
	ContaPoupancaDAO contaPoupancaDAO = new ContaPoupancaDAO(connection);
	ContaCorrenteDAO contaCorrenteDAO = new ContaCorrenteDAO(connection);
	
	
	
	public void cadastrarContaCorrente(ContaCorrente conta) {
		contaCorrenteDAO.save(conta);
	}
	
	public void cadastrarContaPoupanca(ContaPoupanca conta) {
		contaPoupancaDAO.save(conta);
	}
	
	public void deletarContaPoupanca(ContaPoupanca conta) {
		contaPoupancaDAO.delete(conta);
	}
	
	public void deletarContaCorrente(ContaCorrente conta) {
		contaCorrenteDAO.delete(conta);
	}
	
	public void acharContaPorNumeroConta(IConta conta) {
		if(conta instanceof ContaCorrente) {
			ContaCorrente conta2 = (ContaCorrente) conta;
			contaCorrenteDAO.findByNumeroConta(conta2.getNumeroConta());
		}else if(conta instanceof ContaPoupanca) {
			ContaPoupanca conta2 = (ContaPoupanca) conta;
			contaPoupancaDAO.findByNumeroConta(conta2.getNumeroConta());
		}else {
			System.err.println("O tipo da classe é bizarro meu nobre");
		}
	}
	
	//A conta já deve ter sido adicionada anteriormente
	public void saque(IConta conta,float valor) {
	
		if(conta instanceof ContaCorrente) {
			ContaCorrente conta2 = (ContaCorrente)conta; 
			ContaCorrenteController contaCorrenteController= new ContaCorrenteController(conta2);
			contaCorrenteController.sacar(valor);
		}else if(conta instanceof ContaPoupanca) {
			ContaPoupanca conta2 = (ContaPoupanca) conta;
			ContaPoupancaController contaPoupancaController=new ContaPoupancaController(conta2);
			contaPoupancaController.sacar(valor);
		}
	}
	
	public void deposito(IConta conta,float valor) {
		
		if(conta instanceof ContaCorrente) {
			ContaCorrente conta2 = (ContaCorrente)conta; 
			ContaCorrenteController contaCorrenteController= new ContaCorrenteController(conta2);
			contaCorrenteController.depositar(valor);
		}else if(conta instanceof ContaPoupanca) {
			ContaPoupanca conta2 = (ContaPoupanca) conta;
			ContaPoupancaController contaPoupancaController=new ContaPoupancaController(conta2);
			contaPoupancaController.depositar(valor);
		}
	}
	
	public void transferir(IConta contaOrigem,IConta contaDestino,float valor) {
		
		if(contaOrigem instanceof ContaCorrente) {
			ContaCorrente conta2 = (ContaCorrente) contaOrigem;
			ContaCorrenteController contaCorrenteController= new ContaCorrenteController(conta2);
			contaCorrenteController.transferir(contaDestino, valor);
		}else if(contaOrigem instanceof ContaPoupanca) {
			ContaPoupanca conta2 = (ContaPoupanca) contaOrigem;
			ContaPoupancaController contaPoupancaController=new ContaPoupancaController(conta2);
			contaPoupancaController.transferir(contaDestino, valor);
		}
	}
	
	public void extrato(IConta conta, int mes, int ano) {
	
		if(conta instanceof ContaCorrente) {
			ContaCorrente conta2 = (ContaCorrente)conta; 
			ContaCorrenteController contaCorrenteController= new ContaCorrenteController(conta2);
			contaCorrenteController.imprimirExtrato(mes, ano);
		}else if(conta instanceof ContaPoupanca) {
			ContaPoupanca conta2 = (ContaPoupanca) conta;
			ContaPoupancaController contaPoupancaController=new ContaPoupancaController(conta2);
			contaPoupancaController.imprimirExtrato(mes, ano);
		}
	}
	
	
}
