package controller;

import dao.ContaCorrenteDAO;
import dao.ContaPoupancaDAO;
import dao.RegistroTransacaoDAO;
import db.ConexaoBancoMySQL;
import model.ContaCorrente;
import model.ContaPoupanca;
import model.IConta;
import model.TipoTransacao;

//Os atributos do BD já devem existir, ele vai somente maniipular(update)
//Portanto eles devem estar salvos

public class ContaCorrenteController implements IContaController {

	ConexaoBancoMySQL connection = new ConexaoBancoMySQL();

	ContaCorrenteDAO contaCorrenteDAO = new ContaCorrenteDAO(connection);
	
	ContaPoupancaDAO contaPoupancaDAO = new ContaPoupancaDAO(connection);
	
	RegistroTransacaoDAO registroTransacaoDAO = new RegistroTransacaoDAO(connection);
	
	//chamada vazia para poder acessar os metodos dessa respectiva classe
	RegistroTransacaoController registrocontroller = new RegistroTransacaoController();

	// aproveitar os metodos de conta corrente
	private ContaCorrente contaCorrente;

	// O unico atributo do construtor é o model
	public ContaCorrenteController(ContaCorrente contaCorrente) {
		this.contaCorrente = contaCorrente;
	}

	@Override
	public void sacar(float quantia) {
		ContaCorrente c = contaCorrenteDAO.findByNumeroConta(contaCorrente.getNumeroConta());
		c.sacar(quantia);
		contaCorrenteDAO.update(c);
		registrocontroller.adicionarRegistro(quantia, TipoTransacao.SAQUE, c);
	}

	@Override
	public void depositar(float quantia) {
		//achar a conta no BD
		ContaCorrente c = contaCorrenteDAO.findByNumeroConta(contaCorrente.getNumeroConta());
		c.depositar(quantia);
		contaCorrenteDAO.update(c);
		registrocontroller.adicionarRegistro(quantia, TipoTransacao.DEPOSITO, c);
	}

	@Override
	public void transferir(IConta contaDestino, float valor) {
		ContaCorrente c = contaCorrenteDAO.findByNumeroConta(contaCorrente.getNumeroConta());
		
	    
	    //verificar qual tipo é a conta de destino para fazer o update na tabela correta
	    //conta Corrente
	    if (contaDestino instanceof ContaCorrente) {
	        ContaCorrente contaCorrenteDestino = (ContaCorrente) contaDestino;
	        ContaCorrente conta_corrente_banco_destino = contaCorrenteDAO.findByNumeroConta(contaCorrenteDestino.getNumeroConta());
	        c.transferir(conta_corrente_banco_destino, valor);
		    
	        contaCorrenteDAO.update(c);
	        registrocontroller.adicionarRegistro(valor, TipoTransacao.TRANSFERENCIA_CREDITO, c);
		    contaCorrenteDAO.update(conta_corrente_banco_destino);
		    registrocontroller.adicionarRegistro(valor, TipoTransacao.TRASNFERENCIA_DEBITO, conta_corrente_banco_destino);
		    
	        
	    //conta poupanca
	    } else if (contaDestino instanceof ContaPoupanca) {
	        ContaPoupanca contaPoupancaDestino = (ContaPoupanca) contaDestino;
	        ContaPoupanca conta_poupanca_banco_destino = contaPoupancaDAO.findByNumeroConta(contaPoupancaDestino.getNumeroConta());
	        c.transferir(conta_poupanca_banco_destino, valor);
		    
		     contaCorrenteDAO.update(c);
		     registrocontroller.adicionarRegistro(valor, TipoTransacao.TRANSFERENCIA_CREDITO, c);
		     contaPoupancaDAO.update(conta_poupanca_banco_destino);
		     registrocontroller.adicionarRegistro(valor, TipoTransacao.TRASNFERENCIA_DEBITO, conta_poupanca_banco_destino);
		    
	        
	    } else {
	        System.err.println("A conta é de um tipo inválido ou desconhecido");
	    }
	    
	    
	}

	@Override
	public void imprimirExtrato(int mes, int ano) {
		contaCorrenteDAO.getTransacoesPorMesAno(contaCorrente.getId(), mes, ano);

	}

}
