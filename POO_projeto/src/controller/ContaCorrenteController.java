package controller;

import dao.ContaCorrenteDAO;
import dao.ContaPoupancaDAO;
import db.ConexaoBancoMySQL;
import db.IConnection;
import model.ContaCorrente;
import model.ContaPoupanca;
import model.IConta;

//Os atributos do BD já devem existir, ele vai somente maniipular(update)

public class ContaCorrenteController implements IContaController {

	ConexaoBancoMySQL connection = new ConexaoBancoMySQL();

	ContaCorrenteDAO contaCorrenteDAO = new ContaCorrenteDAO(connection);
	
	ContaPoupancaDAO contaPoupancaDAO = new ContaPoupancaDAO(connection);

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
	}

	@Override
	public void depositar(float quantia) {
		//achar a conta no BD
		ContaCorrente c = contaCorrenteDAO.findByNumeroConta(contaCorrente.getNumeroConta());
		c.depositar(quantia);
		System.out.println("Saldo debug: "+contaCorrente.getSaldo());
		contaCorrenteDAO.update(c);
	}

	@Override
	public void transferir(IConta contaDestino, float valor) {
		
		ContaCorrente c = contaCorrenteDAO.findByNumeroConta(contaCorrente.getNumeroConta());
		
	    float saldoInicial = contaCorrente.getSaldo();
	    float saldoFinal = contaCorrente.getSaldo();
	    float saldoEsperado = saldoInicial - valor;
	    
	    //verificar qual tipo é a conta de destino para fazer o update na tabela correta
	    //conta Corrente
	    if (contaDestino instanceof ContaCorrente) {
	        ContaCorrente contaCorrenteDestino = (ContaCorrente) contaDestino;
	        ContaCorrente conta_corrente_banco_destino = contaCorrenteDAO.findByNumeroConta(contaCorrenteDestino.getNumeroConta());
	        contaCorrente.transferir(conta_corrente_banco_destino, valor);
		    
	        contaCorrenteDAO.update(c);
		    contaCorrenteDAO.update(conta_corrente_banco_destino);
		    
	        
	    //conta poupanca
	    } else if (contaDestino instanceof ContaPoupanca) {
	        ContaPoupanca contaPoupancaDestino = (ContaPoupanca) contaDestino;
	        ContaPoupanca conta_poupanca_banco_destino = contaPoupancaDAO.findByNumeroConta(contaPoupancaDestino.getNumeroConta());
	        contaCorrente.transferir(conta_poupanca_banco_destino, valor);
		    
		     contaCorrenteDAO.update(c);
		     contaPoupancaDAO.update(conta_poupanca_banco_destino);
		    
	        
	    } else {
	        System.err.println("A conta é de um tipo inválido ou desconhecido");
	    }
	    
	    
	}

	@Override
	public void imprimirExtrato(int mes, int ano) {
		contaCorrenteDAO.getTransacoesPorMesAno(contaCorrente.getId(), mes, ano);

	}

}
