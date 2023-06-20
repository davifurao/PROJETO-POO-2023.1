package dao;

import java.util.ArrayList;
import java.util.List;

import db.IConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTransientConnectionException;
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
        int maxAttempts = 3;
        int attempts = 0;

        while (attempts < maxAttempts) {
            try {
                String sql = "INSERT INTO registro_transacao (id, valor, tipo_conta, tipo_transacao) VALUES (?, ?, ?, ?);";
                PreparedStatement statement = connection.getConnection().prepareStatement(sql);
                statement.setInt(1, t.getId());
                statement.setFloat(2, t.getValor());
                statement.setString(3, t.getTipo_conta().toString());
                statement.setString(4, t.getTipo().toString());
                statement.executeUpdate();
                statement.close();
                break;
            } catch (SQLTransientConnectionException e) {
                System.err.println("Erro transiente de conexão. Tentando novamente...");
                e.printStackTrace();
                attempts++;
            } catch (SQLException e) {
                System.err.println("Erro no Insert na tabela registro_transacao");
                e.printStackTrace();
                break;
            }
        }

        if (attempts == maxAttempts) {
            System.err.println("Falha ao inserir o registro de transação após " + maxAttempts + " tentativas. Verifique a conexão com o banco de dados.");
        }
    }

    @Override
    public List<RegistroTransacao> findAll() {
        int maxAttempts = 3;
        int attempts = 0;

        while (attempts < maxAttempts) {
            try {
                List<RegistroTransacao> registros = new ArrayList<>();
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

                statement.close();
                return registros; // Retorna a lista de registros se a execução for bem-sucedida
            } catch (SQLTransientConnectionException e) {
                System.err.println("Erro transiente de conexão. Tentando novamente...");
                e.printStackTrace();
                attempts++;
            } catch (SQLException e) {
                System.err.println("Erro na consulta na tabela registro_transacao");
                e.printStackTrace();
                break;
            }
        }

        if (attempts == maxAttempts) {
            System.err.println("Falha ao consultar os registros de transação após " + maxAttempts + " tentativas. Verifique a conexão com o banco de dados.");
        }

        return new ArrayList<>(); // Retorna uma lista vazia em caso de falha
    }

    @Override
    public void update(RegistroTransacao t) {
        int maxAttempts = 3;
        int attempts = 0;

        while (attempts < maxAttempts) {
            try {
                String sql = "UPDATE registro_transacao SET valor = ?, tipo_conta = ?, tipo_transacao = ?, data = ? WHERE id = ?";
                PreparedStatement statement = connection.getConnection().prepareStatement(sql);
                statement.setFloat(1, t.getValor());
                statement.setString(2, t.getTipo_conta().toString());
                statement.setString(3, t.getTipo().toString());
                statement.setTimestamp(4, Timestamp.valueOf(t.getData()));
                statement.setInt(5, t.getId());
                statement.executeUpdate();
                statement.close();
                break;
            } catch (SQLTransientConnectionException e) {
                System.err.println("Erro transiente de conexão. Tentando novamente...");
                e.printStackTrace();
                attempts++;
            } catch (SQLException e) {
                System.err.println("Erro no Update na tabela registro_transacao");
                e.printStackTrace();
                break;
            }
        }

        if (attempts == maxAttempts) {
            System.err.println("Falha ao atualizar o registro de transação após " + maxAttempts + " tentativas. Verifique a conexão com o banco de dados.");
        }
    }

    @Override
    public void delete(RegistroTransacao t) {
        int maxAttempts = 3;
        int attempts = 0;

        while (attempts < maxAttempts) {
            try {
                String sql = "DELETE FROM registro_transacao WHERE id = ?";
                PreparedStatement statement = connection.getConnection().prepareStatement(sql);
                statement.setInt(1, t.getId());
                statement.executeUpdate();
                statement.close();
                break;
            } catch (SQLTransientConnectionException e) {
                System.err.println("Erro transiente de conexão. Tentando novamente...");
                e.printStackTrace();
                attempts++;
            } catch (SQLException e) {
                System.err.println("Erro no Delete na tabela registro_transacao");
                e.printStackTrace();
                break;
            }
        }

        if (attempts == maxAttempts) {
            System.err.println("Falha ao excluir o registro de transação após " + maxAttempts + " tentativas. Verifique a conexão com o banco de dados.");
        }
    }
}
