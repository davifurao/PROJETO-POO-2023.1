package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import client_model.RegistroConta;
import db.ConexaoBancoMySQL;
import db.IConnection;
import model.ContaCorrente;
import model.ContaPoupanca;
import model.IConta;
import model.TipoConta;


public class RegistroContaDAO implements IEntityDAO<RegistroConta> {

    private IConnection connectionFactory;

    public RegistroContaDAO() {
        connectionFactory = new ConexaoBancoMySQL();
    }

    @Override
    public void save(RegistroConta registroConta) {
        String sql = "INSERT INTO registro_conta (cpf, numero_conta, saldo, tipo, status, data_criacao) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, registroConta.getCpf());
            statement.setString(2, registroConta.getConta().getNumeroConta());
            statement.setDouble(3, registroConta.getConta().getSaldo());
            statement.setString(4, registroConta.getTipoConta().name());
            statement.setBoolean(5, registroConta.getConta().isStatus());
            statement.setObject(6, registroConta.getData());

            statement.executeUpdate();

            // Recupera a chave gerada para o registro conta
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                registroConta.getConta().setId(id);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao adicionar registro conta: " + e.getMessage());
        }
    }

    @Override
    public List<RegistroConta> findAll() {
        List<RegistroConta> registrosConta = new ArrayList<>();
        String sql = "SELECT rc.cpf, rc.numero_conta, rc.saldo, rc.tipo, rc.status, rc.data_criacao "
                + "FROM registro_conta rc "
                + "INNER JOIN cliente c ON rc.cpf = c.cpf";

        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String cpf = resultSet.getString("cpf");
                String numeroConta = resultSet.getString("numero_conta");
                float saldo = resultSet.getFloat("saldo");
                TipoConta tipoConta = TipoConta.valueOf(resultSet.getString("tipo"));
                boolean status = resultSet.getBoolean("status");
                LocalDateTime dataCriacao = resultSet.getTimestamp("data_criacao").toLocalDateTime();

                IConta conta;
                if (tipoConta == TipoConta.CONTA_CORRENTE) {
                    conta = new ContaCorrente(numeroConta, saldo, status);
                } else {
                    conta = new ContaPoupanca(numeroConta, saldo, status);
                }

                RegistroConta registroConta = new RegistroConta(cpf, tipoConta, conta);
                registroConta.setData(dataCriacao);

                registrosConta.add(registroConta);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar registros conta: " + e.getMessage());
        }

        return registrosConta;
    }

    @Override
    public void update(RegistroConta registroConta) {
        int maxAttempts = 3;
        int attempts = 0;

        while (attempts < maxAttempts) {
            try {
                String sql = "UPDATE registro_conta SET saldo = ?, status = ? WHERE numero_conta = ?";
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setDouble(1, registroConta.getConta().getSaldo());
                statement.setBoolean(2, registroConta.getConta().isStatus());
                statement.setString(3, registroConta.getConta().getNumeroConta());
                statement.executeUpdate();
                statement.close();
                connection.close();
                break;
            } catch (SQLException e) {
                System.err.println("Erro na atualização da conta: " + e.getMessage());
                attempts++;
            }
        }

        if (attempts == maxAttempts) {
            System.err.println("Falha ao atualizar a conta após " + maxAttempts + " tentativas. Verifique a conexão com o banco de dados.");
        }
    }
    @Override
    public void delete(RegistroConta registroConta) {
        String sql = "DELETE FROM registro_conta WHERE numero_conta = ?";

        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, registroConta.getConta().getNumeroConta());
            statement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao excluir registro conta: " + e.getMessage());
        }
    }
    
    public RegistroConta findByNumeroConta(String numeroConta) {
        String sql = "SELECT rc.cpf, rc.numero_conta, rc.saldo, rc.tipo, rc.status, rc.data_criacao "
                + "FROM registro_conta rc "
                + "INNER JOIN cliente c ON rc.cpf = c.cpf "
                + "WHERE rc.numero_conta = ?";

        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, numeroConta);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String cpf = resultSet.getString("cpf");
                String numeroConta2 = resultSet.getString("numero_conta");
                float saldo = resultSet.getFloat("saldo");
                TipoConta tipoConta = TipoConta.valueOf(resultSet.getString("tipo"));
                boolean status = resultSet.getBoolean("status");
                LocalDateTime dataCriacao = resultSet.getTimestamp("data_criacao").toLocalDateTime();

                IConta conta;
                if (tipoConta == TipoConta.CONTA_CORRENTE) {
                    conta = new ContaCorrente(numeroConta2, saldo, status);
                } else {
                    conta = new ContaPoupanca(numeroConta2, saldo, status);
                }

                RegistroConta registroConta = new RegistroConta(cpf, tipoConta, conta);
                registroConta.setData(dataCriacao);

                return registroConta;
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar registro de conta por número da conta: " + e.getMessage());
        }

        return null;
    }

    public List<RegistroConta> findByCPF(String cpf) {
        List<RegistroConta> registrosConta = new ArrayList<>();
        String sql = "SELECT rc.cpf, rc.numero_conta, rc.saldo, rc.tipo, rc.status, rc.data_criacao "
                + "FROM registro_conta rc "
                + "INNER JOIN cliente c ON rc.cpf = c.cpf "
                + "WHERE rc.cpf = ?";

        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, cpf);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String cpf2 = resultSet.getString("cpf");
                String numeroConta = resultSet.getString("numero_conta");
                float saldo = resultSet.getFloat("saldo");
                TipoConta tipoConta = TipoConta.valueOf(resultSet.getString("tipo"));
                boolean status = resultSet.getBoolean("status");
                LocalDateTime dataCriacao = resultSet.getTimestamp("data_criacao").toLocalDateTime();

                IConta conta;
                if (tipoConta == TipoConta.CONTA_CORRENTE) {
                    conta = new ContaCorrente(numeroConta, saldo, status);
                } else {
                    conta = new ContaPoupanca(numeroConta, saldo, status);
                }

                RegistroConta registroConta = new RegistroConta(cpf2, tipoConta, conta);
                registroConta.setData(dataCriacao);

                registrosConta.add(registroConta);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar registros de conta por CPF: " + e.getMessage());
        }

        return registrosConta;
    }
    

}
