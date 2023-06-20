package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLTransientConnectionException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import client_model.Cliente;
import db.IConnection;

public class ClienteDAO implements IEntityDAO<Cliente> {

	private IConnection connection;

	public ClienteDAO(IConnection connection) {
		this.connection = connection;
	}

	@Override
	public void save(Cliente t) {

		int maxAttempts = 3; // Número máximo de tentativas de conexão
		int attempts = 0; // Contador de tentativas

		while (attempts < maxAttempts) {
			try {
				String sql = "INSERT INTO cliente(cpf) VALUES(?);";
				PreparedStatement statement = connection.getConnection().prepareStatement(sql);
				statement.setString(1, t.getCpf());
				statement.setString(1, t.getCpf());
				statement.executeUpdate();
			} catch (SQLIntegrityConstraintViolationException e) {
				System.err.println("Erro no Insert na tabela cliente: CPF já existente.");
				e.printStackTrace();
			}
			// Quando um erro ocorrer durante a comunicação
			catch (SQLTransientConnectionException e) {
				System.err.println("Erro de conexão transiente durante o Insert na tabela cliente.");
				attempts++; // Incrementa o contador de tentativas
				e.printStackTrace();
			} catch (SQLException e) {
				System.err.println("Erro no Insert na tabela cliente");
				e.printStackTrace();
			}
		}

		if (attempts == maxAttempts) {
			System.err.println("Falha ao inserir o cliente após " + maxAttempts
					+ " tentativas. Verifique a conexão com o banco de dados.");
		}

	}

	@Override
	public List<Cliente> findAll() {
	    int maxAttempts = 3;
	    int attempts = 0;

	    while (attempts < maxAttempts) {
	        try {
	            List<Cliente> clientes = new ArrayList<>();
	            String sql = "SELECT * FROM cliente";
	            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
	            ResultSet resultSet = statement.executeQuery();

	            while (resultSet.next()) {
	                int id = resultSet.getInt("id");
	                String cpf = resultSet.getString("cpf");

	                Cliente cliente = new Cliente(id, cpf);
	                clientes.add(cliente);
	            }

	            statement.close();
	            return clientes; // Retorna a lista de clientes se a execução for bem-sucedida
	        } catch (SQLTransientConnectionException e) {
	            System.err.println("Erro transiente de conexão. Tentando novamente...");
	            e.printStackTrace();
	            attempts++;
	        } catch (SQLException e) {
	            System.err.println("Erro na consulta da tabela cliente");
	            e.printStackTrace();
	            break;
	        }
	    }
	    if (attempts == maxAttempts) {
	        System.err.println("Falha ao consultar os clientes após " + maxAttempts + " tentativas. Verifique a conexão com o banco de dados.");
	    }

	    return Collections.emptyList(); // Retorna uma lista vazia se ocorrer uma falha
	}


	@Override
	public void update(Cliente t) {
	    int maxAttempts = 3;
	    int attempts = 0;

	    while (attempts < maxAttempts) {
	        try {
	            String sql = "UPDATE cliente SET cpf = ? WHERE id = ?";
	            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
	            statement.setString(1, t.getCpf());
	            statement.setInt(2, t.getId());
	            statement.executeUpdate();
	            statement.close();
	            break;
	        } catch (SQLTransientConnectionException e) {
	            System.err.println("Erro transiente de conexão. Tentando novamente...");
	            e.printStackTrace();
	            attempts++;
	        } catch (SQLException e) {
	            System.err.println("Erro na atualização da tabela cliente");
	            e.printStackTrace();
	            break;
	        }
	    }

	    if (attempts == maxAttempts) {
	        System.err.println("Falha ao atualizar o cliente após " + maxAttempts + " tentativas. Verifique a conexão com o banco de dados.");
	    }
	}


	// Nesse metodo iremos deletar todos os clientes que possuem o mesmo cpf(pois
	// não coloquei como UNIQUE na tabela)
	@Override
	public void delete(Cliente t) {
	    int maxAttempts = 3;
	    int attempts = 0;

	    while (attempts < maxAttempts) {
	        try {
	            String sql = "DELETE FROM cliente WHERE cpf = ?";
	            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
	            statement.setString(1, t.getCpf());
	            statement.executeUpdate();
	            statement.close();
	            break;
	        } catch (SQLTransientConnectionException e) {
	            System.err.println("Erro transiente de conexão. Tentando novamente...");
	            e.printStackTrace();
	            attempts++;
	        } catch (SQLException e) {
	            System.err.println("Erro na exclusão da tabela cliente");
	            e.printStackTrace();
	            break;
	        }
	    }

	    if (attempts == maxAttempts) {
	        System.err.println("Falha ao excluir o cliente após " + maxAttempts + " tentativas. Verifique a conexão com o banco de dados.");
	    }
	}
}
