package controller;

import java.util.List;
import dao.RegistroContaDAO;
import client_model.RegistroConta;

public class RegistroContaController {

    private RegistroContaDAO registroContaDAO;

    public RegistroContaController() {
        registroContaDAO = new RegistroContaDAO();
    }

    public void adicionarRegistro(RegistroConta registroConta) {
        registroContaDAO.save(registroConta);
    }

    public List<RegistroConta> listarRegistros() {
        return registroContaDAO.findAll();
    }

    public void atualizarRegistro(RegistroConta registroConta) {
        registroContaDAO.update(registroConta);
    }

    public void excluirRegistro(RegistroConta registroConta) {
        registroContaDAO.delete(registroConta);
    }
    
    public RegistroConta buscarRegistroPorNumeroConta(String numeroConta) {
        return registroContaDAO.findByNumeroConta(numeroConta);
    }

    public List<RegistroConta> buscarRegistrosPorCPF(String cpf) {
        return registroContaDAO.findByCPF(cpf);
    }
}
