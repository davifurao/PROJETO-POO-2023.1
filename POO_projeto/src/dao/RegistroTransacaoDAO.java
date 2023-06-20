package dao;
import java.util.ArrayList;
import java.util.List;

import db.IConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import model.RegistroTransacao;
import model.TipoConta;
import model.TipoTransacao;

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
        List<RegistroTransacao> registros = new ArrayList<>();
        try {
            String sql = "SELECT id, valor, tipo_conta, tipo_transacao, data FROM registro_transacao";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                float valor = resultSet.getFloat("valor");
                String tipoContaString = resultSet.getString("tipo_conta");
                String tipoTransacaoString = resultSet.getString("tipo_transacao");
                LocalDateTime data = resultSet.getTimestamp("data").toLocalDateTime();
                TipoConta tipoConta = TipoConta.valueOf(tipoContaString);
                TipoTransacao tipoTransacao = TipoTransacao.valueOf(tipoTransacaoString);
                RegistroTransacao registro = new RegistroTransacao(id, valor, tipoConta, tipoTransacao, data);
                registros.add(registro);
            }
        } catch (SQLException e) {
            System.err.println("Erro na consulta na tabela registro_transacao");
            e.printStackTrace();
        }
        return registros;
    }

	@Override
    public void update(RegistroTransacao t) {
        try {
            String sql = "UPDATE registro_transacao SET valor = ?, tipo_conta = ?, tipo_transacao = ?, data = ? WHERE id = ?";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setFloat(1, t.getValor());
            statement.setString(2, t.getTipo_conta().toString());
            statement.setString(3, t.getTipo().toString());
            statement.setTimestamp(4, Timestamp.valueOf(t.getData()));
            statement.setInt(5, t.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro no Update na tabela registro_transacao");
            e.printStackTrace();
        }
    }

	@Override
    public void delete(RegistroTransacao t) {
        try {
            String sql = "DELETE FROM registro_transacao WHERE id = ?";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setInt(1, t.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro no Delete na tabela registro_transacao");
            e.printStackTrace();
        }
    }
	
	
	
}
