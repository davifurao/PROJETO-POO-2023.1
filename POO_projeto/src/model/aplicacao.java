package model;

import db.ConexaoBancoMySQL;

public class aplicacao {

	public static void main(String[] args) {
		
		ConexaoBancoMySQL conexao = new ConexaoBancoMySQL();

	    // Testar a conexão com o banco de dados
	    conexao.testConnection();
		

	}

}
