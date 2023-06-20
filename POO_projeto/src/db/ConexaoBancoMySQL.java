package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import util.ConfigLoader;

public class ConexaoBancoMySQL implements IConnection{

	private final static String DB_ADDRESS;
	private final static String DB_PORT;
	private final static String DB_SCHEMA;
	private final static String DB_USER;
	private final static String DB_PASSWORD;
	
	static {
		DB_ADDRESS = ConfigLoader.loadConfig().getProperty("DB_ADDRESS");
		DB_PORT = ConfigLoader.loadConfig().getProperty("DB_PORT");
		DB_SCHEMA = ConfigLoader.loadConfig().getProperty("DB_SCHEMA");
		DB_USER = ConfigLoader.loadConfig().getProperty("DB_USER");
		DB_PASSWORD = ConfigLoader.loadConfig().getProperty("DB_PASSWORD");
	}
	
	private Connection connection;
	
	public Connection getConnection() {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://"+DB_ADDRESS+":"+DB_PORT+"/"+DB_SCHEMA, DB_USER, DB_PASSWORD);
			return connection;
		} catch (SQLException e) {
			System.err.println("Erro ao estabelecer conexão com o banco de dados:");//tratamento de exceção
			e.printStackTrace();
			return null;
		}
	}


	@Override
	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			System.err.println("Erro ao fechar conexão com o banco de dados:");//tratamento de exceção
			e.printStackTrace();
		}
	}
	
	
	//Teste de conexão
	public void testConnection() {
	    Connection connection = null;
	    try {
	        connection = getConnection();

	        // Consulta de teste
	        String query = "SELECT * FROM banco.conta LIMIT 1";
	        Statement statement = connection.createStatement();
	        ResultSet resultSet = statement.executeQuery(query);

	        // Exibir resultados
	        while (resultSet.next()) {
	            // Obter valores das colunas
	            String coluna1 = resultSet.getString("coluna1");
	            String coluna2 = resultSet.getString("coluna2");

	            // Exibir valores no console
	            System.out.println("Coluna 1: " + coluna1);
	            System.out.println("Coluna 2: " + coluna2);
	        }

	    } catch (SQLException e) {
	        System.err.println("Erro ao executar consulta de teste:");
	        e.printStackTrace();
	    } finally {
	        // Fechar a conexão
	        if (connection != null) {
	            try {
	                connection.close();
	            } catch (SQLException e) {
	                System.err.println("Erro ao fechar conexão com o banco de dados:");
	                e.printStackTrace();
	            }
	        }
	    }
	}
}