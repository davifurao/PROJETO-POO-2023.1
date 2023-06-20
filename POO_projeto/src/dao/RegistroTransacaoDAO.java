package dao;
import java.util.ArrayList;
import java.util.List;

import db.IConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.RegistroTransacao;

public class RegistroTransacaoDAO implements IEntityDAO<RegistroTransacao> {

private IConnection connection;
	
	public RegistroTransacaoDAO(IConnection connection) {
		this.connection = connection;
	}

	@Override
	public void save(RegistroTransacao t) {
		try {
            String sql = "INSERT INTO registro_transacao (id,valor,tipo_conta,tipo_transacao) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setInt(1, t.getId());
            statement.setFloat(2, t.getValor());
            statement.setString(3, t.getTipo_conta().toString());
            statement.setString(4, t.getTipo().toString());
            statement.executeUpdate();
        } catch (SQLException e) {
        	System.err.println("Erro no Insert na tabela conta_corrente");
            e.printStackTrace();
        }

		
	}

	@Override
	public List<RegistroTransacao> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(RegistroTransacao t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(RegistroTransacao t) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
