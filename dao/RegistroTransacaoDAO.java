package dao;

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
		// TODO Auto-generated method stub
		
	}

	@Override
	public RegistroTransacao findById(Integer id) {
		// TODO Auto-generated method stub
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
