package facade;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import dao.IEntityDAO;
import dao.ContaCorrenteDAO;
import db.ConexaoBancoMySQL;
import model.ContaCorrente;
import model.RegistroTransacao;

public class ContaCorrenteFacade {

    private IEntityDAO<ContaCorrente> contaCorrenteDAO;

    private static ContaCorrenteFacade instance;

    private ContaCorrenteFacade() {
        contaCorrenteDAO = new ContaCorrenteDAO(new ConexaoBancoMySQL());
    }

    public static ContaCorrenteFacade getInstance() {
        if (instance != null)
            return instance;
        else {
            instance = new ContaCorrenteFacade();
            return instance;
        }
    }

    public void save(Integer numeroConta, BigDecimal saldo, LocalDateTime dataAbertura, boolean status) {
        contaCorrenteDAO.save(new ContaCorrente(numeroConta, saldo, dataAbertura, status));
    }

    public void delete(ContaCorrente contaCorrente) {
        contaCorrenteDAO.delete(contaCorrente);
    }

    public void update(ContaCorrente contaCorrente) {
        contaCorrenteDAO.update(contaCorrente);
    }

    public ContaCorrente findById(Integer id) {
        return contaCorrenteDAO.findById(id);
    }

    public List<ContaCorrente> findAll() {
        return contaCorrenteDAO.findAll();
    }
}
