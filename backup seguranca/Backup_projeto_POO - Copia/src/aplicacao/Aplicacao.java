package aplicacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import facade.ContaCorrenteFacade;
import model.ContaCorrente;

public class Aplicacao {
    public static void main(String[] args) {
        // Criar uma instância do Facade
        ContaCorrenteFacade facade = ContaCorrenteFacade.getInstance();
        
        // Exemplo de uso dos métodos do Facade
        
        // Salvar uma nova conta corrente
        //facade.save(123456, BigDecimal.valueOf(1000), LocalDateTime.now(), true);
        ContaCorrente v = new ContaCorrente(123456);
        v.setDataAbertura(LocalDateTime.now());
        v.depositar(BigDecimal.valueOf(1));
        //v.sacar(BigDecimal.valueOf(252));
        v.setNumeroConta(123456);
        //v.sacar(BigDecimal.valueOf(1));
        //v.setSaldo(BigDecimal.valueOf(20));
        
        facade.update(v);
        
        
    }
}
