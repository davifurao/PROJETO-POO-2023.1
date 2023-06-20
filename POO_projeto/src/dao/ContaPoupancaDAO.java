package dao;

import java.util.ArrayList;
import java.util.List;

import db.IConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.ContaPoupanca;

public class ContaPoupancaDAO implements IEntityDAO<ContaPoupanca> {
	
	private IConnection connection;
	
	public ContaPoupancaDAO(IConnection connection) {
		this.connection = connection;
	}

	@Override
	public void delete(ContaPoupanca t) {
		try {
			String sql = "DELETE FROM conta_poupanca WHERE numero_conta = ?";
			PreparedStatement statement = connection.getConnection().prepareStatement(sql);
			statement.setString(1, t.getNumeroConta());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

	@Override
    public List<ContaPoupanca> findAll() {
        List<ContaPoupanca> contasPoupanca = new ArrayList<>();
        String sql = "SELECT * FROM conta_poupanca";

        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String numeroConta = resultSet.getString("numero_conta");
                float saldo = resultSet.getFloat("saldo");
                boolean status = resultSet.getBoolean("status");

                ContaPoupanca contaPoupanca = new ContaPoupanca(id, numeroConta, saldo, status);
                contasPoupanca.add(contaPoupanca);
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contasPoupanca;
    }

    @Override
    public void update(ContaPoupanca t) {
        String sql = "UPDATE conta_poupanca SET saldo = ?, status = ? WHERE id = ?";

        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setFloat(1, t.getSaldo());
            statement.setBoolean(2, t.isStatus());
            statement.setInt(3, t.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	@Override
	public void save(ContaPoupanca t) {
		try {
            String sql = "INSERT INTO conta_poupanca (id, numero_conta, saldo, status) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setInt(1, t.getId());
            statement.setString(2, t.getNumeroConta());
            statement.setFloat(3, t.getSaldo());
            statement.setBoolean(4, t.isStatus());
            statement.executeUpdate();
        } catch (SQLException e) {
        	System.err.println("Erro no Insert na tabela conta_poupanca");
            e.printStackTrace();
        }
	}
}
