package aplicacao;


import java.math.BigDecimal;
import dao.ContaCorrenteDAO;
import dao.RegistroTransacaoDAO;
import dao.IEntityDAO;
import db.ConexaoBancoMySQL;
import db.IConnection;
import model.ContaCorrente;
import model.ContaPoupanca;
import model.RegistroTransacao;


public class Aplicacao {

	public static void main(String[] args) {
		
		ContaCorrente d = new ContaCorrente();
		IEntityDAO<ContaCorrente> daoCC = new ContaCorrenteDAO(new ConexaoBancoMySQL());
		IEntityDAO<RegistroTransacao> daoRG = new RegistroTransacaoDAO(new ConexaoBancoMySQL());
		d.setNumeroConta(1885);
		d.depositar(new BigDecimal("200"));
		daoCC.save(d);
		//daoCC.delete(d);
		//ContaCorrente deletar = daoCC.findById(92955380);
		//daoCC.delete(deletar);

	}

}
