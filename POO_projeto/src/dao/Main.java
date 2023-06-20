package dao;

import db.ConexaoBancoMySQL;
import model.RegistroTransacao;
import model.TipoConta;
import model.TipoTransacao;
import dao.RegistroTransacaoDAO;

public class Main {
    public static void main(String[] args) {
        // Cria uma conexão com o banco de dados
        ConexaoBancoMySQL conexao = new ConexaoBancoMySQL();
        
        // Cria um objeto RegistroTransacao
        RegistroTransacao registro = new RegistroTransacao(100.0f, TipoTransacao.SAQUE, TipoConta.CONTA_CORRENTE);
        
        // Cria um objeto RegistroTransacaoDAO passando a conexão
        RegistroTransacaoDAO registroDAO = new RegistroTransacaoDAO(conexao);
        
        // Salva o objeto no banco de dados
        registroDAO.save(registro);
        
        // Fecha a conexão com o banco de dados
        conexao.closeConnection();
    }
}
