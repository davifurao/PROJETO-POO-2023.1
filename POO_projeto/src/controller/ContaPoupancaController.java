package controller;

import dao.ContaCorrenteDAO;
import dao.ContaPoupancaDAO;
import dao.RegistroTransacaoDAO;
import db.ConexaoBancoMySQL;
import model.ContaCorrente;
import model.ContaPoupanca;
import model.IConta;
import model.TipoTransacao;

public class ContaPoupancaController implements IContaController {

    private ConexaoBancoMySQL connection = new ConexaoBancoMySQL();
    private ContaPoupancaDAO contaPoupancaDAO = new ContaPoupancaDAO(connection);

    
    RegistroTransacaoDAO registroTransacaoDAO = new RegistroTransacaoDAO(connection);
    //chamada vazia para poder acessar os metodos dessa respectiva classe
  	RegistroTransacaoController registrocontroller = new RegistroTransacaoController();
    private ContaPoupanca contaPoupanca;

    public ContaPoupancaController(ContaPoupanca contaPoupanca) {
        this.contaPoupanca = contaPoupanca;
    }

    @Override
    public void sacar(float quantia) {
        ContaPoupanca c = contaPoupancaDAO.findByNumeroConta(contaPoupanca.getNumeroConta());
        c.sacar(quantia);
        contaPoupancaDAO.update(c);
        registrocontroller.adicionarRegistro(quantia, TipoTransacao.SAQUE, c);
    }

    @Override
    public void depositar(float quantia) {
        ContaPoupanca c = contaPoupancaDAO.findByNumeroConta(contaPoupanca.getNumeroConta());
        c.depositar(quantia);
        contaPoupancaDAO.update(c);
        registrocontroller.adicionarRegistro(quantia, TipoTransacao.DEPOSITO, c);
    }

    @Override
    public void transferir(IConta contaDestino, float valor) {
        ContaPoupanca c = contaPoupancaDAO.findByNumeroConta(contaPoupanca.getNumeroConta());

        if (contaDestino instanceof ContaCorrente) {
            ContaCorrente contaCorrenteDestino = (ContaCorrente) contaDestino;
            ContaCorrente contaCorrenteBancoDestino = new ContaCorrenteDAO(connection).findByNumeroConta(contaCorrenteDestino.getNumeroConta());
            c.transferir(contaCorrenteBancoDestino, valor);
            contaPoupancaDAO.update(c);
            registrocontroller.adicionarRegistro(valor, TipoTransacao.TRANSFERENCIA_CREDITO, c);
            new ContaCorrenteDAO(connection).update(contaCorrenteBancoDestino);
            registrocontroller.adicionarRegistro(valor, TipoTransacao.TRASNFERENCIA_DEBITO, contaCorrenteBancoDestino);
        } else if (contaDestino instanceof ContaPoupanca) {
            ContaPoupanca contaPoupancaDestino = (ContaPoupanca) contaDestino;
            ContaPoupanca contaPoupancaBancoDestino = contaPoupancaDAO.findByNumeroConta(contaPoupancaDestino.getNumeroConta());
            c.transferir(contaPoupancaBancoDestino, valor);
            contaPoupancaDAO.update(c);
            registrocontroller.adicionarRegistro(valor, TipoTransacao.TRANSFERENCIA_CREDITO, c);
            contaPoupancaDAO.update(contaPoupancaBancoDestino);
            registrocontroller.adicionarRegistro(valor, TipoTransacao.TRASNFERENCIA_DEBITO, contaPoupancaBancoDestino);
        } else {
            System.err.println("A conta é de um tipo inválido ou desconhecido");
        }
    }

    @Override
    public void imprimirExtrato(int mes, int ano) {
        contaPoupancaDAO.getTransacoesPorMesAno(contaPoupanca.getId(), mes, ano);
    }
}
