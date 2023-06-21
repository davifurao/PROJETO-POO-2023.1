package controller;

import model.TipoTransacao;
import db.ConexaoBancoMySQL;
import model.ContaCorrente;
import model.ContaPoupanca;
import model.IConta;
import model.RegistroTransacao;
import dao.RegistroTransacaoDAO;

public class RegistroTransacaoController implements IRegistro {

    private ConexaoBancoMySQL connection;
    private RegistroTransacaoDAO registroTransacaoDAO;

    public RegistroTransacaoController() {
        connection = new ConexaoBancoMySQL();
        registroTransacaoDAO = new RegistroTransacaoDAO(connection);
    }

    @Override
    public void adicionarRegistro(float valor, TipoTransacao tipoTransacao, IConta c) {
        RegistroTransacao registroTransacao;

        if (c instanceof ContaCorrente) {
            ContaCorrente conta = (ContaCorrente) c;
            registroTransacao = new RegistroTransacao(valor, tipoTransacao, conta.getConta());
            registroTransacaoDAO.save(registroTransacao);
        } else if (c instanceof ContaPoupanca) {
            ContaPoupanca conta = (ContaPoupanca) c;
            registroTransacao = new RegistroTransacao(valor, tipoTransacao, conta.getConta());
            registroTransacaoDAO.save(registroTransacao);
        } else {
            System.err.println("A conta é de um tipo inválido ou desconhecido");
        }
    }
}
