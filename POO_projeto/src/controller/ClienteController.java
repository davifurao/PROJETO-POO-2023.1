package controller;

import dao.ClienteDAO;
import dao.ContaCorrenteDAO;
import dao.ContaPoupancaDAO;
import dao.RegistroTransacaoDAO;
import db.ConexaoBancoMySQL;
import model.ContaCorrente;
import model.ContaPoupanca;
import model.IConta;

public class ClienteController implements ICliente {

	ConexaoBancoMySQL connection = new ConexaoBancoMySQL();
	
	ClienteDAO clienteDAO = new ClienteDAO(connection);
	
	ContaCorrenteDAO contaCorrenteDAO = new ContaCorrenteDAO(connection);
	
	ContaPoupancaDAO contaPoupancaDAO = new ContaPoupancaDAO(connection);
	
	RegistroTransacaoDAO registroTransacaoDAO = new RegistroTransacaoDAO(connection);
	
	@Override
	public void cadastrarConta(IConta conta) {
		if (conta instanceof ContaCorrente) {
	        ContaCorrente contaCorrente = (ContaCorrente) conta;
	        contaCorrenteDAO.save(contaCorrente);
		}else if(conta instanceof ContaPoupanca) {
			ContaPoupanca contaPoupanca = (ContaPoupanca) conta;
			contaPoupancaDAO.save(contaPoupanca);
		}else {
			System.err.println("A conta é de um tipo inválido ou desconhecido");
		}
	}

	//O id deve ser igual para poder realizar o update
	@Override
	public void alterarConta(IConta contaAntiga, IConta contaNova) {
	    if (contaAntiga instanceof ContaCorrente && contaNova instanceof ContaCorrente) {
	        ContaCorrente contaAntigaBanco = contaCorrenteDAO.findById(contaAntiga.getId());
	        ContaCorrente contaNovaBanco = contaCorrenteDAO.findById(contaNova.getId());
	        atualizarConta(contaAntigaBanco, contaNovaBanco);
	        contaCorrenteDAO.delete(contaNovaBanco);
	    } else if (contaAntiga instanceof ContaPoupanca && contaNova instanceof ContaPoupanca) {
	        ContaPoupanca contaAntigaBanco = contaPoupancaDAO.findById(contaAntiga.getId());
	        ContaPoupanca contaNovaBanco = contaPoupancaDAO.findById(contaNova.getId());
	        atualizarConta(contaAntigaBanco, contaNovaBanco);
	        contaPoupancaDAO.delete(contaNovaBanco);
	    } else if (contaNova instanceof ContaCorrente && contaAntiga instanceof ContaPoupanca) {
	        ContaCorrente contaNovaBanco = contaCorrenteDAO.findById(contaNova.getId());
	        ContaPoupanca contaAntigaBanco = contaPoupancaDAO.findById(contaAntiga.getId());
	        atualizarConta(contaAntigaBanco, contaNovaBanco);
	        contaPoupancaDAO.delete(contaAntigaBanco);
	    } else if (contaNova instanceof ContaPoupanca && contaAntiga instanceof ContaCorrente) {
	        ContaPoupanca contaNovaBanco = contaPoupancaDAO.findById(contaNova.getId());
	        ContaCorrente contaAntigaBanco = contaCorrenteDAO.findById(contaAntiga.getId());
	        atualizarConta(contaAntigaBanco, contaNovaBanco);
	        contaCorrenteDAO.delete(contaAntigaBanco);
	    } else {
	        System.out.println("A conta é de um tipo inválido ou desconhecido");
	    }
	}

	private void atualizarConta(IConta contaAntiga, IConta contaNova) {
	    //contaAntiga.setSaldo(contaNova.getSaldo());
	    //contaAntiga.setStatus(contaNova.isStatus());
	    if(contaAntiga instanceof ContaCorrente) {
	    	ContaCorrente conta = (ContaCorrente)contaAntiga;
	    	//conta.setSaldo(0);
	    	contaCorrenteDAO.update(conta);
	    }else if(contaAntiga instanceof ContaPoupanca) {
	    	ContaPoupanca conta = (ContaPoupanca) contaAntiga;
	    	contaPoupancaDAO.update(conta);
	    }
	}


	
	

	@Override
	public void deletarConta(IConta conta) {
		if (conta instanceof ContaCorrente) {
	        ContaCorrente contaCorrente = (ContaCorrente) conta;
	        contaCorrenteDAO.delete(contaCorrente);
		}else if(conta instanceof ContaPoupanca) {
			ContaPoupanca contaPoupanca = (ContaPoupanca) conta;
			contaPoupancaDAO.delete(contaPoupanca);
		}else {
			System.err.println("A conta é de um tipo inválido ou desconhecido");
		}
		
	}
	
	public void excluirConta(IConta conta) {
        if (conta instanceof ContaCorrente) {
            ContaCorrente contaCorrente = (ContaCorrente) conta;
            contaCorrenteDAO.delete(contaCorrente);
        } else if (conta instanceof ContaPoupanca) {
            ContaPoupanca contaPoupanca = (ContaPoupanca) conta;
            contaPoupancaDAO.delete(contaPoupanca);
        } else {
            System.err.println("A conta é de um tipo inválido ou desconhecido");
        }
    }

}
