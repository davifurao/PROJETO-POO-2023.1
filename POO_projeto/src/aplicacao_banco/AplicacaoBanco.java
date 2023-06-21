package aplicacao_banco;

import dao.ContaCorrenteDAO;
import dao.ContaPoupancaDAO;
import db.ConexaoBancoMySQL;
import db.IConnection;

public class AplicacaoBanco {

	IConnection connection = new ConexaoBancoMySQL();
	ContaPoupancaDAO contaPoupancaDAO = new ContaPoupancaDAO(connection);
	ContaCorrenteDAO contaCorrenteDAO = new ContaCorrenteDAO(connection);
	
}
