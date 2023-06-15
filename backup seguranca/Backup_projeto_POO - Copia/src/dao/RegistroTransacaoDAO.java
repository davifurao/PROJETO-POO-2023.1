package dao;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import db.IConnection;
import model.RegistroTransacao;

public class RegistroTransacaoDAO implements IEntityDAO<RegistroTransacao> {

	private IConnection conn;
	
	public RegistroTransacaoDAO(IConnection conn) {
		this.conn = conn;
	}
	
	@Override
	public void save(RegistroTransacao t) {
	    String sql = "INSERT INTO registro_transacao (id, valor, tipo_transacao, conta_poupanca_id, conta_corrente_id, tipo_conta, data) " +
	                 "VALUES (?, ?, ?, ?, ?, ?, ?)";
	    
	    try {
	    	PreparedStatement pstm = conn.getConnection().prepareStatement(sql);
	    	pstm.setInt(1, t.getId());
	    	pstm.setBigDecimal(2, t.getValor());
	    	pstm.setString(3, t.getTipo().name());
	    	pstm.setNull(4, java.sql.Types.INTEGER);
	    	pstm.setNull(5, java.sql.Types.INTEGER);
	    	pstm.setString(6, t.getTipo_conta().name());
	    	pstm.setTimestamp(7, Timestamp.valueOf(t.getData()));
	    	
	    	pstm.executeUpdate();
	    	
	    	System.out.println("RegistroTransacao salvo com sucesso.");
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    	
	    }
	}

	@Override
	public RegistroTransacao findById(Integer id) {
		String sql = "SELECT * FROM registro_transacao WHERE ID = ?;";
		ResultSet rs;
		
		try {
			PreparedStatement pstm = conn.getConnection().prepareStatement(sql);
			pstm.setInt(1, id);
			rs = pstm.executeQuery();
			while(rs.next()) {
				//transformar o LocalDateTime em timestamp
				java.sql.Timestamp timestamp = rs.getTimestamp(3);
				LocalDateTime data = timestamp.toLocalDateTime();
				return new RegistroTransacao(rs.getInt("id"), rs.getBigDecimal("valor"), rs.getString("tipo_transacao"), rs.getInt("tipo_conta"), data);

			}
		} catch (SQLException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
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
