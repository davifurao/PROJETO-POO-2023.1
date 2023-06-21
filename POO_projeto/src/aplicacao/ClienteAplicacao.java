package aplicacao;

import controller.ClienteController;
import controller.ContaCorrenteController;
import controller.ContaPoupancaController;
import controller.RegistroContaController;
import dao.ClienteDAO;
import dao.RegistroContaDAO;
import db.ConexaoBancoMySQL;
import db.IConnection;
import model.ContaCorrente;
import model.ContaPoupanca;
import model.IConta;
import client_model.*;


public class ClienteAplicacao {
	IConnection connection = new ConexaoBancoMySQL();
	ClienteDAO clienteDAO = new ClienteDAO(connection);
	RegistroContaDAO registroContaDAO = new RegistroContaDAO();
	RegistroContaController registroContaController = new RegistroContaController();
	ClienteController clienteController = new ClienteController();
	
	
	
	ClienteController c = new ClienteController();
	
	public ClienteAplicacao(ClienteDAO clienteDAO2, RegistroContaDAO registroContaDAO2,
			RegistroContaController registroContaController2, ClienteController clienteController2) {
		// TODO Auto-generated constructor stub
	}

	public ClienteAplicacao() {
		// TODO Auto-generated constructor stub
	}

	public void cadastrarCliente(Cliente cliente) {
		clienteDAO.save(cliente);
	}
	
	public void deletarCliente(Cliente cliente) {
		clienteDAO.delete(cliente);
	}
	
	public void atualizarCliente(Cliente cliente) {
		clienteDAO.update(cliente);
	}
	
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
	
	public void cadastrarConta(IConta conta,String cpf) {
		clienteController.cadastrarConta(conta);
		RegistroConta registro = new RegistroConta(cpf, conta.getConta(), conta);
		registroContaController.adicionarRegistro(registro);
	}
	
	public void deletarConta(IConta conta,String cpf) {
		clienteController.deletarConta(conta);
		RegistroConta registro = new RegistroConta(cpf, conta.getConta(), conta);
		registroContaDAO.delete(registro);
	}

}
