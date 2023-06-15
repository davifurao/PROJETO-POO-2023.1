package dao;

import model.ContaCorrente;
import model.RegistroTransacao;
import model.TipoConta;
import model.TipoTransacao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import db.IConnection;
import model.ContaCorrente;

public class ContaCorrenteDAO implements IEntityDAO<ContaCorrente> {

	private IConnection conn;
	private dao.RegistroTransacaoDAO RegistroTransacaoDAO;
	
	public ContaCorrenteDAO(IConnection conn) {
		this.conn = conn;
		RegistroTransacaoDAO = new RegistroTransacaoDAO(conn);
	}
	
	@Override
	public void save(ContaCorrente t) {
		String sql = "INSERT INTO conta_corrente (numeroConta, saldo, dataAbertura, status) VALUES(?,?,?,?)";
		java.sql.Timestamp dataSQL = java.sql.Timestamp.valueOf(t.getDataAbertura());//transformando a data atual para datetime(pois estava dando erro)
		
		//transformar o status boolean em string
		
		String status_string = String.valueOf(t.isStatus());
		
		try {
			PreparedStatement ptsm = conn.getConnection().prepareStatement(sql);
			ptsm.setInt(1, t.getNumeroConta());
			ptsm.setBigDecimal(2, t.getSaldo());
			ptsm.setTimestamp(3, dataSQL);
			ptsm.setString(4, status_string); //o booleano agora é string
			ptsm.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		for(RegistroTransacao e : t.getTransacoes()) {
			RegistroTransacaoDAO.save(e);
		}
		
		conn.closeConnection();
	}

	@Override
	public ContaCorrente findById(Integer id) {
		String sql1 = "SELECT * FROM conta_corrente WHERE numeroConta=?";
		String sql2 = "SELECT * FROM registro_transacao WHERE conta_corrente_id = ?";
		
		ResultSet rs;
		
		ContaCorrente c = new ContaCorrente();
		List<RegistroTransacao> transacao = new ArrayList<>();
		
		try {
			PreparedStatement ptsm = conn.getConnection().prepareStatement(sql1);
			ptsm.setInt(1, id);
			rs = ptsm.executeQuery();
			while(rs.next()) {
				c.setNumeroConta(rs.getInt(1));
				c.setSaldo(rs.getBigDecimal(2));
				//---------------------------------
				//statement de transformação de localdate para timestamp para manipulação
				//Isso pois não existe um metodo nativo da classe ResultSet para o LocalDateTime
				java.sql.Timestamp timestamp = rs.getTimestamp(3);
				LocalDateTime dataAbertura = timestamp.toLocalDateTime();
				c.setDataAbertura(dataAbertura);
				//-------------------------------
				c.setStatus(rs.getBoolean(4));
				//A taxa de transação vai ser utilizada em todas as classes
				
				while(rs.next()) {
					
					//transformar LocalDateTime em timestamp para ser manipulado pela classe ResultSet
					java.sql.Timestamp Timestamp = rs.getTimestamp(4);
					LocalDateTime data = timestamp.toLocalDateTime();
					
					//Nesse caso é utilizado o metodo transacaoFromValor dos enumerators para 
					//transformar o dado em inteiro
					transacao.add(new RegistroTransacao(rs.getBigDecimal(1),TipoTransacao.transacaoFromValor(rs.getInt(2)),TipoConta.transacaoFromValor(rs.getInt(3)),data));
				}
				c.setTransacoes(transacao);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return c;
	}

	@Override
	public List<ContaCorrente> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(ContaCorrente t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(ContaCorrente t) {
		String sql = "delete from conta_corrente where numeroConta = ?;";
		try {
			PreparedStatement pstm = conn.getConnection().prepareStatement(sql);
			pstm.setInt(1, t.getNumeroConta());
			pstm.executeUpdate();
			
		
	}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	
	
}
}
