package model;


import java.math.BigDecimal;
import dao.ContaCorrenteDAO;
import dao.RegistroTransacaoDAO;
import dao.IEntityDAO;
import db.ConexaoBancoMySQL;
import db.IConnection;
import model.ContaCorrente;
import model.ContaPoupanca;


public class Aplicacao {

	public static void main(String[] args) {
		
		ContaCorrente c = new ContaCorrente();
		IEntityDAO<ContaCorrente> daoCC = new ContaCorrenteDAO(new ConexaoBancoMySQL());
		IEntityDAO<RegistroTransacao> daoRG = new RegistroTransacaoDAO(new ConexaoBancoMySQL());
		
		daoCC.save(c);

	}

}
