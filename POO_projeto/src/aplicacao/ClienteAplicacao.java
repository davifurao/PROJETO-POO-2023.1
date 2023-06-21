package aplicacao;

import controller.ClienteController;
import controller.ICliente;
import controller.RegistroContaController;
import dao.ClienteDAO;
import dao.RegistroContaDAO;
import db.ConexaoBancoMySQL;
import db.IConnection;
import model.IConta;
import dao.ClienteDAO;
import client_model.*;


public class ClienteAplicacao {
	IConnection connection = new ConexaoBancoMySQL();
	ClienteDAO clienteDAO = new ClienteDAO(connection);
	RegistroContaDAO registroContaDAO = new RegistroContaDAO();
	RegistroContaController registroContaController = new RegistroContaController();
	ClienteController clienteController = new ClienteController();
	
	
	
	ClienteController c = new ClienteController();
	
	public ClienteAplicacao(ClienteDAO clienteDAO2, RegistroContaDAO registroContaDAO2,
			RegistroContaController registroContaController2, ClienteController clienteController2) {
		// TODO Auto-generated constructor stub
	}

	public void cadastrarCliente(Cliente cliente) {
		clienteDAO.save(cliente);
	}
	
	public void deletarCliente(Cliente cliente) {
		clienteDAO.delete(cliente);
	}
	
	public void atualizarCliente(Cliente cliente) {
		clienteDAO.update(cliente);
	}
	
	public void cadastrarConta(IConta conta) {
		clienteController.cadastrarConta(conta);
	}
	
	public void deletarConta(IConta conta) {
		clienteController.deletarConta(conta);
	}

}
