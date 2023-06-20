package dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import db.IConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTransientConnectionException;

import model.ContaPoupanca;

public class ContaPoupancaDAO implements IEntityDAO<ContaPoupanca> {

    private IConnection connection;

    public ContaPoupancaDAO(IConnection connection) {
        this.connection = connection;
    }

    @Override
    public void save(ContaPoupanca t) {
        int maxAttempts = 3;
        int attempts = 0;

        while (attempts < maxAttempts) {
            try {
                String sql = "INSERT INTO conta_poupanca (id, numero_conta, saldo, status) VALUES (?, ?, ?, ?)";
                PreparedStatement statement = connection.getConnection().prepareStatement(sql);
                statement.setInt(1, t.getId());
                statement.setString(2, t.getNumeroConta());
                statement.setFloat(3, t.getSaldo());
                statement.setBoolean(4, t.isStatus());
                statement.executeUpdate();
                statement.close();
                break;
            } catch (SQLTransientConnectionException e) {
                System.err.println("Erro transiente de conexão. Tentando novamente...");
                e.printStackTrace();
                attempts++;
            } catch (SQLException e) {
                System.err.println("Erro no Insert na tabela conta_poupanca");
                e.printStackTrace();
                break;
            }
        }

        if (attempts == maxAttempts) {
            System.err.println("Falha ao inserir a conta poupança após " + maxAttempts + " tentativas. Verifique a conexão com o banco de dados.");
        }
    }

    @Override
    public List<ContaPoupanca> findAll() {
        int maxAttempts = 3;
        int attempts = 0;

        while (attempts < maxAttempts) {
            try {
                List<ContaPoupanca> contasPoupanca = new ArrayList<>();
                String sql = "SELECT * FROM conta_poupanca";
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
                return contasPoupanca; // Retorna a lista de contas poupança se a execução for bem-sucedida
            } catch (SQLTransientConnectionException e) {
                System.err.println("Erro transiente de conexão. Tentando novamente...");
                e.printStackTrace();
                attempts++;
            } catch (SQLException e) {
                System.err.println("Erro na consulta da tabela conta_poupanca");
                e.printStackTrace();
                break;
            }
        }

        if (attempts == maxAttempts) {
            System.err.println("Falha ao consultar as contas poupança após " + maxAttempts + " tentativas. Verifique a conexão com o banco de dados.");
        }

        return Collections.emptyList(); // Retorna uma lista vazia se ocorrer uma falha
    }

    @Override
    public void update(ContaPoupanca t) {
        int maxAttempts = 3;
        int attempts = 0;

        while (attempts < maxAttempts) {
            try {
                String sql = "UPDATE conta_poupanca SET saldo = ?, status = ? WHERE id = ?";
                PreparedStatement statement = connection.getConnection().prepareStatement(sql);
                statement.setFloat(1, t.getSaldo());
                statement.setBoolean(2, t.isStatus());
                statement.setInt(3, t.getId());
                statement.executeUpdate();
                statement.close();
                break;
            } catch (SQLTransientConnectionException e) {
                System.err.println("Erro transiente de conexão. Tentando novamente...");
                e.printStackTrace();
                attempts++;
            } catch (SQLException e) {
                System.err.println("Erro na atualização da tabela conta_poupanca");
                e.printStackTrace();
                break;
            }
        }

        if (attempts == maxAttempts) {
            System.err.println("Falha ao atualizar a conta poupança após " + maxAttempts + " tentativas. Verifique a conexão com o banco de dados.");
        }
    }

    @Override
    public void delete(ContaPoupanca t) {
        int maxAttempts = 3;
        int attempts = 0;

        while (attempts < maxAttempts) {
            try {
                String sql = "DELETE FROM conta_poupanca WHERE numero_conta = ?";
                PreparedStatement statement = connection.getConnection().prepareStatement(sql);
                statement.setString(1, t.getNumeroConta());
                statement.executeUpdate();
                statement.close();
                break;
            } catch (SQLTransientConnectionException e) {
                System.err.println("Erro transiente de conexão. Tentando novamente...");
                e.printStackTrace();
                attempts++;
            } catch (SQLException e) {
                System.err.println("Erro na exclusão da tabela conta_poupanca");
                e.printStackTrace();
                break;
            }
        }

        if (attempts == maxAttempts) {
            System.err.println("Falha ao excluir a conta poupança após " + maxAttempts + " tentativas. Verifique a conexão com o banco de dados.");
        }
    }
}
